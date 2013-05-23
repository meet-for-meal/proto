package org.essilab.module.announce;

import java.sql.SQLException;
import java.util.List;

import org.essilab.module.announce.model.Announce;
import org.essilab.module.announce.model.AnnounceDao;

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
	
	public boolean announceDelete(int id) {
		try {
			return AnnounceDao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
