package org.essilab.module.announce.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.essilab.module.connect.Connect;
import org.essilab.module.user.model.User;

public class AnnounceDao {
	
	//One
	public static Announce getAnnounce(int announceid) throws SQLException {
		String request = "SELECT A.id, A.creatorId, A.createdDate, A.disponibilityDate, A.isOpen, A.latitude, A.longitude, A.message, " +
			"U.firstname, U.lastname, U.email, U.gender, U.age "+
			"FROM Announce A " +
			"INNER JOIN User U ON U.id = A.creatorId " +
			"WHERE A.id = "+announceid;
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createAnnounce(result);
	}
		
	//All
	public static List<Announce> getAll() throws SQLException {
		String request = "SELECT A.id, A.creatorId, A.createdDate, A.disponibilityDate, A.isOpen, A.latitude, A.longitude, A.message, " +
			"U.firstname, U.lastname, U.email, U.gender, U.age "+
			"FROM Announce A " +
			"INNER JOIN User U ON U.id = A.creatorId";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createAnnounces(result);
	}
	
	//Announces with same Interests
	public static List<Announce> findAnnouncesByInterests(List<Integer> interests) throws SQLException {
		String request = "SELECT A.id, A.creatorId, A.createdDate, A.disponibilityDate, A.isOpen, A.latitude, A.longitude, A.message, " +
				"U.firstname, U.lastname, U.email, U.gender, U.age "+
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
	public static List<Announce> findNearAnnouncesByInterests(int userId, List<Integer> interests) throws SQLException {
		HashMap<Integer, Announce> announcesMap = new HashMap<Integer, Announce>();
		
		for (Integer interest : interests) {
			String request = "CALL nearAnnouncesByInterests("+userId+","+ interest.intValue() +", 10)";
			
			PreparedStatement ps = Connect.getConnection().prepareStatement(request);
			ResultSet result = ps.executeQuery();
			
			List<Announce> tmp = createAnnounces(result);
			for(Announce a : tmp) {
				announcesMap.put(a.getId(), a);
			}
		}
		Connect.getConnection().close();
		
		return new ArrayList<Announce>(announcesMap.values());
	}
	
	
	//Create Announce
	private static Announce createAnnounce(ResultSet result) {
		Announce announce = new Announce();
		try {
			result.next(); 
			if (result != null) {
				announce = new Announce(
						result.getInt("id"),
						result.getDate("createdDate"), 
						result.getDate("disponibilityDate"),
						result.getBoolean("isOpen"),
						result.getDouble("latitude"),
						result.getDouble("longitude"),
						result.getString("message") );
				announce.setCreator(new User(result.getInt("creatorId"), result.getString("firstname"), result.getString("lastname"), result.getInt("age"), result.getString("email"), result.getInt("gender")));
			}
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
						result.getDate("createdDate"), 
						result.getDate("disponibilityDate"),
						result.getBoolean("isOpen"),
						result.getDouble("latitude"),
						result.getDouble("longitude"),
						result.getString("message") );
				an.setCreator(new User(result.getInt("creatorId"), result.getString("firstname"), result.getString("lastname"), result.getInt("age"), result.getString("email"), result.getInt("gender")));
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
			items = findNearAnnouncesByInterests(3, interests);
			for (Announce i : items) 
				System.out.println(i.getCreatedDate()+" "+i.getCreator().getFirstname()+" "+i.getMessage()+" "+i.getDisponibilityDate());
			System.out.println(items.size()+"\n");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
