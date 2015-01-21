package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class LoginForm{
	private String userName;
	private String button;
	
	public LoginForm(HttpServletRequest request){
		userName = request.getParameter("userName");
		if(userName != null && userName.length() > 20) userName = userName.substring(0,21);
		button = request.getParameter("button");
	}
	
    public boolean isPresent(){
    	return button != null;
    }
    
	public String getUserName()  { return userName; }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userName == null || userName.length() == 0) {
			errors.add("User Name is required");
		}
		return errors;
	}
}