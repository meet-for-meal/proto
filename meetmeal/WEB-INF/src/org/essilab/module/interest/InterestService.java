package org.essilab.module.interest;

import java.sql.SQLException;
import java.util.List;

import org.essilab.module.interest.model.Interest;
import org.essilab.module.interest.model.InterestDao;
import org.essilab.module.user.model.User;

public class InterestService {

	private static InterestService instance = null;
	private InterestService() { }
	public static InterestService getInstance(){
		if ( null == instance)
			instance = new InterestService();
		return instance;
	}
	
	public List<Interest> interestList() {
		try {
			return InterestDao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Interest interestSelect(int id) {
		try {
			return InterestDao.getInterest(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean interestInsert(Interest interest) {
		try {
			return InterestDao.insert(interest);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean interestInsertByUser(int userid, int interestid) {
		try {
			return InterestDao.insertByUser(userid, interestid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean interestDelete(int id) {
		try {
			return InterestDao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean interestDeleteByUser(int userid) {
		try {
			return InterestDao.deleteByUser(userid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static Interest getInterestByTag(String s){
		try {
			 return InterestDao.getInterestByTag(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Interest> getInterestByUser(User u){
		try {
			 return  InterestDao.findInterestsUser(u.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
