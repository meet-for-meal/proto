package org.essilab.module.interest.model;

public class Interest {
	
	private int id;
	private String tag = null;
	
	Interest()						{}
	Interest(String _tag)			{ tag = _tag; }
	Interest(int _id, String _tag) { 
		id = _id;
		tag = _tag; 
	}
	
	public int getId()				{ return id; }
	public void setTag(String _tag)	{ tag = _tag; }
	public String getTag()			{ return tag; }

}
