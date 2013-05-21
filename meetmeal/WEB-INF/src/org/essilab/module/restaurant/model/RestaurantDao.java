package org.essilab.module.restaurant.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.essilab.module.category.model.Category;
import org.essilab.module.connect.Connect;
import org.essilab.module.user.model.User;

public class RestaurantDao {

	//One
	public static Restaurant getRestaurant(int restoid) throws SQLException {
		String request = "SELECT * FROM Restaurant R "+
			"INNER JOIN Category C ON R.categoryId = C.id "+
			"WHERE R.id = "+restoid;
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createRestaurant(result);
	}
	
	//All
	public static List<Restaurant> getAll() throws SQLException {
		String request = "SELECT * FROM Restaurant";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createRestaurants(result);
	}
	
	//Create Category
	private static Restaurant createRestaurant(ResultSet result) {
		Restaurant resto = new Restaurant();
		try {
			result.next(); 
			if (result != null) {
				resto = new Restaurant(result.getInt("id"), result.getString("name"), result.getLong("latitude"), result.getLong("longitude"), result.getString("foursquareId"), result.getInt("partnership"), result.getString("urlImage"), result.getString("titleImage"), result.getString("descImage"));	
				resto.setCategory(new Category(result.getInt("id"), result.getString("foursquareId"), result.getString("name")));
			}
		} catch (SQLException e) { e.printStackTrace();	}
		return resto;
	}
		
	//Create Categories
	private static List<Restaurant> createRestaurants(ResultSet result) {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		try {
			while (result.next()) {
				Restaurant resto = new Restaurant(result.getInt("id"), result.getString("name"), result.getLong("latitude"), result.getLong("longitude"), result.getString("foursquareId"), result.getInt("partnership"), result.getString("urlImage"), result.getString("titleImage"), result.getString("descImage"));	
				resto.setCategory(new Category(result.getInt("id"), result.getString("foursquareId"), result.getString("name")));
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
			System.out.println(r.getId()+" "+r.getName()+" "+r.getPartnership()+" "+r.getTitleImage()+"\n");
			
			//All
			List<Restaurant> items = getAll();
			System.out.println("Nb Restaurants = "+ items.size());
			for (Restaurant i : items) 
				System.out.println(i.getId()+" "+i.getName()+" "+i.getPartnership()+" "+i.getTitleImage());
			System.out.println("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
