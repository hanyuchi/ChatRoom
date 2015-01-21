package databeans;

public class Content {
	private String content;
	private String userName;
	private String time;
	
	public void setUserName(String s){
		userName = s;
	}
	public void setContent(String s) { 
		content = s; 
	}
	public void setTime(String s){
		time = s;
	}
	
	public String getContent() { 
		return content; 
	}
	
	public String getUserName(){
		return userName;
	}
	
	public String getTime(){
		return time;
	}
}
