package org.essilab.module.message.model;
import java.util.Date;

import org.essilab.module.user.model.User;

public class Message {
	
	private int id;
	private String content;
	private User sender, receiver;
	private Date createdDate;
	
	//Constructor
	public Message() {
		createdDate = new Date();
		sender = new User();
		receiver = new User();
	}
	public Message(int _i, String _c, Date _sd) {
		this.id = _i;
		this.content = _c;
		this.createdDate = _sd;
		this.sender = new User();
		this.receiver = new User();
	}
	public Message(int _i, String _c, User _u1, User _u2, Date _cd) {
		this.id = _i;
		this.content = _c;
		this.createdDate = _cd;
		this.sender = _u1;
		this.receiver = _u2;
	}
	
	//Getters
	public int getId() 						{ return this.id; }
	public String getContent() 				{ return this.content; }
	public User getUser1()					{ return this.sender; }
	public User getUser2() 					{ return this.receiver; }
	public Date getSentDate() 				{ return this.createdDate; }
	//Setters
	public void setId(int _id) 				{ this.id = _id; }
	public void setContent(String _c) 		{ this.content = _c; }
	public void setSender(User _u1)			{ this.sender = _u1; }
	public void setReceiver(User _u2) 		{ this.receiver = _u2; }
	public void setCreatedDate(Date _cd) 	{ this.createdDate = _cd; }
}
