package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public abstract class Action {
    public abstract String getName();
    public abstract String perform(HttpServletRequest request);

    private static Map<String,Action> hash = new HashMap<String,Action>();

    public static void add(Action a) {
    	synchronized (hash) {
    		hash.put(a.getName(),a);
    	}
    }

    public static String perform(String name, HttpServletRequest request) {
        Action a;
        synchronized (hash) {
        	a = hash.get(name);//find relevant specific action
        }
        
        if (a == null) return null;
        return a.perform(request);
    }
}
