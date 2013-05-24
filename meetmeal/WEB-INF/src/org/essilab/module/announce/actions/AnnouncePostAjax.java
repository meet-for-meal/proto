package org.essilab.module.announce.actions;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.announce.AnnounceService;
import org.essilab.module.announce.model.Announce;
import org.essilab.module.user.model.UserDao;
import org.essilab.servlet.mvc.example.IAction;

public class AnnouncePostAjax implements IAction{

	AnnounceService service = AnnounceService.getInstance();
	public final static String FIELD_ID = "id";
	public final static String FIELD_CREATOR = "creatorId";
	public final static String FIELD_CREATEDDATE = "createdDate";
	public final static String FIELD_DISPODATE = "disponibilityDate";
	public final static String FIELD_ISOPEN = "isOpen";
	public final static String FIELD_LATITUDE = "latitude";
	public final static String FIELD_LONGITUDE = "longitude";
	public final static String FIELD_MESSAGE = "message";
	
	boolean toUpdate = false;
	
	public AnnouncePostAjax(boolean update) {
		toUpdate = update;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean ok = false;
			Announce a = readPost(request);
			response.setContentType(HEADER_TYPE_JSON);
			if (a != null) {
				if (!toUpdate)
					ok = service.announceInsert(a);
				else
					ok = service.announceUpdate(a);
			}
			response.getWriter().println(ok ? RESPONSE_OK : RESPONSE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}

	public Announce readPost(HttpServletRequest request) {
		int id = (toUpdate && getFieldValue(request, FIELD_ID) != null) ? Integer.parseInt(getFieldValue(request, FIELD_ID)) : 0;
        int creator = (getFieldValue(request, FIELD_CREATOR) != null) ? Integer.parseInt(getFieldValue(request, FIELD_CREATOR)) : 1;
        Date created = (getFieldValue(request, FIELD_CREATEDDATE) != null) ? new Date(getFieldValue(request, FIELD_CREATEDDATE)) : null;
        Date dispo = (getFieldValue(request, FIELD_DISPODATE) != null) ? new Date(getFieldValue(request, FIELD_DISPODATE)) : null;
        boolean isOpen = (getFieldValue(request, FIELD_ISOPEN) != null) ? Boolean.parseBoolean(getFieldValue(request, FIELD_ISOPEN)) : false;
        double lat = (getFieldValue(request, FIELD_LATITUDE) != null) ? Double.parseDouble(getFieldValue(request, FIELD_LATITUDE)) : 0;
        double lng = (getFieldValue(request, FIELD_LATITUDE) != null) ? Double.parseDouble(getFieldValue(request, FIELD_LONGITUDE)) : 0;
        String msg = (getFieldValue(request, FIELD_MESSAGE) != null) ? getFieldValue(request, FIELD_MESSAGE) : "";
       	
        Announce announce = null;
		try {
			announce = new Announce(id,created,dispo,isOpen,lat,lng,msg,UserDao.getUser(creator));
		} catch (SQLException e) {
			e.printStackTrace();
		}
     
        return announce;
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
