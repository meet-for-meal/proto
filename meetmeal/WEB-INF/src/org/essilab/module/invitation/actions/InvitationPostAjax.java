package org.essilab.module.invitation.actions;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.announce.model.Announce;
import org.essilab.module.announce.model.AnnounceDao;
import org.essilab.module.invitation.InvitationService;
import org.essilab.module.invitation.model.Invitation;
import org.essilab.module.user.model.UserDao;
import org.essilab.servlet.mvc.example.IAction;

public class InvitationPostAjax implements IAction{

	InvitationService service =InvitationService.getInstance();
	public final static String FIELD_ID = "id";
	public final static String FIELD_SENDER = "senderId";
	public final static String FIELD_ANNOUNCE = "announceId";
	public final static String FIELD_CREATEDDATE = "createdDate";
	public final static String FIELD_ISACCEPTED = "isAccepted";
	public final static String FIELD_ISCONFIRMED = "isConfirmed";
	public final static String FIELD_ISOPEN = "isOpen";
	public final static String FIELD_MESSAGE = "message";
	
	boolean toUpdate = false;
	
	public InvitationPostAjax(boolean update) {
		toUpdate = update;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean ok = false;
			Invitation i = readPost(request);
			response.setContentType(HEADER_TYPE_JSON);
			if (i != null) {
				if (toUpdate)
					ok = service.invitationUpdate(i);
				else
					ok = service.invitationInsert(i);
			}
			response.getWriter().println(ok ? RESPONSE_OK : RESPONSE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}

	public Invitation readPost(HttpServletRequest request) {
		int id = (toUpdate && getFieldValue(request, FIELD_ID) != null) ? Integer.parseInt(getFieldValue(request, FIELD_ID)) : 0;
        int sender = Integer.parseInt(getFieldValue(request, FIELD_SENDER));
        int announce = Integer.parseInt(getFieldValue(request, FIELD_ANNOUNCE));
        Date created = new Date(getFieldValue(request, FIELD_CREATEDDATE));
        boolean isAccepted = Boolean.parseBoolean(getFieldValue(request, FIELD_ISACCEPTED));
        boolean isConfirmed = Boolean.parseBoolean(getFieldValue(request, FIELD_ISCONFIRMED));
        boolean isOpen = Boolean.parseBoolean(getFieldValue(request, FIELD_ISOPEN));
//        double lat = Double.parseDouble(getFieldValue(request, FIELD_LATITUDE));
//        double lng = Double.parseDouble(getFieldValue(request, FIELD_LONGITUDE));
        String msg = getFieldValue(request, FIELD_MESSAGE);
        
       	Invitation invit = null;
		try {
			invit = new Invitation(id,created,isAccepted,isConfirmed,isOpen,msg,UserDao.getUser(sender),AnnounceDao.getAnnounce(announce));
		} catch (SQLException e) {
			e.printStackTrace();
		}
     
        return invit;
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
