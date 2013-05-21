package org.essilab.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.essilab.module.user.model.User;

public class AdminFilter  implements Filter {
    public static final String CONNECTION     = "/connect-admin";
 
    public void init( FilterConfig config ) throws ServletException {
    }
 
    public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException,
            ServletException {
        /* Cast request and response */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
 
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
 
        /**
         * If sessionUser is null, then there is no valid session
         */
        User user = (User) session.getAttribute("sessionUser");
        if(user != null){
	        if ( user.getIsAdmin() != true ) {
	            /* Redirect to login page */
	            session.invalidate();
	            response.sendRedirect( request.getContextPath() +  CONNECTION);
	        } else {
	            /* display back-office */
	            chain.doFilter( request, response );
	        }
        }
        else{
        	response.sendRedirect( request.getContextPath() +  CONNECTION);
        }
    }
 
    public void destroy() {
    }
}
