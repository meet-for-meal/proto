package org.essilab.module.restaurant.model;

import org.essilab.module.category.model.Category;

public class Restaurant {

	private int id;
	private String name = null;
	private long latitude;
	private long longitude;
	private int partnership;
	private String foursquareId;
	private String urlImage, titleImage, descImage;
	private Category category = null;
	
	//Constructor
	public Restaurant(){
		this.category = new Category();
	}
	public Restaurant(int _id, String _n, long _lat, long _lon) {
		this();
		id = _id;
		name = _n;
		latitude = _lat;
		longitude = _lon;
	}
	public Restaurant(int _id, String _n, long _lat, long _lon, String _f, int _p) {
		this(_id, _n, _lat, _lon);
		this.foursquareId = _f;
		this.partnership = _p;
	}
	public Restaurant(int _id, String _n, long _lat, long _lon, String _f, int _p, Category _c) {
		this(_id, _n, _lat, _lon, _f, _p);
		category = _c;
	}
	public Restaurant(int _id, String _n, long _lat, long _lon, String _f, int _p, String _u, String _t, String _d) {
		this(_id, _n, _lat, _lon, _f, _p);
		this.urlImage = _u;
		this.titleImage = _t;
		this.descImage = _d;
	}
	public Restaurant(int _id, String _n, long _lat, long _lon, String _f, int _p, Category _c, String _u, String _t, String _d) {
		this(_id, _n, _lat, _lon, _f, _p, _c);
		this.urlImage = _u;
		this.titleImage = _t;
		this.descImage = _d;
	}
	
	//Getters
	public int getId() 							{ return this.id; }
	public String getName()						{ return this.name;	}
	public long getLatitude()					{ return this.latitude; }
	public long getLongitude()					{ return this.longitude; }
	public String getFoursquareId()				{ return this.foursquareId;	}
	public int getPartnership()					{ return this.partnership;	}
	public String getUrlImage()					{ return this.urlImage;	}
	public String getTitleImage()				{ return this.titleImage; }
	public String getDescImage()				{ return this.descImage; }
	public Category getCategory()				{ return this.category; }
	
	//Setters
	public void setId(int _id) 					{ this.id = _id; }
	public void setName(String _name)			{ name = _name; }
	public void setLatitude(long _latitude)		{ latitude = _latitude; }
	public void setLongitude(long _longitude)	{ longitude = _longitude; }
	public void setFoursquareId(String _f)		{ foursquareId = _f; }
	public void setPartnership(int _p)			{ partnership = _p; }
	public void setUrlImage(String _u)			{ urlImage = _u; }
	public void setTitleImage(String _t)		{ titleImage = _t; }
	public void setDescImage(String _d)			{ descImage = _d; }
	public void setCategory(Category _categ)	{ category = _categ; }
	
}
