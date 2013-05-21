package org.essilab.module.interest;

import java.sql.SQLException;
import java.util.List;

import org.essilab.module.announce.AnnounceService;
import org.essilab.module.announce.model.Announce;
import org.essilab.module.announce.model.AnnounceDao;
import org.essilab.module.interest.model.Interest;
import org.essilab.module.interest.model.InterestDao;
import org.essilab.module.user.model.User;
import org.essilab.module.user.model.UserDao;

public class InterestService {
	private static InterestService instance = null;
	private InterestService() { }
	public static InterestService getInstance(){
		if ( null == instance)
			instance = new InterestService();
		return instance;
	}
	
	public static List<Interest> getInterestsByUser(User u){
		try {
			 return InterestDao.findInterestsUser(u.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
