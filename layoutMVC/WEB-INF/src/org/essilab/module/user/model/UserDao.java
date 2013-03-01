package org.essilab.module.user.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractUserDao{

	public static UserDao getInstance() {
		
		return new UserDao();
	}

	PreparedStatement psFindAll = null;
	public List<User> findAll() throws SQLException {
		
		if (null == psFindAll) {
			psFindAll = connection.prepareStatement("SELECT id,firstname,lastname,lastLongitude,lastLatitude FROM user");
		}
		ResultSet result = psFindAll.executeQuery();
		System.out.println(result.getMetaData().getColumnName(1));
		if (null != result) {
			List<User> users = new ArrayList<>();

			while (result.next()) {
			  users.add(new User(
					  result.getLong("id"),
					  result.getString("firstname"), 
					  result.getString("lastname"), 
					  result.getFloat("lastLatitude"), 
					  result.getFloat("lastLongitude")));

			}
			return users;
		}
		return null;
	}
	
	public static void main(String[] args)  {
		try {
			List<User> users = getInstance().findAll();
			/*for (User u : users) {
				System.out.println(u.login);
			}*/
			System.out.println(users.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	PreparedStatement psInsert;
	/*public void insert(User u) throws SQLException {
		if (null == psInsert) {
				psInsert = connection.prepareStatement("INSERT INTO users(login,password) VALUES(?,?)");
		}
		psInsert.setString(1, u.login);
		psInsert.setString(2, u.password);
		
		psInsert.executeUpdate();
	}*/

}
