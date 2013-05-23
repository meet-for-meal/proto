package org.essilab.module.invitation.model;

import java.util.Date;

import org.essilab.module.announce.model.Announce;
import org.essilab.module.user.model.User;

public class Invitation {
	
	private int id;
	private User sender;
	private Announce announce;
	private Date createdDate;
	private boolean isAccepted;
	private boolean isConfirmed;
	private boolean isOpen;
//	private double latitude;
//	private double longitude;
	private String message;
	
	//Constructor
	public Invitation() {	
		this.sender = new User();
		this.announce = new Announce();
	}
	
	public Invitation(int _i, Date _cd, boolean _ia, boolean _ic, boolean _io, String _m) {
		this();
		this.id = _i;
		this.createdDate = _cd;
		this.isAccepted = _ia;
		this.isConfirmed = _ic;
		this.isOpen = _io;
		this.message = _m;
	}
	public Invitation(int _i, Date _cd, boolean _ia, boolean _ic, boolean _io, String _m, User _u, Announce _a) {
		this(_i, _cd, _ia, _ic, _io, _m);
		this.sender = _u;
		this.announce = _a;
	}
	
	//Getters
	public int getId() 						{ return this.id; }
	public User getSender() 				{ return this.sender; }
	public Announce getAnnounce()			{ return this.announce; }
	public Date getCreatedDate() 			{ return this.createdDate; }
	public boolean getIsAccepted() 			{ return this.isAccepted; }
	public boolean getIsConfirmed() 		{ return this.isConfirmed; }
	public boolean getIsOpen()				{ return this.isOpen; }
//	public double getLatitude() 			{ return this.latitude; }
//	public double getLongitude() 			{ return this.longitude; }
	public String getMessage() 				{ return this.message; }
	//Setters
	public void setId(int _id) 				{ this.id = _id; }
	public void setSender(User _u) 			{ this.sender = _u; }
	public void setAnnounce(Announce _a)	{ this.announce = _a; }
	public void setCreatedDate(Date _cd)	{ this.createdDate = _cd; }
	public void setIsAccepted(boolean _ia) 	{ this.isAccepted = _ia; }
	public void setIsConfirmed(boolean _ic)	{ this.isConfirmed = _ic; }
	public void setIsOpen(boolean _io)		{ this.isOpen = _io; }
//	public void setLatitude(double _lat) 	{ this.latitude = _lat; }
//	public void setLongitude(double _lng)	{ this.longitude = _lng; }
	public void setMessage(String _m)		{ this.message = _m; }
	
	
	//Methods

}
