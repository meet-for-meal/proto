package org.essilab.module.interest.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.essilab.module.connect.Connect;
import org.essilab.module.user.model.User;

public class InterestDao {

	//One
	public static Interest getInterest(int interestid) throws SQLException {
		String request = "SELECT * FROM Interest WHERE id="+interestid;
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createInterest(result);
	}
	
	//Insert
	public static boolean insert(Interest i) throws SQLException{
		boolean ok = false;
		if (getInterestByTag(i.getTag()) == null) {
			String request = "INSERT INTO Interest VALUES (NULL, '"+ i.getTag() +"')";
			System.out.println(request);
			PreparedStatement ps = Connect.getConnection().prepareStatement(request);
			ps.executeUpdate();
			Connect.getConnection().close();
			if (getInterestByTag(i.getTag()) != null)
				ok = true;
		}
		return ok;
	}
	
	public static boolean insertByUser(int userid, int interestid) throws SQLException{
		boolean ok = false;
		String request = "INSERT INTO has_interest VALUES ('"+ userid +"', '"+ interestid +"')";
		System.out.println(request);
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ps.executeUpdate();
		Connect.getConnection().close();
		
		return true;
	}
	
	//Delete
	public static boolean delete(int id) throws SQLException {
		boolean ok = false;
		if (getInterest(id) != null) {
			String request = "DELETE FROM Interest WHERE id="+ id;
			System.out.println(request);
			PreparedStatement ps = Connect.getConnection().prepareStatement(request);
			ps.executeUpdate();
			if (getInterest(id) == null) 
				ok = true;
			Connect.getConnection().close();
		}
		return ok;
	}
	
	public static boolean deleteByUser(int id) throws SQLException {
		boolean ok = false;
		if (getInterest(id) != null) {
			String request = "DELETE FROM has_interest WHERE userId="+ id;
			System.out.println(request);
			PreparedStatement ps = Connect.getConnection().prepareStatement(request);
			ps.executeUpdate();
			if (getInterest(id) == null) 
				ok = true;
			Connect.getConnection().close();
		}
		return ok;
	}
	
	
	// By Tag
	public static Interest getInterestByTag(String tag) throws SQLException {
		String request = "SELECT * FROM Interest WHERE tag LIKE \""+tag+"\"";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createInterest(result);
	}
	
	
	//All
	public static List<Interest> getAll() throws SQLException {
		String request = "SELECT * FROM Interest";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createInterests(result);
	}
	
	
	//Find interests for an User
	public static List<Interest> findInterestsUser(int userid) throws SQLException {
		String request = "SELECT * FROM Interest I "+
			"INNER JOIN Has_Interest HI ON HI.interestId = I.id "+
			"WHERE userId = "+userid+";";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createInterests(result);
	}
	
	
	//Create Interest
	private static Interest createInterest(ResultSet result) {
		Interest interest = new Interest();
		try {
			if (result != null && result.next())
				interest = new Interest(result.getInt("id"), result.getString("tag"));	
			else
				return null;
		} catch (SQLException e) { e.printStackTrace();	}
		return interest;
	}
	
	//Create Interests
	private static List<Interest> createInterests(ResultSet result) {
		List<Interest> interests = new ArrayList<Interest>();
		try {
			while (result.next()) 
				interests.add(new Interest(result.getInt("id"),	result.getString("tag")));	
		} catch (SQLException e) { e.printStackTrace();	}
		return interests;
	}
	
	//MAIN TEST
	public static void main(String[] args)  {
		try {
			//All
			List<Interest> items = getAll();
			for (Interest i : items) 
				System.out.println(i.getId()+" "+i.getTag());
			System.out.println("");
			
			//User interests
			items = findInterestsUser(1);
			for (Interest i : items) 
				System.out.println(i.getId()+" "+i.getTag());
			System.out.println(items.size() +"\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
