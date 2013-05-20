package org.essilab.module.user.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.essilab.module.connect.Connect;

public class UserDao {
	
	// Search for existing user in DB
	public static User authenticateUser(String mail, String password) throws SQLException {
		String request = "SELECT * FROM User U "+
			"WHERE '"+mail+"' LIKE CONCAT('%',U.email,'%') "+
			"AND '"+password+"' LIKE CONCAT('%',U.password,'%')";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		if(result != null)
			return createUser(result);
		else
			return null;
	}
	
	//setFirstVisit
	public static void setFirstVisit(int id) throws SQLException{
		String request = "UPDATE user SET firstVisit=0 WHERE id= "+ id +"";
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ps.executeUpdate();
		Connect.getConnection().close();
	}
	
	
	//One
	public static User getUser(int userid) throws SQLException {
		String request = "SELECT * FROM User WHERE id = "+userid;
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createUser(result);
	}
	
	//Insert
	public static void insert(User user) throws SQLException{
		String request = "INSERT INTO User VALUES (NULL, " +
			" '"+ user.getFirstname() +"'," +
			" '"+ user.getLastname() +"'," +
			" "+ user.getAge() +"," +
			" '"+ user.getEmail() +"'," +
			" '"+ user.getPassword() +"'," +
			" "+ user.getGender() +"," +
			" "+ (user.getFirstVisit()? 1: 0) +"," +
			" "+ (user.getIsAdmin() ? 1 : 0) +"," +
			" NULL, NULL, NULL)";
		System.out.println(request);
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ps.executeUpdate();
		Connect.getConnection().close();
	}
	
	//Update
	public static void update(User user) throws SQLException{
		String request = "UPDATE User SET" +
			" firstname='"+ user.getFirstname() +"'," +
			" lastname='"+ user.getLastname() +"'," +
			" age="+ user.getAge() +"," +
			" email='"+ user.getEmail() +"'," +
			" password='"+ user.getPassword() +"'," +
			" gender="+ user.getGender() +"," +
			" firstVisit="+ (user.getFirstVisit()? 1: 0) +"," +
			" isAdmin="+ (user.getIsAdmin()? 1: 0);
			if (user.getLastPosition() != null) {
			request += "," +
			" lastPosition=?," +
			" lastLatitude="+ user.getLastLat() +"," +
			" lastLongitude="+ user.getLastLong();
			}
			request += " WHERE id="+ user.getId();
		System.out.println(request);
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ps.setDate(1, new java.sql.Date(user.getLastPosition().getTime()));
		ps.executeUpdate();
		Connect.getConnection().close();
	}
	
	//Delete
	public static void delete(User user) throws SQLException{
		String request = "DELETE FROM User WHERE id="+ user.getId();
		System.out.println(request);
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ps.executeUpdate();
		Connect.getConnection().close();
	}

	//All
	public static List<User> getAll() throws SQLException {
		String request = "SELECT * FROM User";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createUsers(result);
	}
	
	//Get UserId by email
	public static int getIdByEmail(String email) throws SQLException {
		String request = "SELECT id FROM User WHERE email = '"+email+"'";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		return (result != null && result.next()) ? result.getInt("id") : null;
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
				users.add(new User(result.getInt("id"), result.getString("firstname"), result.getString("lastname"), result.getInt("age"), result.getString("email"), result.getInt("gender"), result.getBoolean("firstVisit"), result.getBoolean("isAdmin")));	
		} catch (SQLException e) { e.printStackTrace();	}
		return users;
	}
	
	//Create User
	private static User createUser(ResultSet result) {
		User user = new User();

		try {
			if(result != null && result.next()){ 
				user = new User(
					result.getInt("id"),
					result.getString("firstname"), 
					result.getString("lastname"),
					result.getInt("age"),
					result.getString("email"),
					result.getString("password"),
					result.getInt("gender"),
					result.getBoolean("firstVisit"),
					result.getBoolean("isAdmin"),
					result.getDate("lastPosition"),
					result.getDouble("lastLatitude"),
					result.getDouble("lastLongitude")
				);	
			} else{
				return null;
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
					result.getInt("age"),
					result.getString("email"),
					result.getString("password"),
					result.getInt("gender"),
					result.getBoolean("firstVisit"),
					result.getBoolean("isAdmin"),
					result.getDate("lastPosition"),
					result.getDouble("lastLatitude"),
					result.getDouble("lastLongitude")
				));	
			}
		} catch (SQLException e) { e.printStackTrace();	}
		return users;
	}
	
	
	//MAIN
	public static void main(String[] args)  {
		try {
			//One
			User u = getUser(1);
			System.out.println(u.getId()+" "+u.getLastname()+" "+u.getFirstname()+"\n");
			
			//All
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
			
			//Insert
			User u1 = new User(0, "Truc", "Machin", 30, "test@msn.com", "haha", 0, true, false);
//			insert(u1);
			
			//Update
			u1.setId(getIdByEmail(u1.getEmail()));
			u1.setAge(21);
			u1.setEmail("romain@mail.com");
			u1.setPassword("hihi");
			u1.setGender(1);
			u1.setIsAdmin(true);
			u1.setFirstVisit(false);
			u1.setLastPosition(new Date());
			update(u1);
			
			//Delete 
//			delete(u1);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
