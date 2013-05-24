package org.essilab.module.invitation;

import java.sql.SQLException;
import java.util.List;

import org.essilab.module.invitation.model.Invitation;
import org.essilab.module.invitation.model.InvitationDao;

public class InvitationService {

	private static InvitationService instance = null;
	private InvitationService() { }
	public static InvitationService getInstance(){
		if ( null == instance)
			instance = new InvitationService();
		return instance;
	}

	public Invitation invitationSelect(int id) {
		try {
			return InvitationDao.getInvitation(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Invitation> findInvitationsByUserid(int id){
		try{
			return InvitationDao.findInvitationsByUserid(id);
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean invitationInsert(Invitation a){
		try {
			return InvitationDao.insert(a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Invitation findInvitationsByAnnounceid(int id_announce){
		try {
			return InvitationDao.findInvitationsByAnnounceid(id_announce);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean invitationUpdate(Invitation i){
		try {
			return InvitationDao.update(i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean invitationDelete(int id) {
		try {
			return InvitationDao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
