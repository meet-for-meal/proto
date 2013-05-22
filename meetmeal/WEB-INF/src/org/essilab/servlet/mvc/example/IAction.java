package org.essilab.servlet.mvc.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface IAction {
	public static final String RESPONSE_OK = "{\"status\": \"ok\"}";
	public static final String RESPONSE_ERROR = "{\"status\": \"error\"}";
	
	public void execute(HttpServletRequest request, HttpServletResponse response);
}
