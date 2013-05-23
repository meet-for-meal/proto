package org.essilab.module.announce;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.essilab.module.announce.model.Announce;
import org.essilab.module.announce.model.AnnounceDao;
import org.essilab.module.interest.model.Interest;
import org.essilab.module.user.model.User;

public class AnnounceService {

	private static AnnounceService instance = null;
	private AnnounceService() { }
	public static AnnounceService getInstance(){
		if ( null == instance)
			instance = new AnnounceService();
		return instance;
	}
	
	public List<Announce> announceList() {
		try {
			return AnnounceDao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Announce> getNearAnnouncesByInterests(User u, List<Interest> interests){
		try {
			List<Integer> list = new ArrayList<Integer>();
			for(Interest in : interests){
				list.add(in.getId());
			}
			System.out.println(u.getId());
			System.out.println(list.get(0));
			//System.out.println(list.get(1));
			 return AnnounceDao.findNearAnnouncesByInterests(u.getId(), list,10);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Announce announceSelect(int id) {
		try {
			return AnnounceDao.getAnnounce(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean announceInsert(Announce a){
		try {
			return AnnounceDao.insert(a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean announceUpdate(Announce a){
		try {
			return AnnounceDao.update(a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean announceDelete(int id) {
		try {
			return AnnounceDao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
