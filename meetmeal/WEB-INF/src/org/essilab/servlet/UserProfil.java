package org.essilab.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.essilab.forms.UserForm;
import org.essilab.module.category.CategoryService;
import org.essilab.module.category.model.Category;
import org.essilab.module.interest.InterestService;
import org.essilab.module.interest.model.Interest;
import org.essilab.module.user.model.User;

public class UserProfil extends HttpServlet {
    public static final String ATT_USER         = "user";
    public static final String ATT_SESSION_INTERESTS = "interests";
    public static final String ATT_SESSION_CATEGORIES = "categories";
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String url = request
    			.getRequestURI()
    			.substring(request.getContextPath().length()+1);
        request.setAttribute("url", url);
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("sessionUser");
        if (user != null ) {

            InterestService interestService = InterestService.getInstance();
            List<Interest>  interest = interestService.getInterestByUser(user);
            System.out.println(interest.get(0));
            CategoryService categoryService = CategoryService.getInstance();
            List<Category>  category = (List<Category>) categoryService.getCategoryByUser(user);
            session.setAttribute(ATT_SESSION_INTERESTS, interest);
            session.setAttribute(ATT_SESSION_CATEGORIES, category);
            
        } else {
        	session.setAttribute(ATT_SESSION_INTERESTS, null);
        	session.setAttribute(ATT_SESSION_CATEGORIES, null);
        }
 
        /* store form error and data in request */
        request.setAttribute( ATT_USER, user );
        this.getServletContext().getRequestDispatcher("/layout.jsp" ).forward( request, response );
    }
 
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    	String url = request
     			.getRequestURI()
     			.substring(request.getContextPath().length()+1);
         request.setAttribute("url", url); 

        /* Préparation de l'objet formulaire */
        

        /* Traitement de la requête et récupération du bean en résultant */

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
 

        User user = (User) session.getAttribute("sessionUser");
        if (user != null ) {

            InterestService interestService = InterestService.getInstance();
            List<Interest>  interest = interestService.getInterestByUser(user);
            CategoryService categoryService = CategoryService.getInstance();
            List<Category>  category = categoryService.getCategoryByUser(user);
            session.setAttribute(ATT_SESSION_INTERESTS, interest);
            session.setAttribute(ATT_SESSION_CATEGORIES, category);
            
        } else {
        	session.setAttribute(ATT_SESSION_INTERESTS, null);
        	session.setAttribute(ATT_SESSION_CATEGORIES, null);
        }
 
        /* store form error and data in request */
        request.setAttribute( ATT_USER, user );
        
        this.getServletContext().getRequestDispatcher( "/layout.jsp" ).forward( request, response );
    }
}