package org.essilab.servlet.connection;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import org.essilab.module.user.model.User;
import org.essilab.forms.ConnectionForm;
import org.essilab.module.user.UserService; 
public class Connection extends HttpServlet {
    public static final String ATT_USER         = "user";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUser";
    public static final String VIEW              = "/WEB-INF/views/front/homepage.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String url = request
    			.getRequestURI()
    			.substring(request.getContextPath().length()+1);
        request.setAttribute("url", url);
        /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher( "/layout.jsp" ).forward( request, response );
    }
 
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	 String url = request
     			.getRequestURI()
     			.substring(request.getContextPath().length()+1);
         request.setAttribute("url", url); 
        /* Préparation de l'objet formulaire */
        ConnectionForm form = new ConnectionForm();
 
        /* Traitement de la requête et récupération du bean en résultant */
        User user = form.connectUser( request );
 
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
 
        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * User à la session, sinon suppression du bean de la session.
         */
        if ( form.getErrors().isEmpty() ) {
            
            /* Check if user exist in database */
            UserService userService = UserService.getInstance();
            user = userService.userAuthenticate(user);
            if(user != null){
            	session.setAttribute( ATT_SESSION_USER, user );
            	userService.setFirstVisit(user, false);

    			if(url.equals("connect-admin")){

    				response.sendRedirect( request.getContextPath() + "/admin" );
    				return;
    			}
    			else{
	                response.sendRedirect( request.getContextPath() + "/index" );
	    			return;
    			}
            }
            else{
            	request.setAttribute( "not_exist", "true" );
            }
        } else {
            session.setAttribute( ATT_SESSION_USER, null );
        }
 
        /* store form error and data in request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, user );
        
        this.getServletContext().getRequestDispatcher( "/layout.jsp" ).forward( request, response );
    }
}