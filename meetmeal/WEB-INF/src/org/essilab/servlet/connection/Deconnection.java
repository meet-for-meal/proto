package org.essilab.servlet.connection;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class Deconnection extends HttpServlet {
    public static final String URL_REDIRECTION = "homepage";
 
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Get session and invalidate */
        HttpSession session = request.getSession();
        session.invalidate();

        /* Redirect to homepage */
        response.sendRedirect( URL_REDIRECTION );
    }
}
