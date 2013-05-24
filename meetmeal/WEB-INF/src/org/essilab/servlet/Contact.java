package org.essilab.servlet;
 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.essilab.forms.ContactForm;


@SuppressWarnings("serial")
public class Contact extends HttpServlet {
    public static final String CONTACT_VIEW = "/WEB-INF/views/front/contact.jsp";
    public static final String HOMEPAGE_VIEW = "/WEB-INF/views/front/homepage.jsp";
    public static final String ATT_FORM = "form";
    public static final String ATT_SESSION_USER = "sessionUser";
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /* Display inscription page */
    	String url = request
    			.getRequestURI()
    			.substring(request.getContextPath().length()+1);
        request.setAttribute("url", url);
        /* Affichage de la page de contact */
        this.getServletContext().getRequestDispatcher( "/layout.jsp" ).forward( request, response );

         
    }
    
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    	String url = request
    			.getRequestURI()
    			.substring(request.getContextPath().length()+1);
        request.setAttribute("url", url);

        /* Préparation de l'objet formulaire */
        ContactForm form = new ContactForm();
         
        
        form.sendMail(request);
        
        if ( form.getErrors().isEmpty() ) {

            response.sendRedirect( request.getContextPath() + "/homepage" );
			return;
        } else {
        	request.setAttribute( ATT_FORM, form );
            this.getServletContext().getRequestDispatcher( "/layout.jsp" ).forward( request, response );
        }

    }
    
    
}