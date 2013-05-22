package org.essilab.module.category;

import java.sql.SQLException;
import java.util.List;

import org.essilab.module.category.model.Category;
import org.essilab.module.category.model.CategoryDao;
import org.essilab.module.user.model.User;

public class CategoryService {

	private static CategoryService instance = null;
	private CategoryService() { }
	public static CategoryService getInstance(){
		if ( null == instance)
			instance = new CategoryService();
		return instance;
	}
	
	public List<Category> CategoryList() {
		try {
			return CategoryDao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Category> getCategoryByUser(User u){
		try {
			 return CategoryDao.findCategoriesUser(u.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
