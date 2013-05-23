package org.essilab.module.user.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.user.UserService;
import org.essilab.module.user.model.User;
import org.essilab.servlet.mvc.example.IAction;

public class UserInsertAjax implements IAction{

	UserService service = UserService.getInstance();
	public final static String FIELD_FIRSTNAME = "firstname";
	public final static String FIELD_LASTNAME = "lastname";
	public final static String FIELD_EMAIL = "email";
	public final static String FIELD_PASSWORD = "password";
	public final static String FIELD_AGE = "age";
	public final static String FIELD_GENDER = "gender";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean ok = false;
			User u = readPost(request);
			response.setContentType(HEADER_TYPE_JSON);
			if (u != null)
				ok = service.userInsert(u);
			response.getWriter().println(ok ? RESPONSE_OK : RESPONSE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}

	public User readPost(HttpServletRequest request) {
        String firstname = getFieldValue(request, FIELD_FIRSTNAME);
        String lastname = getFieldValue(request, FIELD_LASTNAME);
        String email = getFieldValue(request, FIELD_EMAIL);
        String password = getFieldValue(request, FIELD_PASSWORD);
        int age = Integer.parseInt(getFieldValue(request, FIELD_AGE));
        int gender = Integer.parseInt(getFieldValue(request, FIELD_GENDER));
       	User u = new User(0,lastname,firstname,age,email,password,gender,true,false);
     
        return u;
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
