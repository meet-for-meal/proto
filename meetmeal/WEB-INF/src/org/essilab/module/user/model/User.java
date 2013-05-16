package org.essilab.module.user.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.essilab.module.category.model.Category;
import org.essilab.module.interest.model.Interest;

public class User {
	
	private int id;
	private String lastname, firstname;
	private String email, password;
	private List<User> friends;
	private List<Interest> interests;
	private List<Category> tastes;
	
	private Date lastPosition;
	private double lastLat, lastLong;
	
	//Constructor
	public User() {
		this.lastPosition = new Date();
		this.friends = new ArrayList<User>();
		this.interests = new ArrayList<Interest>();
		this.tastes = new ArrayList<Category>();
	}
	public User(int _id, String _ln, String _fn, String _e) {
		this.id = _id;
		this.lastname = _ln;
		this.firstname = _fn;
		this.email = _e;
		this.lastPosition = new Date();
		this.friends = new ArrayList<User>();
		this.interests = new ArrayList<Interest>();
		this.tastes = new ArrayList<Category>();
	}
	public User(int _id, String _ln, String _fn, String _e, String _p) {
		this.id = _id;
		this.lastname = _ln;
		this.firstname = _fn;
		this.email = _e;
		this.password = _p;
		this.lastPosition = new Date();
		this.friends = new ArrayList<User>();
		this.interests = new ArrayList<Interest>();
		this.tastes = new ArrayList<Category>();
	}
	public User(int _id, String _ln, String _fn, String _e, String _p, Date _lp, double _llat, double _llng) {
		this.id = _id;
		this.lastname = _ln;
		this.firstname = _fn;
		this.email = _e;
		this.password = _p;
		this.lastPosition = _lp;
		this.lastLat = _llat;
		this.lastLong = _llng;
		this.friends = new ArrayList<User>();
		this.interests = new ArrayList<Interest>();
		this.tastes = new ArrayList<Category>();
	}
	public User(int _id, String _ln, String _fn, String _e, String _p, List<User> _f, List<Interest> _i, List<Category> _t) {
		this.id = _id;
		this.lastname = _ln;
		this.firstname = _fn;
		this.email = _e;
		this.password = _p;
		this.lastPosition = new Date();
		this.friends = _f;
		this.interests = _i;
		this.tastes = _t;
	}
	
	//Getters
	public int getId() 						{ return this.id; }
	public String getLastname() 			{ return this.lastname; }
	public String getFirstname()			{ return this.firstname; }
	public String getEmail() 				{ return this.email; }
	public String getPassword()				{ return this.password; }
	public List<User> getFriends() 			{ return this.friends; }
	public Date getLastPosition() 			{ return this.lastPosition; }
	public double getLastLat()				{ return this.lastLat; }
	public double getLastLong()				{ return this.lastLong; }
	//Setters
	public void setId(int _id) 				{ this.id = _id; }
	public void setLastname(String _l) 		{ this.lastname = _l; }
	public void setFirstname(String _f)		{ this.firstname = _f; }
	public void setEmail(String _e) 		{ this.email = _e; }
	public void setPassword(String _p)		{ this.password = _p; }
	public void setFriends(List<User> _f) 	{ this.friends = _f; }
	public void setLastPosition(Date _lp) 	{ this.lastPosition = _lp; }
	public void setLastLat(double _llat)	{ this.lastLat = _llat; }
	public void setLastLong(double _llng)	{ this.lastLong = _llng; }
}
