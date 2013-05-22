package org.essilab.module.category.model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Category {
	
	private int id;
	private String foursquareId;
	private String name;
	
	//Constructor
	public Category() {	}
	public Category(String _n){ this.name = _n; }
	public Category(int _i, String _f, String _n) {
		this.id = _i;
		this.foursquareId = _f;
		this.name = _n;
	}
	
	//Getters
	public int getId() 						{ return this.id; }
	public String getName() 				{ return this.name; }
	public String getFoursquareId()			{ return this.foursquareId; }
	//Setters
	public void setId(int _id) 				{ this.id = _id; }
	public void setName(String _n) 			{ this.name = _n; }
	public void setFoursquareId(String _f)	{ this.foursquareId = _f; }
	
	
	//Methods

}
