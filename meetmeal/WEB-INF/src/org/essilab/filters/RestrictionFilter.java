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
 
public class RestrictionFilter implements Filter {
    public static final String HOME     = "/homepage";
    public static final String ATT_SESSION_USER = "sessionUser";
 
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
        if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
            /* Redirection vers la page publique */
            response.sendRedirect( request.getContextPath() +  HOME);
        } else {
            /* Affichage de la page restreinte */
            chain.doFilter( request, response );
        }
    }
 
    public void destroy() {
    }
}
