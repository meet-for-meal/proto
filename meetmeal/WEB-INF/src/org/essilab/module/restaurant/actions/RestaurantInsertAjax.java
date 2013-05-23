package org.essilab.module.restaurant.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.restaurant.RestaurantService;
import org.essilab.module.restaurant.model.Restaurant;
import org.essilab.servlet.mvc.example.IAction;

public class RestaurantInsertAjax implements IAction{

	RestaurantService service = RestaurantService.getInstance();
	public final static String FIELD_NAME = "name";
	public final static String FIELD_LASTNAME = "lastname";
	public final static String FIELD_EMAIL = "email";
	public final static String FIELD_PASSWORD = "password";
	public final static String FIELD_AGE = "age";
	public final static String FIELD_GENDER = "gender";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean ok = false;
			Restaurant resto = readPost(request);
			response.setContentType(HEADER_TYPE_JSON);
			if (resto != null)
				ok = service.restaurantInsert(resto);
			response.getWriter().println(ok ? RESPONSE_OK : RESPONSE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}

	public Restaurant readPost(HttpServletRequest request) {
        String name = getFieldValue(request, FIELD_NAME);
        String lastname = getFieldValue(request, FIELD_LASTNAME);
        String email = getFieldValue(request, FIELD_EMAIL);
        String password = getFieldValue(request, FIELD_PASSWORD);
        int age = Integer.parseInt(getFieldValue(request, FIELD_AGE));
        int gender = Integer.parseInt(getFieldValue(request, FIELD_GENDER));
//       	Restaurant resto = new Restaurant(0,lastname,name,age,email,password,gender,true,false);
     
        return null;
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
