package org.essilab.module.user.actions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.user.UserService;
import org.essilab.servlet.mvc.example.IAction;

public class UserUpdateLocalizationAjax implements IAction{

	UserService service = UserService.getInstance();
	public final static String FIELD_ID = "id";
	public final static String FIELD_LASTPOSITION = "lastPosition";
	public final static String FIELD_LATITUDE = "latitude";
	public final static String FIELD_LONGITUDE = "longitude";
	
	int id;
	Date lastPos; 
	double lat, lng;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean ok = false;
			readPost(request);
			response.setContentType(HEADER_TYPE_JSON);
			ok = service.userUpdateLocalization(id, lastPos, lat, lng);
			response.getWriter().println(ok ? RESPONSE_OK : RESPONSE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}

	public void readPost(HttpServletRequest request) {
		id = (getFieldValue(request, FIELD_ID) != null) ? Integer.parseInt(getFieldValue(request, FIELD_ID)) : 0;
        lastPos = new Date(getFieldValue(request, FIELD_LASTPOSITION));
        lat = Double.parseDouble(getFieldValue(request, FIELD_LATITUDE));
        lng = Double.parseDouble(getFieldValue(request, FIELD_LONGITUDE));
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
