package org.essilab.module.user;

import java.sql.SQLException;
import java.util.List;

import org.essilab.module.user.model.User;
import org.essilab.module.user.model.UserDao;

public class UserService {

	//UserDao userDao = UserDao.getInstance();
	private static UserService instance = null;
	private UserService() { }
	public static UserService getInstance(){
		if ( null == instance)
			instance = new UserService();
		return instance;
	}
	
	public List<User> userList() {
		try {
			return UserDao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void userInsert(User u){
		try {
			UserDao.insert(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User userAuthenticate(User u){
		try {
			 return UserDao.authenticateUser(u.getEmail(), u.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setFirstVisit(User u, boolean isFirstTime){
		try {
			 u.setFirstVisit(isFirstTime);
			 UserDao.update(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
