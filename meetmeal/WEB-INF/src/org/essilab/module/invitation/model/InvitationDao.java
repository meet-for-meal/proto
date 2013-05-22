package org.essilab.module.invitation.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.essilab.module.connect.Connect;
import org.essilab.module.user.model.User;
import org.essilab.module.user.model.UserDao;

public class InvitationDao {
	
	//One
	public static Invitation getAnnounce(int announceid) throws SQLException {
		String request = "SELECT A.id, A.creatorId, A.createdDate, A.disponibilityDate, A.isOpen, A.latitude, A.longitude, A.message " +
			"FROM Announce A " +
			"WHERE A.id = "+announceid;
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createAnnounce(result);
	}
		
	//Insert
	public static void insert(Invitation a) throws SQLException{
		String request = "INSERT INTO Announce VALUES (NULL" +
			", "+ (a.getCreator() != null ? a.getCreator().getId()+"" : "NULL") +
			", "+ (a.getCreatedDate() != null ? "'"+new java.sql.Date(a.getCreatedDate().getTime())+" "+new java.sql.Time(a.getCreatedDate().getTime())+"'" : "NULL") +
			", "+ (a.getDisponibilityDate() != null ? "'"+new java.sql.Date(a.getDisponibilityDate().getTime())+" "+new java.sql.Time(a.getDisponibilityDate().getTime())+"'" : "NULL") +
			", "+ (a.getIsOpen()? 1: 0) +"," +
			" "+ a.getLatitude() +"," +
			" "+ a.getLongitude() +"," +
			" '"+ a.getMessage() +"')";
		System.out.println(request);
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ps.executeUpdate();
		Connect.getConnection().close();
	}
	
	//Update
	public static void update(Invitation a) throws SQLException{
		String request = "UPDATE Announce SET";
			if (a.getCreator() != null)
			request += " creatorId="+ a.getCreator().getId();
			if (a.getCreatedDate() != null)
			request += ", createdDate='"+ new java.sql.Date(a.getCreatedDate().getTime())+" "+new java.sql.Time(a.getCreatedDate().getTime())+"'";
			if (a.getDisponibilityDate() != null)
			request += ", disponibilityDate='"+ new java.sql.Date(a.getDisponibilityDate().getTime())+" "+new java.sql.Time(a.getDisponibilityDate().getTime()) +"'";
			request += ", isOpen="+ (a.getIsOpen()? 1: 0) +"," +
			" latitude="+ a.getLatitude() +"," +
			" longitude="+ a.getLongitude() +"," +
			" message='"+ a.getMessage() +"'" +
			" WHERE id="+ a.getId();
		System.out.println(request);
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ps.executeUpdate();
		Connect.getConnection().close();
	}
	
	//Delete
	public static void delete(Invitation a) throws SQLException{
		String request = "DELETE FROM Announce WHERE id="+ a.getId();
		System.out.println(request);
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ps.executeUpdate();
		Connect.getConnection().close();
	}
	
	//Get AnnounceId by createdDate and creatorId
	public static int getIdByCreatoridCreatedDate(int creatorId, Date createdDate) throws SQLException {
		String request = "SELECT id FROM Announce WHERE creatorId = "+creatorId+" AND createdDate = '"+ new java.sql.Date(createdDate.getTime())+" "+ new java.sql.Time(createdDate.getTime())+"';";
		System.out.println(request);
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		return (result != null && result.next()) ? result.getInt("id") : null;
	}
	
	//All
	public static List<Invitation> getAll() throws SQLException {
		String request = "SELECT A.id, A.creatorId, A.createdDate, A.disponibilityDate, A.isOpen, A.latitude, A.longitude, A.message "+
			"FROM Announce A " +
			"INNER JOIN User U ON U.id = A.creatorId";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createAnnounces(result);
	}
	
	//Announces with same Interests
	public static List<Invitation> findAnnouncesByInterests(List<Integer> interests) throws SQLException {
		String request = "SELECT A.id, A.creatorId, A.createdDate, A.disponibilityDate, A.isOpen, A.latitude, A.longitude, A.message "+
				"FROM Announce A " +
				"INNER JOIN User U ON U.id = A.creatorId " +
				"INNER JOIN Has_Interest HI ON U.id = HI.userId " +
				"INNER JOIN Interest I ON I.id = HI.interestId ";
		if (interests != null) {
			if (interests.size() > 0)
				request += "WHERE I.id = "+interests.get(0)+" ";
			if (interests.size() > 1) {
				for(int i=1 ; i<interests.size() ; i++) 
					request += "OR I.id =  "+interests.get(0)+" ";
			}
		}
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createAnnounces(result);
	}
	
	//Near Announces with same Interests
	public static List<Invitation> findNearAnnouncesByInterests(int userId, List<Integer> interests, int distance) throws SQLException {
		HashMap<Integer, Invitation> announcesMap = new HashMap<Integer, Invitation>();
		
		for (Integer interest : interests) {
			String request = "CALL nearAnnouncesByInterests("+userId+","+ interest.intValue() +", "+distance+")";
			
			PreparedStatement ps = Connect.getConnection().prepareStatement(request);
			ResultSet result = ps.executeQuery();
			
			List<Invitation> tmp = createAnnounces(result);
			for(Invitation a : tmp) {
				announcesMap.put(a.getId(), a);
			}
		}
		Connect.getConnection().close();
		
		return new ArrayList<Invitation>(announcesMap.values());
	}
	
	
	//Create Announce
	private static Invitation createAnnounce(ResultSet result) {
		Invitation announce = new Invitation();
		try {
			result.next(); 
			if (result != null) {
				announce = new Invitation(
						result.getInt("id"),
						result.getTimestamp("createdDate"), 
						result.getTimestamp("disponibilityDate"),
						result.getBoolean("isOpen"),
						result.getDouble("latitude"),
						result.getDouble("longitude"),
						result.getString("message") );
				announce.setCreator(UserDao.getUser(result.getInt("creatorId")));
			}
		} catch (SQLException e) { e.printStackTrace();	}
		return announce;
	}
		
	//Create Announces
	private static List<Invitation> createAnnounces(ResultSet result) {
		List<Invitation> announces = new ArrayList<Invitation>();
		try {
			while (result.next()) {
				Invitation an = new Invitation(
						result.getInt("id"),
						result.getTimestamp("createdDate"), 
						result.getTimestamp("disponibilityDate"),
						result.getBoolean("isOpen"),
						result.getDouble("latitude"),
						result.getDouble("longitude"),
						result.getString("message") );
				an.setCreator(UserDao.getUser(result.getInt("creatorId")));
				announces.add(an);	
			}
		} catch (SQLException e) { e.printStackTrace();	}
		return announces;
	}
	
	
	//MAIN TEST
	public static void main(String[] args)  {
		try {
			//One
			Invitation item = getAnnounce(1);
			System.out.println(item.getCreatedDate()+" "+item.getCreator().getFirstname()+" "+item.getMessage()+" "+item.getDisponibilityDate()+"\n");
			
			//All
			List<Invitation> items = getAll();
			for (Invitation i : items) 
				System.out.println(i.getCreatedDate()+" "+i.getCreator().getFirstname()+" "+i.getMessage()+" "+i.getDisponibilityDate());
			System.out.println(items.size()+"\n");
		
			//Announce same Users
			System.out.println("Announce same Users");
			List<Integer> interests = new ArrayList<Integer>();
			interests.add(Integer.valueOf(1));
			interests.add(Integer.valueOf(4));
			interests.add(Integer.valueOf(8));
			items = findAnnouncesByInterests(interests);
			for (Invitation i : items) 
				System.out.println(i.getId()+" "+i.getCreatedDate()+" "+i.getCreator().getFirstname()+" "+i.getMessage()+" "+i.getDisponibilityDate());
			System.out.println(items.size()+"\n");
			
			//Near Announce same Users
			System.out.println("Near Announce same Users");
			items = findNearAnnouncesByInterests(3, interests, 5);
			for (Invitation i : items) 
				System.out.println(i.getCreatedDate()+" "+i.getCreator().getFirstname()+" "+i.getMessage()+" "+i.getDisponibilityDate());
			System.out.println(items.size()+"\n");
			
			//Insert
			Invitation a1 = new Invitation(0, new Date(), new Date(), true, (long)48.1021, 2.5292, "Viens, viens, regarde comme on est bien");
			a1.setCreator(UserDao.getUser(4));
			insert(a1);
			
			//Update
			a1.setId(getIdByCreatoridCreatedDate(4, a1.getCreatedDate()));
			a1.setIsOpen(false);
			a1.setLatitude(47.2192);
			a1.setMessage("Perdu");
			a1.setDisponibilityDate(new Date());
			update(a1);
			
			//Delete 
			delete(a1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}