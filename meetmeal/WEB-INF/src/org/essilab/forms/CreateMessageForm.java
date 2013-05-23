package org.essilab.forms;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.essilab.module.announce.AnnounceService;
import org.essilab.module.announce.model.Announce;
import org.essilab.module.message.MessageService;
import org.essilab.module.message.model.Message;
import org.essilab.module.user.UserService;
import org.essilab.module.user.model.User;

public class CreateMessageForm {
	public static final String ATT_FORM_RECEIVER        = "receiver";
    public static final String ATT_FORM_SENDER        = "sender";
    public static final String ATT_FORM_MESSAGE        = "message";
    
    private String              result;
    private Map<String, String> errors      = new HashMap<String, String>();

     
    public String getResult() {
        return result;
    }
     
    public Map<String, String> getErrors() {
        return errors;
    }
    
    
    public Message CreateMessage(HttpServletRequest request) {
    	String receiver = getFieldValue( request, ATT_FORM_RECEIVER );
    	if(receiver == null)
    		return null;
    	String sender = getFieldValue( request, ATT_FORM_SENDER );
    	String content = getFieldValue( request, ATT_FORM_MESSAGE);
    	MessageService messageService = MessageService.getInstance();
    	UserService userService = UserService.getInstance();
    	
    	User userSender = userService.userSelect(Integer.parseInt(sender));
    	User userReceiver = userService.userSelect(Integer.parseInt(receiver));
    	Date created_date = new Date();
    	
    	Message message = new Message(0, content, userSender, userReceiver, created_date);
    	messageService.messageInsert(message);
    	return message;
    }
	
   
    
    /*
     * Add message to map error
     */
    private void setError( String champ, String message ) {
        errors.put( champ, message );
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
