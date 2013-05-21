package org.essilab.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.essilab.forms.SearchAnnounceForm;
import org.essilab.module.announce.AnnounceService;
import org.essilab.module.announce.model.Announce;
import org.essilab.module.interest.model.Interest;
import org.essilab.module.user.model.User;

public class SingleAnnounce extends HttpServlet {
    public static final String ATT_USER         = "user";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUser";
    public static final String ATT_SESSION_ANNOUNCE = "announce";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String url = request
    			.getRequestURI()
    			.substring(request.getContextPath().length()+1);
        request.setAttribute("url", url);
        
        int id_announce = Integer.parseInt(request.getParameter("announceid"));
        AnnounceService announceService = AnnounceService.getInstance();
        Announce announce = announceService.getAnnounce(id_announce);
        request.setAttribute(ATT_SESSION_ANNOUNCE, announce);

        this.getServletContext().getRequestDispatcher("/layout.jsp" ).forward( request, response );
    }
 
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	String url = request
    			.getRequestURI()
    			.substring(request.getContextPath().length()+1);
        request.setAttribute("url", url);
        int id_announce = Integer.parseInt(request.getParameter("announceid"));
        AnnounceService announceService = AnnounceService.getInstance();
        Announce announce = announceService.getAnnounce(id_announce);
        request.setAttribute(ATT_SESSION_ANNOUNCE, announce);
        this.getServletContext().getRequestDispatcher("/layout.jsp" ).forward( request, response );
    	
    }
}
