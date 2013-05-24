package org.essilab.module.message.actions;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.announce.model.Announce;
import org.essilab.module.message.MessageService;
import org.essilab.module.message.model.Message;
import org.essilab.module.user.model.UserDao;
import org.essilab.servlet.IAction;

public class MessageInsertAjax implements IAction{

	MessageService service = MessageService.getInstance();
	public final static String FIELD_SENDER = "senderId";
	public final static String FIELD_RECEIVER = "receiverId";
	public final static String FIELD_CREATEDDATE = "createdDate";
	public final static String FIELD_CONTENT = "content";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean ok = false;
			Message m = readPost(request);
			response.setContentType(HEADER_TYPE_JSON);
			ok = service.messageInsert(m);
			response.getWriter().println(ok ? RESPONSE_OK : RESPONSE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}

	public Message readPost(HttpServletRequest request) {
        int sender = getFieldValue(request, FIELD_SENDER) != null ? Integer.parseInt(getFieldValue(request, FIELD_SENDER)) : 0;
        int receiver = getFieldValue(request, FIELD_RECEIVER) != null ? Integer.parseInt(getFieldValue(request, FIELD_RECEIVER)) : 0;
        Date created = new Date(getFieldValue(request, FIELD_CREATEDDATE));
        String content = getFieldValue(request, FIELD_CONTENT);
        
       	Message msg = null;
		try {
			msg = new Message(0,content,UserDao.getUser(sender),UserDao.getUser(receiver),created);
		} catch (SQLException e) {
			e.printStackTrace();
		}
     
        return msg;
    }
    
    /*
     * Return field value or null
     */
    private static String getFieldValue( HttpServletRequest request, String fieldName ) {
        String valeur = request.getParameter( fieldName );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }
    }
}
