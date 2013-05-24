package org.essilab.module.user;

import java.sql.SQLException;
import java.util.Date;
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
	
	public List<User> userFriends(User u) {
		try {
			return UserDao.findFriends(u.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User userSelect(int id) {
		try {
			return UserDao.getUser(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean userInsert(User u){
		try {
			return UserDao.insert(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean userUpdate(User u){
		try {
			return UserDao.update(u, false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean userUpdateLocalization(int id, Date lastPos, double lat, double lng){
		try {
			return UserDao.updateLocalization(id, lastPos, lat, lng);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean userDelete(int id) {
		try {
			return UserDao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
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
			 UserDao.update(u, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean friendInsert(int userId, int friendId){
		try {
			return UserDao.insertFriend(userId, friendId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean friendDelete(int userId, int friendId) {
		try {
			return UserDao.deleteFriend(userId,friendId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
