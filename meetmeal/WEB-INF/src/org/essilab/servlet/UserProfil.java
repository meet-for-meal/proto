package org.essilab.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.essilab.forms.UserForm;
import org.essilab.module.announce.AnnounceService;
import org.essilab.module.announce.model.Announce;
import org.essilab.module.category.CategoryService;
import org.essilab.module.category.model.Category;
import org.essilab.module.interest.InterestService;
import org.essilab.module.interest.model.Interest;
import org.essilab.module.invitation.InvitationService;
import org.essilab.module.invitation.model.Invitation;
import org.essilab.module.user.UserService;
import org.essilab.module.user.model.User;

@SuppressWarnings("serial")
public class UserProfil extends HttpServlet {
    public static final String ATT_USER         = "user";
    public static final String ATT_USER_PROFILE = "userprofile";
    public static final String ATT_SESSION_INTERESTS = "interests";
    public static final String ATT_SESSION_CATEGORIES = "categories";
    public static final String ATT_SESSION_FRIENDS = "friends";
    public static final String ATT_SESSION_INVITATION = "invitation";
    public static final String ATT_SESSION_INVITATION_ACCEPTED = "accepted";
    public static final String ATT_SESSION_MEAL = "meal";
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String url = request
    			.getRequestURI()
    			.substring(request.getContextPath().length()+1);
        request.setAttribute("url", url);
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("sessionUser");
        UserService userService = UserService.getInstance();
        AnnounceService announceService = AnnounceService.getInstance();
        InvitationService invitationService = InvitationService.getInstance();
    	if(!url.substring(0,6).equals("mypage")){

    		if(request.getParameter("id") != null){
	    		user = userService.userSelect(Integer.parseInt(request.getParameter("id")));
	            request.setAttribute( ATT_USER_PROFILE, user );
	            session.setAttribute( ATT_USER_PROFILE, user );
    		}
    	}
    	else{
    		request.setAttribute( ATT_USER, user );
    	}
    	
        if (user != null ) {
        	int id_announce = 0;	

        	//Check if invitations are waiting
        	id_announce = announceService.getIdByCreatorId(user.getId());
        	if(id_announce != 0){
        		Invitation invit = invitationService.findInvitationsByAnnounceid(id_announce);
        		session.setAttribute(ATT_SESSION_INVITATION, invit);
        	}
        	
        	//Check if invitation is_accepted (for sender)
        	List<Invitation> list_invit = invitationService.findInvitationsByUserid(user.getId());
        	if(list_invit != null){
        		Invitation is_accepted = null;
	        	for(Invitation in : list_invit){
	        		if(in.getIsAccepted() == true){
	        			is_accepted = in;
	        		}
	        	}
	        	if(is_accepted == null){
	        		
	        		session.removeAttribute(ATT_SESSION_INVITATION_ACCEPTED);
	        		session.removeAttribute(ATT_SESSION_MEAL);
	        	}
	        	else{
		        	session.setAttribute(ATT_SESSION_MEAL, true);
	        		session.setAttribute(ATT_SESSION_INVITATION_ACCEPTED, is_accepted);
	        	}
        	}
        	else{
        		session.removeAttribute(ATT_SESSION_MEAL);
        	}
        	
        	// Handle invitation to eat (for receiver)
        	if(request.getParameter("accepted") != null){
        		Invitation invit = (Invitation) session.getAttribute(ATT_SESSION_INVITATION);
        		Announce announce = announceService.announceSelect(id_announce);
            	if(request.getParameter("accepted").equals("true")){
            		
            		invit.setIsAccepted(true);
            		boolean update = invitationService.invitationUpdate(invit);
            		session.removeAttribute(ATT_SESSION_INVITATION);
            		announce.setIsOpen(false);
            		boolean update_announce = announceService.announceUpdate(announce);
            		session.setAttribute(ATT_SESSION_MEAL, true);

            	}
            	else{
            		invit.setIsOpen(false);
            		boolean update = invitationService.invitationUpdate(invit);
            		session.removeAttribute(ATT_SESSION_INVITATION);
            		session.removeAttribute(ATT_SESSION_MEAL);
            		announce.setIsOpen(true);
            		boolean update_announce = announceService.announceUpdate(announce);

            	}
            	
            }
        	
            InterestService interestService = InterestService.getInstance();
            List<Interest>  interest = interestService.getInterestByUser(user);
            
            CategoryService categoryService = CategoryService.getInstance();
            List<Category>  category = (List<Category>) categoryService.getCategoryByUser(user);
            
            UserService userservice = UserService.getInstance();
            List<User> friend = (List<User>) userservice.userFriends(user);
            
            session.setAttribute(ATT_SESSION_INTERESTS, interest);
            session.setAttribute(ATT_SESSION_CATEGORIES, category);
            session.setAttribute(ATT_SESSION_FRIENDS, friend);
            
        } else {
        	session.setAttribute(ATT_SESSION_INTERESTS, null);
        	session.setAttribute(ATT_SESSION_CATEGORIES, null);
        	session.setAttribute(ATT_SESSION_FRIENDS, null);
        }
        
        
        this.getServletContext().getRequestDispatcher("/layout.jsp" ).forward( request, response );
    }
 
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    	String url = request
     			.getRequestURI()
     			.substring(request.getContextPath().length()+1);
         request.setAttribute("url", url); 

         HttpSession session = request.getSession();
         
         User user = (User) session.getAttribute("sessionUser");
         UserForm userform = new UserForm();
    	 User newuser= userform.GetUserUpdate(request);
    	 
         if ( userform.getErrors().isEmpty() ) {
        	 
        	 UserService userservice = UserService.getInstance();
        	 userservice.userUpdate(newuser);
        	 
        	 InterestService interestService = InterestService.getInstance();
             List<Interest>  interest = interestService.getInterestByUser(user);
             
             CategoryService categoryService = CategoryService.getInstance();
             List<Category>  category = (List<Category>) categoryService.getCategoryByUser(user);
             
             List<User> friend = (List<User>) userservice.userFriends(user);
             
             session.setAttribute(ATT_SESSION_INTERESTS, interest);
             session.setAttribute(ATT_SESSION_CATEGORIES, category);
             session.setAttribute(ATT_SESSION_FRIENDS, friend);
         
         }
        
 
        /* store form error and data in request */
        request.setAttribute( ATT_USER, user );
        
        this.getServletContext().getRequestDispatcher( "/layout.jsp" ).forward( request, response );
    }
}