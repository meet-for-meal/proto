package org.essilab.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface IAction {
	public static final String RESPONSE_OK = "{\"status\": \"ok\"}";
	public static final String RESPONSE_ERROR = "{\"status\": \"error\"}";
	public static final String HEADER_TYPE_JSON = "application/x-javascript;charset=UTF-8";
	
	public void execute(HttpServletRequest request, HttpServletResponse response);
}
