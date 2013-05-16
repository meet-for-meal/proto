package org.essilab.module.user.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.essilab.module.connect.Connect;

public class UserDao {

	//One
	public static User getUser(int userid) throws SQLException {
		String request = "SELECT * FROM User WHERE id = "+userid;
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createUser(result);
	}

	//All
	public static List<User> getAll() throws SQLException {
		String request = "SELECT * FROM User";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();;
		
		return createUsers(result);
	}
	
	//Find Users by name
	public static List<User> findUsersByName(String name) throws SQLException {
		String request = "SELECT * FROM User U "+
			"WHERE '"+name+"' LIKE CONCAT('%',U.lastname,'%') "+
			"OR '"+name+"' LIKE CONCAT('%',U.firstname,'%') "+
			"GROUP BY U.id";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createUsers(result);
	}
	

	//Find friends of an User
	public static List<User> findFriends(int userId) throws SQLException {
		String request = "SELECT F.* FROM User F "+	 
			"INNER JOIN Has_Friend HF ON HF.friendId = F.id "+
			"WHERE HF.userId = "+userId+" "+
			"GROUP BY F.id "+
			"ORDER BY F.lastname ";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createUsers(result);
	}
	
	//Find near Users
	public static List<User> findNearUsers(int userId) throws SQLException {
		String request = "CALL nearUsers("+userId+", 5)";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createShortUsers(result);
	}
	
	//Find near Users with tags
	public static List<User> findNearUsersByTag(int userId, int tagId) throws SQLException {
		String request = "CALL nearUsersByTag("+userId+","+tagId+", 5)";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createShortUsers(result);
//		System.out.println(result.getDouble("distance")+"km");
	}
	
	//Find Users by interests
	public static List<User> findUsersByInterests(List<String> interests) throws SQLException {
		/*SELECT * FROM User U 
		INNER JOIN Has_Interest HI ON U.id = HI.userId 
		INNER JOIN Interest I ON I.id = HI.interestId
		WHERE I.tag = 'Informatique'
		OR I.tag = 'Mode';*/
		
		String request = "SELECT * " +
				"FROM User U " +
				"INNER JOIN Has_Interest HI ON U.id = HI.userId " +
				"INNER JOIN Interest I ON I.id = HI.interestId ";
		if (interests.size() > 0)
			request += "WHERE I.tag = '"+interests.get(0)+"' ";
		if (interests.size() > 1) {
			for(int i=1 ; i<interests.size() ; i++) 
				request += "OR I.tag =  '"+interests.get(i)+"' ";
		}
		request += "GROUP BY U.id";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createUsers(result);
	}
	
	//Find Users by categories
	public static List<User> findUsersByCategories(List<String> categories) throws SQLException {
		String request = "SELECT * FROM User U " +
				"INNER JOIN Has_Taste HT ON U.id = HT.userId " +
				"INNER JOIN Category C ON C.id = HT.categoryId ";
		if (categories.size() > 0)
			request += "WHERE C.name = '"+categories.get(0)+"' ";
		if (categories.size() > 1) {
			for(int i=1 ; i<categories.size() ; i++) 
				request += "OR C.name =  '"+categories.get(i)+"' ";
		}
		request += "GROUP BY U.id";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createUsers(result);
	}
	
	
	//Create short Users
	private static List<User> createShortUsers(ResultSet result) {
		List<User> users = new ArrayList<User>();
		try {
			while (result.next()) 
				users.add(new User(result.getInt("id"), result.getString("firstname"), result.getString("lastname"), result.getString("email")));	
		} catch (SQLException e) { e.printStackTrace();	}
		return users;
	}
	
	//Create User
	private static User createUser(ResultSet result) {
		User user = new User();
		try {
			result.next(); 
			if (result != null) {
				user = new User(
					result.getInt("id"),
					result.getString("firstname"), 
					result.getString("lastname"),
					result.getString("email"),
					result.getString("password"),
					result.getDate("lastPosition"),
					result.getDouble("lastLatitude"),
					result.getDouble("lastLongitude")
				);	
			}
		} catch (SQLException e) { e.printStackTrace();	}
		return user;
	}
	
	//Create Users
	private static List<User> createUsers(ResultSet result) {
		List<User> users = new ArrayList<User>();
		try {
			while (result.next()) {
				users.add(new User(
					result.getInt("id"),
					result.getString("firstname"), 
					result.getString("lastname"),
					result.getString("email"),
					result.getString("password"),
					result.getDate("lastPosition"),
					result.getDouble("lastLatitude"),
					result.getDouble("lastLongitude")
				));	
			}
		} catch (SQLException e) { e.printStackTrace();	}
		return users;
	}
	
	public static void main(String[] args)  {
		try {
			//One
			User u = getUser(1);
			System.out.println(u.getId()+" "+u.getLastname()+" "+u.getFirstname()+"\n");
			
			List<User> items = getAll();
			System.out.println("Nb Users = "+ items.size());
			for (User v : items) 
				System.out.println(v.getId()+" "+v.getLastname()+" "+v.getFirstname());
			System.out.println("");
			
			//Interests
			String [] strings = new String[] {"Animaux", "Jeux vidéo", "Informatique"};
			List<String> params = new ArrayList<String>();
			for(String s : strings)
				params.add(s);
			
			items = findUsersByInterests(params);
			System.out.println("Nb interest = "+ items.size());
			for (User v : items) 
				System.out.println(v.getId()+" "+v.getLastname()+" "+v.getFirstname());
			System.out.println("");
			
			//Tastes
			strings = new String[] {"Restaurant japonais", "Brasserie", "Snack"};
			params = new ArrayList<String>();
			for(String s : strings)
				params.add(s);
			
			items = findUsersByCategories(params);
			System.out.println("Nb taste = "+ items.size());
			for (User v : items) 
				System.out.println(v.getId()+" "+v.getLastname()+" "+v.getFirstname());
			System.out.println("");
			
			//Users name
			items = findUsersByName("connard julien");
			System.out.println("Nb User name = "+ items.size());
			for (User v : items) 
				System.out.println(v.getId()+" "+v.getLastname()+" "+v.getFirstname());
			System.out.println("");
			
			//Near Users
			items = findNearUsers(1);
			System.out.println("Nb Users near = "+ items.size());
			for (User v : items) 
				System.out.println(v.getId()+" "+v.getLastname()+" "+v.getFirstname());
			System.out.println("");
			
			//Near Users tag
			items = findNearUsersByTag(1, 5);
			System.out.println("Nb near tag = "+ items.size());
			for (User v : items) 
				System.out.println(v.getId()+" "+v.getLastname()+" "+v.getFirstname());
			System.out.println("");
			
			//Friends
			items = findFriends(1);
			System.out.println("Nb friends = "+ items.size());
			for (User v : items) 
				System.out.println(v.getId()+" "+v.getLastname()+" "+v.getFirstname());
			System.out.println("");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
