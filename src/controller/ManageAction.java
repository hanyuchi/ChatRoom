package controller;

import javax.servlet.http.HttpServletRequest;

import model.ContentDAO;
import model.Model;
import databeans.Content;
import databeans.User;
import formbean.ChatForm;
public class ManageAction extends Action {
	private ContentDAO contentDAO;
	private Model model;
	private int count;
	
	public ManageAction(Model model) {
		count = 0;
		this.model = model;
    	contentDAO = model.getContentDAO();
	}

	public String getName() { return "manage.do"; }

	public String perform(HttpServletRequest request) {
		User user = (User) request.getSession(false).getAttribute("user");
		String name = user.getUserName();
		request.setAttribute("title", name);
		
		ChatForm form = new ChatForm(request);
		if(form.isPresent() && form.isButton()){
			Content content = new Content();
			content.setContent(form.getContent());
			content.setUserName(form.getUserName());
			content.setTime(form.getTime());
			contentDAO.create(content);
		}
		
		model.addAll(contentDAO.getContent(count));
		count = model.getListSize();
		request.setAttribute("list", model.getList());
		
		if(!form.isButton()) request.setAttribute("original", request.getParameter("content"));
		
        return "manage.jsp";
    }
}
