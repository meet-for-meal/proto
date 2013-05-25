package org.essilab.module.announce.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.essilab.module.connect.Connect;
import org.essilab.module.user.model.UserDao;

public class AnnounceDao {
	
	//One
	public static Announce getAnnounce(int announceid) throws SQLException {
		String request = "SELECT A.id, A.creatorId, A.createdDate, A.disponibilityDate, A.isOpen, A.latitude, A.longitude, A.message " +
			"FROM Announce A " +
			"WHERE A.id = "+announceid;
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createAnnounce(result);
	}
		
	//Insert
	public static boolean insert(Announce a) throws SQLException{
		boolean ok = false;

		if (a != null && a.getCreator() != null) {
			System.out.println("in_request");
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
			if (getIdByCreatoridCreatedDate(a.getCreator().getId(), a.getCreatedDate()) > 0)
				ok = true;
		}
		return ok;
	}
	
	//Update
	public static boolean update(Announce a) throws SQLException{
		boolean ok = false;
		if (a.getCreator() != null) {
			String request = "UPDATE Announce SET";
//				if (a.getCreator() != null)
//				request += " creatorId="+ a.getCreator().getId();
				if (a.getCreatedDate() != null)
				request += " createdDate='"+ new java.sql.Date(a.getCreatedDate().getTime())+" "+new java.sql.Time(a.getCreatedDate().getTime())+"'";
				if (a.getDisponibilityDate() != null)
				request += ", disponibilityDate='"+ new java.sql.Date(a.getDisponibilityDate().getTime())+" "+new java.sql.Time(a.getDisponibilityDate().getTime()) +"'";
				request += ", isOpen="+ (a.getIsOpen()? 1: 0) +"," +
				" latitude="+ a.getLatitude() +"," +
				" longitude="+ a.getLongitude() +"," +
				" message=\""+ a.getMessage() +"\"" +
				" WHERE id="+ a.getId();
			System.out.println(request);
			PreparedStatement ps = Connect.getConnection().prepareStatement(request);
			ps.executeUpdate();
			Connect.getConnection().close();
			if (getIdByCreatoridCreatedDate(a.getCreator().getId(), a.getCreatedDate()) > 0)
				ok = true;
		}
		return ok;
	}
	
	//Delete
	public static boolean delete(int announceId) throws SQLException{
		boolean ok = false;
		if (getAnnounce(announceId) != null) {
			String request = "DELETE FROM Announce WHERE id="+ announceId;
			System.out.println(request);
			PreparedStatement ps = Connect.getConnection().prepareStatement(request);
			ps.executeUpdate();
			if (getAnnounce(announceId) == null) 
				ok = true;
			Connect.getConnection().close();
		}
		return ok;
	}
	
	//Get AnnounceId by createdDate and creatorId
	public static int getIdByCreatoridCreatedDate(int creatorId, Date createdDate) throws SQLException {
		String request = "SELECT id FROM Announce WHERE creatorId = "+creatorId+" AND createdDate = '"+ new java.sql.Date(createdDate.getTime())+" "+ new java.sql.Time(createdDate.getTime())+"';";
		System.out.println(request);
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		return (result != null && result.next()) ? result.getInt("id") : 0;
	}
	
	//Get AnnounceId and creatorId
	public static int getIdByCreatorId(int creatorId) throws SQLException {
		String request = "SELECT id FROM Announce WHERE creatorId = "+creatorId+" AND isOpen = 1";
		System.out.println(request);
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		return (result != null && result.next()) ? result.getInt("id") : 0;
	}
	
	
	//All
	public static List<Announce> getAll() throws SQLException {
		String request = "SELECT A.id, A.creatorId, A.createdDate, A.disponibilityDate, A.isOpen, A.latitude, A.longitude, A.message "+
			"FROM Announce A " +
			"INNER JOIN User U ON U.id = A.creatorId";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createAnnounces(result);
	}
	
	//Announces with same Interests
	public static List<Announce> findAnnouncesByInterests(List<Integer> interests) throws SQLException {
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
	public static List<Announce> findNearAnnouncesByInterests(int userId, List<Integer> interests, int distance) throws SQLException {
		HashMap<Integer, Announce> announcesMap = new HashMap<Integer, Announce>();
		
		for (Integer interest : interests) {
			String request = "CALL nearAnnouncesByInterests("+userId+","+ interest.intValue() +", "+distance+")";

			System.out.println(request);
			PreparedStatement ps = Connect.getConnection().prepareStatement(request);
			ResultSet result = ps.executeQuery();
			
			List<Announce> tmp = createAnnounces(result);
			for(Announce a : tmp) {
				announcesMap.put(a.getId(), a);
			}
			System.out.println(tmp.size()+"announces for "+interest);
		}
		Connect.getConnection().close();
		
		return new ArrayList<Announce>(announcesMap.values());
	}
	
	
	//Create Announce
	private static Announce createAnnounce(ResultSet result) {
		Announce announce = new Announce();
		try {
			if (result != null && result.next()) {
				announce = new Announce(
						result.getInt("id"),
						result.getTimestamp("createdDate"), 
						result.getTimestamp("disponibilityDate"),
						result.getBoolean("isOpen"),
						result.getDouble("latitude"),
						result.getDouble("longitude"),
						result.getString("message") );
				announce.setCreator(UserDao.getUser(result.getInt("creatorId")));
			} else
				return null;
		} catch (SQLException e) { e.printStackTrace();	}
		return announce;
	}
		
	//Create Announces
	private static List<Announce> createAnnounces(ResultSet result) {
		List<Announce> announces = new ArrayList<Announce>();
		try {
			while (result.next()) {
				Announce an = new Announce(
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
			Announce item = getAnnounce(1);
			System.out.println(item.getCreatedDate()+" "+item.getCreator().getFirstname()+" "+item.getMessage()+" "+item.getDisponibilityDate()+"\n");
			
			//All
			List<Announce> items = getAll();
			for (Announce i : items) 
				System.out.println(i.getCreatedDate()+" "+i.getCreator().getFirstname()+" "+i.getMessage()+" "+i.getDisponibilityDate());
			System.out.println(items.size()+"\n");
		
			//Announce same Users
			System.out.println("Announce same Users");
			List<Integer> interests = new ArrayList<Integer>();
			interests.add(Integer.valueOf(1));
			interests.add(Integer.valueOf(4));
			interests.add(Integer.valueOf(8));
			items = findAnnouncesByInterests(interests);
			for (Announce i : items) 
				System.out.println(i.getId()+" "+i.getCreatedDate()+" "+i.getCreator().getFirstname()+" "+i.getMessage()+" "+i.getDisponibilityDate());
			System.out.println(items.size()+"\n");
			
			//Near Announce same Users
			System.out.println("Near Announce same Users");
			items = findNearAnnouncesByInterests(3, interests, 5);
			for (Announce i : items) 
				System.out.println(i.getCreatedDate()+" "+i.getCreator().getFirstname()+" "+i.getMessage()+" "+i.getDisponibilityDate());
			System.out.println(items.size()+"\n");
			
			//Insert
			Announce a1 = new Announce(0, new Date(), new Date(), true, (long)48.1021, 2.5292, "Viens, viens, regarde comme on est bien");
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
			delete(a1.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
