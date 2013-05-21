package org.essilab.module.restaurant.model;

import org.essilab.module.category.model.Category;

public class Restaurant {

	private String name = null;
	private long latitude;
	private long longitude;
	private Category category = null;
	
	Restaurant(){}
	Restaurant(String _name, long _latitude, long _longitude) {
		name = _name;
		latitude = _latitude;
		longitude = _longitude;
	}
	Restaurant(String _name, long _latitude, long _longitude, Category _categ) {
		name = _name;
		latitude = _latitude;
		longitude = _longitude;
		category = _categ;
	}
	
	public String getName()						{ return this.name;	}
	public long getLatitude()					{ return this.latitude; }
	public long getLongitude()					{ return this.longitude; }
	public Category getCategory()				{ return this.category; }
	
	public void setName(String _name)			{ name = _name; }
	public void setLatitude(long _latitude)	{ latitude = _latitude; }
	public void setLongitude(long _longitude)	{ longitude = _longitude; }
	public void setCategory(Category _categ)	{ category = _categ; }
	
}
