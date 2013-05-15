package org.essilab.module.user.model;

public class User {
	public Long id;
	public String lastname;
	public String firstname;
	public String email;
	public String password;
	public boolean sex;
	public String lastPosition;
	boolean active = true;
	public float lastLatitude;
	public float lastLongitude;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(Long id, String firstname, String lastname,float lastLatitude,float lastLongitude) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.id = id;
		this.lastLatitude = lastLatitude;
		this.lastLongitude = lastLongitude;
	}

	//@Override
	/*public String toString() {
		return "User [login=" + email + ", password=" + password + ", id=" + id
				+ " ]";
	}*/

	//exemple d override
	/*public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}*/
}
