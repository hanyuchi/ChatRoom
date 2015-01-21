package model;

import java.util.ArrayList;
import java.util.List;

import databeans.Content;

public class Model {
	private UserDAO userDAO;
	private ContentDAO contentDAO;
	private List<Content> list;

	public Model(){
		userDAO = new UserDAO();
		contentDAO = new ContentDAO();
		list = new ArrayList<Content>();
	}
	
	public UserDAO getUserDAO() { return userDAO; }
	public ContentDAO getContentDAO() { return contentDAO; }
	public List<Content> getList(){
		return list;
	}
	public void addAll(List<Content> l){
		list.addAll(l);
	}
	public int getListSize(){
		return list.size();
	}
}
