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
	

	@Override public String toString() {
		
		StringBuilder result = new StringBuilder();
	    String NEW_LINE = System.getProperty("line.separator");

	    result.append(this.getClass().getName() + " Object {" + NEW_LINE);
	    result.append(" id: " + this.getId() + NEW_LINE);
	    result.append(" content: " + this.getContent() + NEW_LINE);
	    result.append(" sender: " + this.getSender() + NEW_LINE );
	    result.append(" receiver: " + this.getReceiver() + NEW_LINE);
	    result.append(" date: " + this.getSentDate() + NEW_LINE);
	    //Note that Collections and Maps also override toString
	    result.append("}");
	    return result.toString();
	}
	
	//Getters
	public int getId() 						{ return this.id; }
	public String getContent() 				{ return this.content; }
	public User getSender()					{ return this.sender; }
	public User getReceiver() 				{ return this.receiver; }
	public Date getSentDate() 				{ return this.createdDate; }
	public Date getCreatedDate() 				{ return this.createdDate; }
	//Setters
	public void setId(int _id) 				{ this.id = _id; }
	public void setContent(String _c) 		{ this.content = _c; }
	public void setSender(User _u1)			{ this.sender = _u1; }
	public void setReceiver(User _u2) 		{ this.receiver = _u2; }
	public void setCreatedDate(Date _cd) 	{ this.createdDate = _cd; }
}
