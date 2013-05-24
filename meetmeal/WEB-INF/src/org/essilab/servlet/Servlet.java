package org.essilab.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.servlet.util.HttpResponseCapture;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;


public class Servlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
// System.out.println("OOKOOKOKKOK");
		ByteOutputStream buffer = new ByteOutputStream();
		HttpResponseCapture capture = new HttpResponseCapture(response, buffer);
		
		request
		.getRequestDispatcher("index.jsp")
		.forward(request, capture);
		
		capture.flushBuffer();
		response.getOutputStream().print(new String(buffer.getBytes()));
		System.err.println("---->>>> ");
		
	}

}
