package databeans;

public class User{
	private String userName = null;
	
	public void setUserName(String s) { userName = s.trim().toLowerCase(); }
	public String getUserName() { return userName; }
}
