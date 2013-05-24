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
import org.essilab.module.user.model.User;

@SuppressWarnings("serial")
public class EditUserProfil extends HttpServlet {
    public static final String ATT_USER         = "user";
    public static final String ATT_SESSION_ALLINTERESTS = "allinterests";
    public static final String ATT_SESSION_ALLCATEGORIES = "allcategories";
    public static final String ATT_SESSION_USERINTERESTS = "userinterests";
    public static final String ATT_SESSION_USERCATEGORIES = "usercategories";
    
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String url = request
    			.getRequestURI()
    			.substring(request.getContextPath().length()+1);
        request.setAttribute("url", url);
        
        HttpSession session = request.getSession();
        

        User user = (User) session.getAttribute("sessionUser");
        if (user != null ) {

            InterestService interestService = InterestService.getInstance();
            List<Interest>  allInterest = interestService.interestList();
            List<Interest>  userInterest = interestService.getInterestByUser(user);
            
            CategoryService categoryService = CategoryService.getInstance();
            List<Category>  allCategory = categoryService.CategoryList();
            List<Category>  userCategory = categoryService.getCategoryByUser(user);
            
            session.setAttribute(ATT_SESSION_ALLINTERESTS, allInterest);
            session.setAttribute(ATT_SESSION_ALLCATEGORIES, allCategory);
            session.setAttribute(ATT_SESSION_USERINTERESTS, userInterest);
            session.setAttribute(ATT_SESSION_USERCATEGORIES, userCategory);
            
        } else {
        	session.setAttribute(ATT_SESSION_ALLINTERESTS, null);
        	session.setAttribute(ATT_SESSION_ALLCATEGORIES, null);
        	session.setAttribute(ATT_SESSION_USERINTERESTS, null);
            session.setAttribute(ATT_SESSION_USERCATEGORIES, null);
        }
 
        /* store form error and data in request */
        request.setAttribute( ATT_USER, user );
        
        this.getServletContext().getRequestDispatcher( "/layout.jsp" ).forward( request, response );
    
    }
 
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    	String url = request
     			.getRequestURI()
     			.substring(request.getContextPath().length()+1);
         request.setAttribute("url", url); 

      }
}