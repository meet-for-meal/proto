package org.essilab.module.invitation.model;

import java.util.Date;

import org.essilab.module.user.model.User;

public class Invitation {
	
	private int id;
	private User senderId;
	private Date createdDate;
	private boolean isAccepted;
	private boolean isConfirmed;
	private boolean isOpen;
	private double latitude;
	private double longitude;
	private String message;
	
	//Constructor
	public Invitation() {	
		this.creator = new User();
	}
	
	public Invitation(int _i, Date _cd, Date _dd, boolean _io, double _lat, double _lng, String _m) {
		this.id = _i;
		this.createdDate = _cd;
		this.disponibilityDate = _dd;
		this.isOpen = _io;
		this.latitude = _lat;
		this.longitude = _lng;
		this.message = _m;
		this.creator = new User();
	}
	
	//Getters
	public int getId() 						{ return this.id; }
	public User getCreator() 				{ return this.creator; }
	public Date getCreatedDate() 			{ return this.createdDate; }
	public Date getDisponibilityDate() 		{ return this.disponibilityDate; }
	public boolean getIsOpen()				{ return this.isOpen; }
	public double getLatitude() 			{ return this.latitude; }
	public double getLongitude() 			{ return this.longitude; }
	public String getMessage() 				{ return this.message; }
	//Setters
	public void setId(int _id) 				{ this.id = _id; }
	public void setCreator(User _u) 		{ this.creator = _u; }
	public void setCreatedDate(Date _cd)	{ this.createdDate = _cd; }
	public void setDisponibilityDate(Date _dd) 		{ this.disponibilityDate = _dd; }
	public void setIsOpen(boolean _io)		{ this.isOpen = _io; }
	public void setLatitude(double _lat) 		{ this.latitude = _lat; }
	public void setLongitude(double _lng)	{ this.longitude = _lng; }
	public void setMessage(String _m)		{ this.message = _m; }
	
	
	//Methods

}
