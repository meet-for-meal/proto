package org.essilab.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.essilab.forms.CreateAnnounceForm;
import org.essilab.forms.CreateMessageForm;
import org.essilab.module.announce.model.Announce;
import org.essilab.module.message.model.Message;
import org.essilab.module.user.model.User;

public class MessageCreate extends HttpServlet {
    public static final String ATT_USER         = "user";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_SENDER = "sender";
    public static final String ATT_SESSION_MESSAGE = "message";
    public static final String ATT_SESSION_SUCCESS = "success";
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	String url = request
     			.getRequestURI()
     			.substring(request.getContextPath().length()+1);
         request.setAttribute("url", url); 
        this.getServletContext().getRequestDispatcher("/layout.jsp" ).forward( request, response );
    }
 
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    	String url = request
     			.getRequestURI()
     			.substring(request.getContextPath().length()+1);
         request.setAttribute("url", url); 

        /* Pr�paration de l'objet formulaire */
        CreateMessageForm form = new CreateMessageForm();

        
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("sessionUser");
        /* Handle request */
        Message message = form.CreateMessage( request );
        /* Retrieve session */
        if ( form.getErrors().isEmpty() && user != null && message != null) {
        	session.setAttribute(ATT_SESSION_SUCCESS, true);
        	session.setAttribute(ATT_SESSION_MESSAGE, message);
        	//response.sendRedirect( request.getContextPath() +  "/index");
        	//return;
        } else {
        	session.setAttribute(ATT_SESSION_SUCCESS, false);
        	session.setAttribute(ATT_SESSION_MESSAGE, null);
        }
        
        /* store form error and data in request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, user );
        
        this.getServletContext().getRequestDispatcher( "/layout.jsp" ).forward( request, response );
    }
}