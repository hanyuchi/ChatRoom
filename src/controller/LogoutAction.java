package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databeans.User;
import model.Model;
import model.UserDAO;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class LogoutAction extends Action {
	private UserDAO userDAO;
	public LogoutAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() { return "logout.do"; }

	public String perform(HttpServletRequest request) {
		User user = (User) request.getSession(false).getAttribute("user");
		userDAO.remove(user);
        HttpSession session = request.getSession(false);
        session.setAttribute("user",null);

        return "login.jsp";
    }
}
