package org.essilab.module.category.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.essilab.module.connect.Connect;

public class CategoryDao {

	//One
	public static Category getCategory(int categid) throws SQLException {
		String request = "SELECT * FROM Category WHERE id = "+categid;
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createCategory(result);
	}
	
	public static boolean insertByUser(int userid, int tasteid) throws SQLException{
		boolean ok = false;
		String request = "INSERT INTO Has_Taste VALUES ("+ userid +", "+ tasteid +")";
		System.out.println(request);
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ps.executeUpdate();
		Connect.getConnection().close();
		
		return true;
	}
	
	public static boolean deleteByUser(int id) throws SQLException {
		String request = "DELETE FROM Has_Taste WHERE userId="+ id;
		System.out.println(request);
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ps.executeUpdate();
		
		Connect.getConnection().close();
		return true;
	}
	
	//All
	public static List<Category> getAll() throws SQLException {
		String request = "SELECT * FROM Category";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createCategories(result);
	}
	
	//Find categories for an User
	public static List<Category> findCategoriesUser(int userid) throws SQLException {
		String request = "SELECT * FROM Category C "+
			"INNER JOIN Has_Taste HT ON HT.categoryId = C.id "+
			"WHERE userId = "+userid+";";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createCategories(result);
	}
	
	//Find category for a Restaurant
	public static Category findCategoryRestaurant(int restaurantid) throws SQLException {
		String request = "SELECT * FROM Category C "+
			"INNER JOIN Restaurant R ON R.categoryId = C.id "+
			"WHERE R.id = "+restaurantid+";";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return createCategory(result);
	}
	
	
	//Create Category
	private static Category createCategory(ResultSet result) {
		Category category = new Category();
		try {
			result.next(); 
			if (result != null)
				category = new Category(result.getInt("id"), result.getString("foursquareId"), result.getString("name"));	
		} catch (SQLException e) { e.printStackTrace();	}
		return category;
	}
	
	//Create Categories
	private static List<Category> createCategories(ResultSet result) {
		List<Category> categories = new ArrayList<Category>();
		try {
			while (result.next()) 
				categories.add(new Category(result.getInt("id"), result.getString("foursquareId"), result.getString("name")));	
		} catch (SQLException e) { e.printStackTrace();	}
		return categories;
	}
	
	//MAIN TEST
	public static void main(String[] args)  {
		try {
			Category item = getCategory(10);
			System.out.println(item.getName()+" "+item.getFoursquareId()+"\n");
			
			List<Category> items = getAll();
			for (Category i : items) 
				System.out.println(i.getName()+" "+i.getFoursquareId());
			System.out.println(items.size()+"\n");
			
			items = findCategoriesUser(1);
			for (Category i : items) 
				System.out.println(i.getName());
			System.out.println(items.size()+"\n");
			
			//TODO field categoryId in table Restaurant
//			Category item = findCategoryRestaurant(1);
//			System.out.println(item.getName()+"\n");
			
		} catch (SQLException e) { e.printStackTrace(); }
	}

}
