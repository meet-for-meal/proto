package org.essilab.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.essilab.forms.CreateAnnounceForm;
import org.essilab.forms.SearchAnnounceForm;
import org.essilab.module.announce.AnnounceService;
import org.essilab.module.announce.model.Announce;
import org.essilab.module.interest.model.Interest;
import org.essilab.module.user.model.User;

public class CreateAnnounce  extends HttpServlet {
    public static final String ATT_USER         = "user";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUser";
    public static final String ATT_SESSION_ANNOUNCES = "announces";

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
        CreateAnnounceForm form = new CreateAnnounceForm();

        
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("sessionUser");
        /* Handle request */
        Announce announce = form.CreateAnnounce( request, user );
        /* Retrieve session */
        if ( form.getErrors().isEmpty() && user != null && announce != null) {
            
        } else {
        	session.setAttribute(ATT_SESSION_ANNOUNCES, null);
        }
 
        /* store form error and data in request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, user );
        
        this.getServletContext().getRequestDispatcher( "/layout.jsp" ).forward( request, response );
    }
}
