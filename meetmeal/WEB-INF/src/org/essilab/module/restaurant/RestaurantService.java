package org.essilab.module.restaurant;

import java.sql.SQLException;
import java.util.List;

import org.essilab.module.restaurant.model.Restaurant;
import org.essilab.module.restaurant.model.RestaurantDao;

public class RestaurantService {

	private static RestaurantService instance = null;
	private RestaurantService() { }
	public static RestaurantService getInstance(){
		if ( null == instance)
			instance = new RestaurantService();
		return instance;
	}
	
	public List<Restaurant> restaurantList() {
		try {
			return RestaurantDao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Restaurant restaurantSelect(int id) {
		try {
			return RestaurantDao.getRestaurant(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean restaurantInsert(Restaurant r){
		try {
			return RestaurantDao.insert(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean restaurantDelete(int id) {
		try {
			return RestaurantDao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
