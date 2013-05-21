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
	private int age, gender = 0;
	private boolean firstVisit = true, isAdmin = false;
	private List<User> friends;
	private List<Interest> interests;
	private List<Category> tastes;
	
	private Date lastPosition;
	private double lastLat, lastLong;
	
	//Constructor
	public User() {
//		this.lastPosition = new Date();
		this.friends = new ArrayList<User>();
		this.interests = new ArrayList<Interest>();
		this.tastes = new ArrayList<Category>();
	}
	public User(int _id, String _ln, String _fn, String _e) {
		this();
		this.id = _id;
		this.lastname = _ln;
		this.firstname = _fn;
		this.email = _e;
	}
	public User(int _id, String _ln, String _fn, int _a, String _e) {
		this();
		this.id = _id;
		this.lastname = _ln;
		this.firstname = _fn;
		this.age = _a;
		this.email = _e;
	}
	public User(int _id, String _ln, String _fn, int _a, String _e, int _g, boolean _fv, boolean _ia) {
		this(_id,_ln,_fn, _a, _e);
		this.gender = _g;
		this.firstVisit = _fv;
		this.isAdmin = _ia;
	}
	public User(int _id, String _ln, String _fn, int _a, String _e, int _g) {
		this(_id,_ln,_fn,_a, _e);
		this.gender = _g;
	}
	public User(int _id, String _ln, String _fn, int _a, String _e, String _p, int _g, boolean _fv, boolean _ia) {
		this(_id,_ln,_fn, _a,_e,_g,_fv,_ia);
		this.password = _p;
	}
	public User(int _id, String _ln, String _fn, int _a, String _e, String _p, int _g, boolean _fv, boolean _ia, Date _lp, double _llat, double _llng) {
		this(_id,_ln,_fn, _a,_e,_p,_g,_fv,_ia);
		this.lastPosition = _lp;
		this.lastLat = _llat;
		this.lastLong = _llng;
	}
	public User(int _id, String _ln, String _fn, int _a, String _e, String _p, int _g, boolean _fv, boolean _ia, List<User> _f, List<Interest> _i, List<Category> _t) {
		this(_id,_ln,_fn, _a,_e,_p,_g,_fv,_ia);
		this.friends = _f;
		this.interests = _i;
		this.tastes = _t;
	}
	
	//Getters
	public int getId() 						{ return this.id; }
	public String getLastname() 			{ return this.lastname; }
	public String getFirstname()			{ return this.firstname; }
	public int getAge()						{ return this.age; }
	public String getEmail() 				{ return this.email; }
	public String getPassword()				{ return this.password; }
	public int getGender()					{ return this.gender; }
	public boolean getFirstVisit()			{ return this.firstVisit; }
	public boolean getIsAdmin()				{ return this.isAdmin; }
	public List<User> getFriends() 			{ return this.friends; }
	public Date getLastPosition() 			{ return this.lastPosition; }
	public double getLastLat()				{ return this.lastLat; }
	public double getLastLong()				{ return this.lastLong; }
	//Setters
	public void setId(int _id) 				{ this.id = _id; }
	public void setLastname(String _l) 		{ this.lastname = _l; }
	public void setFirstname(String _f)		{ this.firstname = _f; }
	public void setAge(int _a)				{ this.age = _a; }
	public void setEmail(String _e) 		{ this.email = _e; }
	public void setPassword(String _p)		{ this.password = _p; }
	public void setGender(int _g)			{ this.gender = _g; }
	public void setFirstVisit(boolean _fv)	{ this.firstVisit = _fv; }
	public void setIsAdmin(boolean _ia)		{ this.isAdmin = _ia; }
	public void setFriends(List<User> _f) 	{ this.friends = _f; }
	public void setLastPosition(Date _lp) 	{ this.lastPosition = _lp; }
	public void setLastLat(double _llat)	{ this.lastLat = _llat; }
	public void setLastLong(double _llng)	{ this.lastLong = _llng; }
}
