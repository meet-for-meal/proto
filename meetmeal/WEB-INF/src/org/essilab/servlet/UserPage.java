package org.essilab.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.essilab.module.category.CategoryService;
import org.essilab.module.category.model.Category;
import org.essilab.module.interest.InterestService;
import org.essilab.module.interest.model.Interest;
import org.essilab.module.user.UserService;
import org.essilab.module.user.model.User;

@SuppressWarnings("serial")
public class UserPage extends HttpServlet {
    public static final String ATT_USER         = "user";
    public static final String ATT_SESSION_INTERESTS = "interests";
    public static final String ATT_SESSION_CATEGORIES = "categories";
    public static final String ATT_SESSION_FRIENDS = "friends";
    private static final String ID   = "id";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String url = request
    			.getRequestURI()
    			.substring(request.getContextPath().length()+1);
        request.setAttribute("url", url);
        HttpSession session = request.getSession();
        
        String id = getValeurChamp( request, ID );
        UserService userservice = UserService.getInstance();
        User user = userservice.userSelect(Integer.parseInt(id));
        if (id != null ) {
        	
            
            InterestService interestService = InterestService.getInstance();
            List<Interest>  interest = interestService.getInterestByUser(user);
            
            CategoryService categoryService = CategoryService.getInstance();
            List<Category>  category = (List<Category>) categoryService.getCategoryByUser(user);
            
            List<User> friend = (List<User>) userservice.userFriends(user);
            
            session.setAttribute(ATT_SESSION_INTERESTS, interest);
            session.setAttribute(ATT_SESSION_CATEGORIES, category);
            session.setAttribute(ATT_SESSION_FRIENDS, friend);
        }
        else {
        	session.setAttribute(ATT_SESSION_INTERESTS, null);
        	session.setAttribute(ATT_SESSION_CATEGORIES, null);
        	session.setAttribute(ATT_SESSION_FRIENDS, null);
        }        

        request.setAttribute( ATT_USER, user );
    	
        
        this.getServletContext().getRequestDispatcher("/layout.jsp" ).forward( request, response );
    }
 
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    	String url = request
     			.getRequestURI()
     			.substring(request.getContextPath().length()+1);
         request.setAttribute("url", url); 

         
        
        this.getServletContext().getRequestDispatcher( "/layout.jsp" ).forward( request, response );
    }
    
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}