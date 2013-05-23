package org.essilab.module.restaurant.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.essilab.module.category.model.Category;
import org.essilab.module.category.model.CategoryDao;
import org.essilab.module.connect.Connect;

public class RestaurantDao {

	//One
	public static Restaurant getRestaurant(int restoid) throws SQLException {
		String request = "SELECT R.* FROM Restaurant R "+
			"WHERE R.id = "+restoid;
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createRestaurant(result);
	}
	
	//Insert
	public static boolean insert(Restaurant r) throws SQLException{
		boolean ok = false;
		if (getIdByFoursquareId(r.getFoursquareId()) > 0) {
			String request = "INSERT INTO Restaurant VALUES (NULL, " +
				" '"+ r.getName() +"'," +
				" "+ r.getLatitude() +"," +
				" "+ r.getLongitude() +"," +
				" "+ ((r.getCategory() != null) ? r.getCategory().getId() : "NULL") +"," +
				" '"+ r.getFoursquareId() +"'," +
				" "+ r.getPartnership() +"," +
				" '"+ r.getUrlImage() +"'," +
				" '"+ r.getTitleImage() +"'," +
				" '"+ r.getDescImage() +"')";
			System.out.println(request);
			PreparedStatement ps = Connect.getConnection().prepareStatement(request);
			ps.executeUpdate();
			Connect.getConnection().close();
			if (getIdByFoursquareId(r.getFoursquareId()) > 0)
				ok = true;
		}
		return ok;
	}
	
	//Update
	public static boolean update(Restaurant resto) throws SQLException{
		boolean ok = false;
		if (getIdByFoursquareId(resto.getFoursquareId()) > 0) {
			String request = "UPDATE Restaurant SET" +
				" name='"+ resto.getName() +"'," +
				" latitude="+ resto.getLatitude() +"," +
				" longitude="+ resto.getLongitude() +"," +
				" "+ ((resto.getCategory() != null) ? resto.getCategory().getId() : "NULL") +"," +
				" foursquareId='"+ resto.getFoursquareId() +"'," +
				" partnership="+ resto.getPartnership() +"," +
				" urlImage='"+ resto.getUrlImage() +"'," +
				" titleImage='"+ resto.getTitleImage()+"'," +
				" descImage='" + resto.getDescImage()+"'" +
				" WHERE id="+ resto.getId() +";";
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
		if (getRestaurant(id) != null) {
			String request = "DELETE FROM Restaurant WHERE id="+ id;
			System.out.println(request);
			PreparedStatement ps = Connect.getConnection().prepareStatement(request);
			ps.executeUpdate();
			Connect.getConnection().close();
			if (getRestaurant(id) == null) 
				ok = true;
			Connect.getConnection().close();
		}
		return ok;
	}
	
	//Get RestaurantId by foursquareId
	public static int getIdByFoursquareId(String fsqId) throws SQLException {
		String request = "SELECT id FROM Restaurant WHERE foursquareId = '"+fsqId+"'";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		return (result != null && result.next()) ? result.getInt("id") : null;
	}
	
	//All
	public static List<Restaurant> getAll() throws SQLException {
		String request = "SELECT *FROM Restaurant";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createRestaurants(result);
	}
	
	//Create Category
	private static Restaurant createRestaurant(ResultSet result) {
		Restaurant resto = new Restaurant();
		try {
			if (result != null && result.next()) {
				resto = new Restaurant(result.getInt("id"), result.getString("name"), result.getLong("latitude"), result.getLong("longitude"), result.getString("foursquareId"), result.getInt("partnership"), result.getString("urlImage"), result.getString("titleImage"), result.getString("descImage"));	
				resto.setCategory(CategoryDao.getCategory(result.getInt("categoryId")));		
			} else
				return null;
		} catch (SQLException e) { e.printStackTrace();	}
		return resto;
	}
		
	//Create Categories
	private static List<Restaurant> createRestaurants(ResultSet result) {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		try {
			while (result.next()) {
				Restaurant resto = new Restaurant(result.getInt("id"), result.getString("name"), result.getLong("latitude"), result.getLong("longitude"), result.getString("foursquareId"), result.getInt("partnership"), result.getString("urlImage"), result.getString("titleImage"), result.getString("descImage"));	
				resto.setCategory(CategoryDao.getCategory(result.getInt("categoryId")));
				restaurants.add(resto);	
			}
		} catch (SQLException e) { e.printStackTrace();	}
		return restaurants;
	}
	
	
	//MAIN
	public static void main(String[] args)  {
		try {
			//One
			Restaurant r = getRestaurant(1);
			System.out.println(r.getId()+" "+r.getName()+" "+r.getPartnership()+" "+r.getTitleImage()+" "+r.getCategory().getName()+" "+"\n");
			
			//All
			List<Restaurant> items = getAll();
			System.out.println("Nb Restaurants = "+ items.size());
			for (Restaurant i : items) 
				System.out.println(i.getId()+" "+i.getName()+" "+i.getPartnership()+" "+i.getTitleImage());
			System.out.println("");
			
			//Insert
			Restaurant r1 = new Restaurant(0, "SUSHI DE OUF", (long)48.202, (long)2.643, "402fD22sdzc30o", 1, new Category(10, "FFOZN29D","Restaurant japonais"), "", "aucun", "");
			insert(r1);
			
			//Update
			r1.setId(getIdByFoursquareId("402fD22sdzc30o"));
			r1.setName("Sushi de Ouf");
			r1.setLongitude((long)2.9321);
			r1.setPartnership(2);
			r1.setUrlImage("http://gogole.com/image.jpg");
			r1.setTitleImage("imagechat");
			update(r1);
			
			//Delete 
			delete(r1.getId());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
