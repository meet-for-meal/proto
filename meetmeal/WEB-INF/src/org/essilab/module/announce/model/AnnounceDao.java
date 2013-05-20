package org.essilab.module.announce.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.essilab.module.connect.Connect;
import org.essilab.module.user.model.User;

public class AnnounceDao {
	
	//One
	public static Announce getAnnounce(int announceid) throws SQLException {
		String request = "SELECT A.id, A.createdDate, A.disponibilityDate, A.isOpen, A.latitude, A.longitude, A.message, " +
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
		String request = "SELECT A.id, A.createdDate, A.disponibilityDate, A.isOpen, A.latitude, A.longitude, A.message, " +
			"U.firstname, U.lastname, U.email, U.gender, U.age "+
			"FROM Announce A " +
			"INNER JOIN User U ON U.id = A.creatorId";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createAnnounces(result);
	}
	
	
	//Announce same near Users
	public static List<Announce> findAnnouncesBySameNearUsers(List<String> interests) throws SQLException {
		String request = "SELECT A.id, A.createdDate, A.disponibilityDate, A.isOpen, A.latitude, A.longitude, A.message, " +
				"U.firstname, U.lastname, U.email, U.gender, U.age "+
				"FROM Announce A " +
				"INNER JOIN User U ON U.id = A.creatorId " +
				"INNER JOIN Has_Interest HI ON U.id = HI.userId " +
				"INNER JOIN Interest I ON I.id = HI.interestId ";
				
		if (interests != null) {
			if (interests.size() > 0)
				request += "WHERE I.tag = '"+interests.get(0)+"' ";
			if (interests.size() > 1) {
				for(int i=1 ; i<interests.size() ; i++) 
					request += "OR I.tag =  '"+interests.get(0)+"' ";
			}
		}
		request += "GROUP BY A.id";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createAnnounces(result);
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
				announce.setCreator(new User(result.getInt("id"), result.getString("firstname"), result.getString("lastname"), result.getInt("age"), result.getString("email"), result.getInt("gender")));
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
				an.setCreator(new User(result.getInt("id"), result.getString("firstname"), result.getString("lastname"), result.getInt("age"), result.getString("email"), result.getInt("gender")));
				announces.add(an);	
			}
		} catch (SQLException e) { e.printStackTrace();	}
		return announces;
	}
	
	
	//MAIN TEST
	public static void main(String[] args)  {
		try {
			//One
			System.out.println("One");
			Announce item = getAnnounce(1);
			System.out.println(item.getCreatedDate()+" "+item.getCreator().getFirstname()+" "+item.getMessage()+" "+item.getDisponibilityDate()+"\n");
			
			//All
			System.out.println("All");
			List<Announce> items = getAll();
			for (Announce i : items) 
				System.out.println(i.getCreatedDate()+" "+i.getCreator().getFirstname()+" "+i.getMessage()+" "+i.getDisponibilityDate());
			System.out.println(items.size()+"\n");
			
			//Announce same near Users
			System.out.println("Announce same near Users");
			List<String> tags = new ArrayList<String>();
			tags.add("Informatique");
			items = findAnnouncesBySameNearUsers(tags);
			for (Announce i : items) 
				System.out.println(i.getId()+" "+i.getCreatedDate()+" "+i.getCreator().getFirstname()+" "+i.getMessage()+" "+i.getDisponibilityDate());
			System.out.println(items.size()+"\n");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
