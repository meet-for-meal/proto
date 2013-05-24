package org.essilab.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.essilab.module.announce.AnnounceService;
import org.essilab.module.announce.model.Announce;
import org.essilab.module.category.CategoryService;
import org.essilab.module.category.model.Category;
import org.essilab.module.interest.InterestService;
import org.essilab.module.interest.model.Interest;

public class SingleAnnounce extends HttpServlet {
    public static final String ATT_USER         = "user";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUser";
    public static final String ATT_SESSION_ANNOUNCE = "announce";
    public static final String ATT_SESSION_CATEGORIES = "categories";
    public static final String ATT_SESSION_INTERESTS = "interests";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String url = request
    			.getRequestURI()
    			.substring(request.getContextPath().length()+1);
        request.setAttribute("url", url);
        HttpSession session = request.getSession();
        if (request.getParameter("announceid") != null) {
        	int id_announce = Integer.parseInt(request.getParameter("announceid"));
	        AnnounceService announceService = AnnounceService.getInstance();
	        Announce announce = announceService.announceSelect(id_announce);
	        request.setAttribute(ATT_SESSION_ANNOUNCE, announce);
	        
	        InterestService interestService = InterestService.getInstance();
            CategoryService categoryService = CategoryService.getInstance();
            List<Interest> super_list = interestService.getInterestByUser(announce.getCreator());
            List<Category> super_list_category = categoryService.getCategoryByUser(announce.getCreator());

                        
            session.setAttribute(ATT_SESSION_CATEGORIES, super_list_category);
            session.setAttribute(ATT_SESSION_INTERESTS, super_list);
	        
	        
	        this.getServletContext().getRequestDispatcher("/layout.jsp" ).forward( request, response );
        }
    }
 
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	String url = request
    			.getRequestURI()
    			.substring(request.getContextPath().length()+1);
        request.setAttribute("url", url);
        
        if (request.getParameter("announceid") != null) {
	        int id_announce = Integer.parseInt(request.getParameter("announceid"));
	        AnnounceService announceService = AnnounceService.getInstance();
	        Announce announce = announceService.announceSelect(id_announce);
	        request.setAttribute(ATT_SESSION_ANNOUNCE, announce);
	        this.getServletContext().getRequestDispatcher("/layout.jsp" ).forward( request, response );
        }
    }
}
