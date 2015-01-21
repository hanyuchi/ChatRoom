package formbean;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import databeans.User;

public class ChatForm {
	String content;
	String userName;
	String time;
	String send;
	
	public ChatForm(HttpServletRequest request){
		send = request.getParameter("send");
		content = request.getParameter("content");
		User user = (User) request.getSession(false).getAttribute("user");
		userName = user.getUserName();
		if(content != null && content.length() > 1000) content = content.substring(0,1001);
		time = getT();
	}
	
	public boolean isButton(){
		return send != null;
	}
	
	private String getT(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		return format.format(date);
	}
	
	public boolean isPresent(){
		if(content == null || content.length() == 0) return false;
		return true;
	}
	
	public String getContent(){
		return content;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public String getTime(){
		return time;
	}
}
