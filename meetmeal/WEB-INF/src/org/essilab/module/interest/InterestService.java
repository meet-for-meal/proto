package org.essilab.module.interest;

import java.sql.SQLException;
import java.util.List;

import org.essilab.module.interest.model.Interest;
import org.essilab.module.interest.model.InterestDao;

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
	
	
	public static Interest getInterestByTag(String s){
		try {
			 return InterestDao.getInterestByTag(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
