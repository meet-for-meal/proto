package org.essilab.module.invitation.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.essilab.module.announce.model.AnnounceDao;
import org.essilab.module.connect.Connect;
import org.essilab.module.user.model.UserDao;

public class InvitationDao {
	
	//One
	public static Invitation getInvitation(int invitid) throws SQLException {
		String request = "SELECT id, senderId, announceId, createdDate, isAccepted, isConfirmed, isOpen, message " +
			"FROM Invitation " +
			"WHERE id = "+invitid;
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createInvitation(result);
	}
		
	//Insert
	public static boolean insert(Invitation i) throws SQLException{
		boolean ok = false;
		if (i.getAnnounce() != null && i.getSender() != null) {
			if (getIdBySameInvitation(i.getSender().getId(), i.getCreatedDate(), i.getAnnounce().getId()) <= 0) {
				String request = "INSERT INTO Invitation VALUES (NULL" +
					", "+ (i.getSender() != null ? i.getSender().getId()+"" : "NULL") +
					", "+ (i.getCreatedDate() != null ? "'"+new java.sql.Date(i.getCreatedDate().getTime())+" "+new java.sql.Time(i.getCreatedDate().getTime())+"'" : "NULL") +
					", "+ (i.getIsAccepted()? 1: 0) +
					", "+ (i.getIsConfirmed()? 1: 0) +
					", "+ (i.getIsOpen()? 1: 0) +"," +
					" '"+ i.getMessage() +"')";
				System.out.println(request);
				PreparedStatement ps = Connect.getConnection().prepareStatement(request);
				ps.executeUpdate();
				if (getIdBySameInvitation(i.getSender().getId(), i.getCreatedDate(), i.getAnnounce().getId()) > 0)
					ok = true;
				Connect.getConnection().close();
			}
		}
		return ok;
	}
	
	//Update
	public static boolean update(Invitation i) throws SQLException{
		boolean ok = false;
		if (i.getId() > 0) {
			String request = "UPDATE Invitation SET";
				if (i.getSender() != null)
				request += " senderId="+ i.getSender().getId();
				if (i.getCreatedDate() != null)
				request += ", createdDate='"+ new java.sql.Date(i.getCreatedDate().getTime())+" "+new java.sql.Time(i.getCreatedDate().getTime())+"'";
				request += ", isAccepted="+ (i.getIsAccepted()? 1: 0) +"," +
				" isConfirmed="+ (i.getIsConfirmed()? 1: 0) +"," +
				" isOpen="+ (i.getIsOpen()? 1: 0) +"," +
				" message='"+ i.getMessage() +"'" +
				" WHERE id="+ i.getId();
			System.out.println(request);
			PreparedStatement ps = Connect.getConnection().prepareStatement(request);
			ps.executeUpdate();
			Connect.getConnection().close();
			ok = true;
		}
		return ok;
	}
	
	//Delete
	public static boolean delete(int id) throws SQLException{
		boolean ok = false;
		if (getInvitation(id) != null) {
			String request = "DELETE FROM Invitation WHERE id="+ id;
			System.out.println(request);
			PreparedStatement ps = Connect.getConnection().prepareStatement(request);
			ps.executeUpdate();
			if (getInvitation(id) == null) 
				ok = true;
			Connect.getConnection().close();
		}
		return ok;
	}
	
	//Get InvitationId by createdDate and senderId
	public static int getIdBySameInvitation(int senderId, Date createdDate, int announceId) throws SQLException {
		String request = "SELECT id FROM Invitation " +
				"WHERE senderId = "+senderId+" " +
				"AND createdDate = '"+ new java.sql.Date(createdDate.getTime())+" "+ new java.sql.Time(createdDate.getTime())+"' " +
				"AND announceId = "+ announceId +";";
		System.out.println(request);
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		return (result != null && result.next()) ? result.getInt("id") : 0;
	}
	
	//Invitations by User
	public static List<Invitation> findInvitationsByUserid(int userId) throws SQLException {
		String request = "SELECT id, senderId, announceId, createdDate, isAccepted, isConfirmed, isOpen, message " +
				"FROM Invitation I " +
				"WHERE senderId = "+userId;
		System.out.println(request);
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createInvitations(result);
	}
	
	//Invitations by Announce
	public static List<Invitation> findInvitationsByAnnounceid(int announceId) throws SQLException {
		String request = "SELECT id, senderId, announceId, createdDate, isAccepted, isConfirmed, isOpen, message " +
				"FROM Invitation I " +
				"WHERE announceId = "+announceId;
		System.out.println(request);
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createInvitations(result);
	}
	
	
	//Create Invitation
	private static Invitation createInvitation(ResultSet result) {
		Invitation invit = new Invitation();
		try {
			result.next(); 
			if (result != null) {
				invit = new Invitation(
						result.getInt("id"),
						result.getTimestamp("createdDate"), 
						result.getBoolean("isAccepted"),
						result.getBoolean("isConfirmed"),
						result.getBoolean("isOpen"),
//						result.getDouble("latitude"),
//						result.getDouble("longitude"),
						result.getString("message") );
				invit.setSender(UserDao.getUser(result.getInt("senderId")));
				invit.setAnnounce(AnnounceDao.getAnnounce(result.getInt("announceId")));
			}
		} catch (SQLException e) { e.printStackTrace();	}
		return invit;
	}
		
	//Create Invitations
	private static List<Invitation> createInvitations(ResultSet result) {
		List<Invitation> invits = new ArrayList<Invitation>();
		try {
			while (result.next()) {
				Invitation invit = new Invitation(
						result.getInt("id"),
						result.getTimestamp("createdDate"), 
						result.getBoolean("isAccepted"),
						result.getBoolean("isConfirmed"),
						result.getBoolean("isOpen"),
//						result.getDouble("latitude"),
//						result.getDouble("longitude"),
						result.getString("message") );
				invit.setSender(UserDao.getUser(result.getInt("senderId")));
				invit.setAnnounce(AnnounceDao.getAnnounce(result.getInt("announceId")));
				invits.add(invit);	
			}
		} catch (SQLException e) { e.printStackTrace();	}
		return invits;
	}
	
	
	//MAIN TEST
	public static void main(String[] args)  {
		try {
			//One
			Invitation item = getInvitation(1);
			System.out.println(item.getCreatedDate()+" "+item.getSender().getFirstname()+" "+item.getMessage()+" "+item.getIsAccepted()+"\n");
			
			
			
			//Insert
			Invitation i1 = new Invitation(0, new Date(), true, true, false, "Viens, viens, regarde comme on est bien");
			i1.setSender(UserDao.getUser(4));
			i1.setAnnounce(AnnounceDao.getAnnounce(1));
			insert(i1);
			
			//Update
			i1.setId(getIdBySameInvitation(4, i1.getCreatedDate(), i1.getAnnounce().getId()));
			i1.setIsOpen(false);
			i1.setIsConfirmed(true);
			i1.setMessage("Perdu");
			i1.setCreatedDate(new Date());
			update(i1);
			
			//Delete 
			delete(i1.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
