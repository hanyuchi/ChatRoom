package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import databeans.User;

public class UserDAO {
	
	private ArrayDeque<Connection> connectionPool;
	private String tableName;
	
	public UserDAO(){
		tableName = "user_chatRoom";
		connectionPool = new ArrayDeque<Connection>();
		
		if (!tableExist()) {
			createTable();
			User user = new User();
	        user.setUserName("liuying");
        	create(user);
		}
	}
	
	public User read(String s){
		User user = new User();
    	try {
    		Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM " + tableName + " WHERE userName =" + "\""+s+"\"");
            set.next();
        	user.setUserName(set.getString("userName"));
            statement.close();
    	} catch (SQLException e) {
    		user = null;
		}
		return user;
	}

	public User[] getUsers(){
		List<User> list = new ArrayList<User>();
		
    	try {
    		Connection connection = getConnection();

            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM " + tableName);
            
            while (set.next()) {
            	User bean = new User();
            	bean.setUserName(set.getString("userName"));
            	list.add(bean);
            }
            statement.close();
    	} catch (SQLException e) {
    		System.out.println("return user list error!");
		}
    	
    	User[] users = new User[list.size()];
    	for(int i = 0; i < list.size(); i++){
    		users[i] = list.get(i);
    	}
		Arrays.sort(users);  // We want them sorted by last and first names (as per User.compareTo());
		return users;
	}
	
	public void remove(User user){
		String s = user.getUserName();
		try {
			Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(
            		"DELETE FROM " + tableName + " WHERE userName=" + "\""+s+"\"");
            statement.executeUpdate();
            statement.close();
		} catch (SQLException e) {
			System.out.println("SQL delete error!");
		}
	}
	
	public void create(User user){
		Connection connection = null;
        try {
        	connection = getConnection();
        	PreparedStatement statement = connection.prepareStatement("INSERT INTO " + tableName 
        			+ " (userName) VALUES (?)");
        	statement.setString(1,user.getUserName());
        	statement.executeUpdate();
        	
        	statement.close();
        } catch (Exception e) {
            System.out.println("create() error!");
        }
	}
	
	/*multi-threading*/
	private synchronized Connection getConnection(){
		/*record this connection*/
		if (connectionPool.size() > 0) {
			return connectionPool.poll();
		}
		
		Connection connection = null;
	    try{   
	    	Class.forName("com.mysql.jdbc.Driver"); 
	    }
	    catch(ClassNotFoundException e){   
	        System.out.println("cannot find the driver!");   
	    }  
	    
	    /*Define the connection URL*/
	    String host = "localhost";
		String dbName = "test";
		int port = 3306;
		String mysqlURL = "jdbc:mysql://"+ host + ":" + port +"/" + dbName;
		
		/*Establish the connection*/
	    try {
			connection = DriverManager.getConnection(mysqlURL);
		} catch (SQLException e) {
			System.out.println("getConnection() errors in FavoriteDAO");
		}
	    connectionPool.offer(connection);
	    return connection;
	}
	
	private boolean tableExist(){
    	Connection connection = getConnection();
		try {
			DatabaseMetaData metaData = connection.getMetaData();
			ResultSet set = metaData.getTables(null, null, tableName, null);
	    	
	    	boolean ret = set.next();
	    	set.close();
	    	return ret;
		} catch (SQLException e) {
			System.out.println("connection error in tableExist()");;
		}
    	return false;
	}
	
	private void createTable(){
		Connection connection = null;
        try {
        	connection = getConnection();
            Statement state = connection.createStatement();
            state.executeUpdate("CREATE TABLE " + tableName + 
            		" (userID INT NOT NULL AUTO_INCREMENT, "
            		+ "userName VARCHAR(255), PRIMARY KEY(userID))");
            state.close();
        }catch (SQLException e) {
			System.out.println("connection error in createTable()");;
		}
    }
}
