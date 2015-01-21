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
import java.util.List;
import databeans.Content;

public class ContentDAO {

	private ArrayDeque<Connection> connectionPool;
	private String tableName;
	
	public ContentDAO(){
		tableName = "history_chatRoom";
		connectionPool = new ArrayDeque<Connection>();
		if (!tableExist()) {
			createTable();
		}
	}

	public List<Content> getContent(int start){
		List<Content> list = new ArrayList<Content>();
    	try {
    		Connection connection = getConnection();
    		String query = "SELECT * FROM " + tableName + " where contentID > ?";
    		PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, start);
			
			ResultSet set = preparedStatement.executeQuery();
    		
            while (set.next()) {
            	Content c = new Content();
            	c.setUserName(set.getString("userName"));
            	c.setContent(set.getString("content"));
            	c.setTime(set.getString("time"));
            	list.add(c);
            }
            preparedStatement.close();
    	} catch (SQLException e) {
    		System.out.println("return user list error!");
		}
		return list;
	}
	
	public void create(Content content){
		Connection connection = null;
        try {
			connection = getConnection();
			
            PreparedStatement statement = connection.prepareStatement(
            		"INSERT INTO " + tableName + " (userName,content,time) VALUES (?,?,?)");

            statement.setString(1, content.getUserName());
            statement.setString(2, content.getContent());
            statement.setString(3, content.getTime());
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
            		" (contentID INT NOT NULL AUTO_INCREMENT," +
            		" userName VARCHAR(255)," +
            		" content VARCHAR(1000), " +
            		" time VARCHAR(255), " +
            		" PRIMARY KEY(contentID))");
            state.close();
        }catch (SQLException e) {
			System.out.println("connection error in createTable()");;
		}
    }
}
