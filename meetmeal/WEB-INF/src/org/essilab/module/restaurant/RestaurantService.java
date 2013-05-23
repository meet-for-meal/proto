package org.essilab.module.restaurant;

import java.sql.SQLException;
import java.util.List;

import org.essilab.module.restaurant.model.Restaurant;
import org.essilab.module.restaurant.model.RestaurantDao;

public class RestaurantService {

	//UserDao userDao = UserDao.getInstance();
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
	
	
}
