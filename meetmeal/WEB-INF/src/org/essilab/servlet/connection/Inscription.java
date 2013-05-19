package org.essilab.servlet.connection;
 
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.essilab.module.user.model.User;
import org.essilab.forms.InscriptionForm; 
import org.essilab.module.user.UserService;


public class Inscription extends HttpServlet {
    public static final String INSCRIPTION_VIEW = "/WEB-INF/views/front/register.jsp";
    public static final String INDEX_VIEW = "/WEB-INF/views/front/index.jsp";
    public static final String ATT_USER = "user";
    public static final String ATT_FORM = "form";
    public static final String ATT_SESSION_USER = "sessionUser";
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /* Display inscription page */
    	String url = request
    			.getRequestURI()
    			.substring(request.getContextPath().length()+1);
        request.setAttribute("url", url);
        /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher( "/layout.jsp" ).forward( request, response );

         
    }
    
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    	String url = request
    			.getRequestURI()
    			.substring(request.getContextPath().length()+1);
        request.setAttribute("url", url);

        /* Préparation de l'objet formulaire */
        InscriptionForm form = new InscriptionForm();
         
        /* get user from form validation */
        User user = form.userInscription( request );
        
        if ( form.getErrors().isEmpty() ) {
        	/* Insert user in database */
            UserService userService = UserService.getInstance();
            userService.userInsert(user);
            
            /* Start session with new user */
            HttpSession session = request.getSession();
            session.setAttribute( ATT_SESSION_USER, user );
            response.sendRedirect( request.getContextPath() + "/index" );
			return;
        } else {
        	request.setAttribute( ATT_FORM, form );
            request.setAttribute( ATT_USER, user );
            this.getServletContext().getRequestDispatcher( "/layout.jsp" ).forward( request, response );
        }

    }
    
    
}