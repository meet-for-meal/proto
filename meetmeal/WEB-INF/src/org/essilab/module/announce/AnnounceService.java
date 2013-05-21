package org.essilab.module.announce;

import java.sql.SQLException;
import java.util.List;

import org.essilab.module.announce.model.Announce;
import org.essilab.module.announce.model.AnnounceDao;
import org.essilab.module.interest.model.Interest;
import org.essilab.module.user.UserService;
import org.essilab.module.user.model.User;
import org.essilab.module.user.model.UserDao;

public class AnnounceService {
	private static AnnounceService instance = null;
	private AnnounceService() { }
	public static AnnounceService getInstance(){
		if ( null == instance)
			instance = new AnnounceService();
		return instance;
	}
	
	
	public List<Announce> getAnnounces(User u, List<Interest> interests){
		try {
			 //return AnnounceDao.FindNearAnnouncesByTags(u.getId(), interests);
			return AnnounceDao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Announce getAnnounce(int id){
		try {
			 //return AnnounceDao.FindNearAnnouncesByTags(u.getId(), interests);
			return AnnounceDao.getAnnounce(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
