package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.UserDAO;
import databeans.User;
import formbean.LoginForm;

public class LoginAction extends Action {
	private UserDAO userDAO;

	public LoginAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() { return "login.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
    	LoginForm form = new LoginForm(request);
        request.setAttribute("form",form);
     	
        if (!form.isPresent()) {
            return "login.jsp";
        }

        // Any validation errors?
        errors.addAll(form.getValidationErrors());
        if (errors.size() != 0) {
            return "login.jsp";
        }

        // Look up the user
        User user = userDAO.read(form.getUserName());
        
        if (user != null) {
            errors.add("User has been existed! Please try another one!");
            return "login.jsp";
        }else{
        	user = new User();
        	user.setUserName(form.getUserName());
        	userDAO.create(user);
        }

        // Attach (this copy of) the user bean to the session
        HttpSession session = request.getSession();
        session.setAttribute("user",user);

        return "manage.do";
    }
}
