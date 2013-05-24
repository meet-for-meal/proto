package org.essilab.module.user.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.user.UserService;
import org.essilab.servlet.mvc.example.IAction;

public class FriendDeleteAjax implements IAction{
	UserService service = UserService.getInstance();
	
	public final static String FIELD_USER = "userId";
	public final static String FIELD_FRIEND = "friendId";
	int userId, friendId;
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType(HEADER_TYPE_JSON);
			readPost(request);
			boolean ok = service.friendDelete(userId, friendId);
			response.getWriter().println(ok ? RESPONSE_OK : RESPONSE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}
	
	public void readPost(HttpServletRequest request) {
		userId = (getFieldValue(request, FIELD_USER) != null) ? Integer.parseInt(getFieldValue(request, FIELD_USER)) : 0;
        friendId = (getFieldValue(request, FIELD_FRIEND) != null) ? Integer.parseInt(getFieldValue(request, FIELD_FRIEND)) : 0;
    }
    
    /*
     * Return field value or null
     */
    private static String getFieldValue( HttpServletRequest request, String fieldName ) {
        String valeur = request.getParameter( fieldName );
        System.out.println(valeur);
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }
    }
}
