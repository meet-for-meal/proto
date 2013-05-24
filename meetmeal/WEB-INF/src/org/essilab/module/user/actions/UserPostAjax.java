package org.essilab.module.user.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.user.UserService;
import org.essilab.module.user.model.User;
import org.essilab.module.user.model.UserDao;
import org.essilab.servlet.mvc.example.IAction;

public class UserPostAjax implements IAction{

	UserService service = UserService.getInstance();
	public final static String FIELD_ID = "id";
	public final static String FIELD_FIRSTNAME = "firstname";
	public final static String FIELD_LASTNAME = "lastname";
	public final static String FIELD_EMAIL = "email";
	public final static String FIELD_PASSWORD = "password";
	public final static String FIELD_AGE = "age";
	public final static String FIELD_GENDER = "gender";
	public final static String FIELD_ISADMIN = "isAdmin";
	
	boolean toUpdate = false;
	
	public UserPostAjax(boolean update) {
		toUpdate = update;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean ok = false;
			User u = readPost(request);
			response.setContentType(HEADER_TYPE_JSON);
			if (u != null) {
				if (toUpdate)
					ok = service.userUpdate(u);
				else 
					ok = service.userInsert(u);
			}
			response.getWriter().println(ok ? RESPONSE_OK : RESPONSE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}

	public User readPost(HttpServletRequest request) {
		int id = (toUpdate && getFieldValue(request, FIELD_ID) != null) ? Integer.parseInt(getFieldValue(request, FIELD_ID)) : 0;
        String firstname = getFieldValue(request, FIELD_FIRSTNAME);
        String lastname = getFieldValue(request, FIELD_LASTNAME);
        String email = getFieldValue(request, FIELD_EMAIL);
        String password = getFieldValue(request, FIELD_PASSWORD);
        int age = (getFieldValue(request, FIELD_AGE) != null) ? Integer.parseInt(getFieldValue(request, FIELD_AGE)) : 0;
        int gender = (getFieldValue(request, FIELD_GENDER) != null) ? Integer.parseInt(getFieldValue(request, FIELD_GENDER)) : 0;
        boolean isAdmin = (getFieldValue(request, FIELD_ISADMIN) != null) ? Boolean.parseBoolean(getFieldValue(request, FIELD_ISADMIN)) : false;
        
        return new User(id,lastname,firstname,age,email,password,gender, false, isAdmin);
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
