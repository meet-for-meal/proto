package org.essilab.module.connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect  {
	
	public Connect() {
	}
	
	public static Connection getConnection(){
		String base = "MEET_FOR_MEAL";
		String socket = "8889";			//or try 3306
		String url = "jdbc:mysql://localhost:"+socket+"/" + base;
		String user = "root";
		String passwd = "root";
		
		try { 
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			return DriverManager.getConnection(url, user, passwd); 
		}
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); } 
	
		return null;
	}
}
