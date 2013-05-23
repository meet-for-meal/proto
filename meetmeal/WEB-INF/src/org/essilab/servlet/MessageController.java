package org.essilab.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import org.essilab.module.message.MessageService;
import org.essilab.module.message.model.Message;
import org.essilab.module.user.UserService;
import org.essilab.module.user.model.User;

public class MessageController  extends HttpServlet {
	public static final String ATT_SESSION_CONVERSATIONS = "conversations";
	public static final String ATT_SESSION_MESSAGES = "messages";
	public static final String ATT_SESSION_SENDERS = "users";
    public void service( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String url = request
    			.getRequestURI()
    			.substring(request.getContextPath().length()+1);
        request.setAttribute("url", url);
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("sessionUser");

        MessageService messageService = MessageService.getInstance();
        UserService userService = UserService.getInstance();
        List<Message> conversations = messageService.messageListConversation(user.getId());
        Integer id_conv = 0;
        if(request.getParameter("id_conv") != null){
        	try{
            	id_conv = Integer.parseInt(request.getParameter("id_conv"));
            }
            catch (Exception e) {
    			e.printStackTrace();
    		}
        }

        if(id_conv != 0){
        	List<Message> messages = messageService.messageListMessages(id_conv, user.getId());
        	
        	session.setAttribute(ATT_SESSION_MESSAGES, messages);
        	

        	
        }
        else{
        	session.setAttribute(ATT_SESSION_MESSAGES, null);
        }
        
        
        
        if(conversations !=null){
        	session.setAttribute(ATT_SESSION_CONVERSATIONS, conversations);
        	User sender = new User();
        	List<User> list = new ArrayList<User>();
        	for(Message m: conversations){
        		sender = userService.userSelect(m.getSender().getId());
        		list.add(sender);
        	}
        	session.setAttribute(ATT_SESSION_SENDERS, list);
        }
        else{
        	System.out.println("echec requete");
        }

        this.getServletContext().getRequestDispatcher("/layout.jsp" ).forward( request, response );
    }
}
