package org.essilab.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.essilab.forms.ConnectionForm;
import org.essilab.forms.SearchAnnounceForm;
import org.essilab.module.announce.AnnounceService;
import org.essilab.module.announce.model.Announce;
import org.essilab.module.category.CategoryService;
import org.essilab.module.category.model.Category;
import org.essilab.module.interest.InterestService;
import org.essilab.module.interest.model.Interest;
import org.essilab.module.user.UserService;
import org.essilab.module.user.model.User;

public class SearchAnnounce extends HttpServlet {
	    public static final String ATT_USER         = "user";
	    public static final String ATT_FORM         = "form";
	    public static final String ATT_SESSION_USER = "sessionUser";
	    public static final String ATT_SESSION_ANNOUNCES = "announces";
	    public static final String ATT_SESSION_INTERESTS = "interests";
	    public static final String ATT_SESSION_CATEGORIES = "categories";
	    
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

	        /* Préparation de l'objet formulaire */
	        SearchAnnounceForm form = new SearchAnnounceForm();

	        /* Traitement de la requête et récupération du bean en résultant */
	        List<Interest> interests = form.SearchAnnounce( request );

	        /* Récupération de la session depuis la requête */
	        HttpSession session = request.getSession();
	 
	        /**
	         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
	         * User à la session, sinon suppression du bean de la session.
	         */
	        User user = (User) session.getAttribute("sessionUser");

	        if ( form.getErrors().isEmpty() && user != null && interests != null) {

	            AnnounceService announceService = AnnounceService.getInstance();
	            List<Announce>  announces = announceService.getNearAnnouncesByInterests(user,interests);
	            InterestService interestService = InterestService.getInstance();
	            CategoryService categoryService = CategoryService.getInstance();
	            List<List<Interest>> super_list = new ArrayList<List<Interest>>();
	            List<List<Category>> super_list_category = new ArrayList<List<Category>>();
	            for(Announce an : announces){
            		List<Interest>  interest = interestService.getInterestByUser(an.getCreator());
            		List<Category>  category = categoryService.getCategoryByUser(user);
            		super_list_category.add(category);
            		super_list.add(interest);
	            }
	                        
	            session.setAttribute(ATT_SESSION_CATEGORIES, super_list_category);
	            session.setAttribute(ATT_SESSION_INTERESTS, super_list);
	            session.setAttribute(ATT_SESSION_ANNOUNCES, announces);
	            
	        } else {
	        	session.setAttribute(ATT_SESSION_ANNOUNCES, null);
	            session.setAttribute( ATT_SESSION_USER, null );
	        }
	        

	        
	        /* store form error and data in request */
	        request.setAttribute( ATT_FORM, form );
	        request.setAttribute( ATT_USER, user );
	        
	        this.getServletContext().getRequestDispatcher( "/layout.jsp" ).forward( request, response );
	    }
}
