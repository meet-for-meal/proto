package org.essilab.module.restaurant.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.restaurant.RestaurantService;
import org.essilab.module.restaurant.model.Restaurant;
import org.essilab.servlet.IAction;

public class RestaurantPostAjax implements IAction{

	RestaurantService service = RestaurantService.getInstance();
	public final static String FIELD_ID = "id";
	public final static String FIELD_NAME = "name";
	public final static String FIELD_LATITUDE = "latitude";
	public final static String FIELD_LONGITUDE = "longitude";
	public final static String FIELD_FOURSQUARE = "foursquare_id";
	public final static String FIELD_PARTNERSHIP = "partnership";
	public final static String FIELD_URLIMAGE = "urlImage";
	public final static String FIELD_TITLEIMAGE = "titleImage";
	public final static String FIELD_DESCIMAGE = "descImage";
	
	boolean toUpdate = false;
	
	public RestaurantPostAjax(boolean update) {
		toUpdate = update;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean ok = false;
			Restaurant resto = readPost(request);
			response.setContentType(HEADER_TYPE_JSON);
			if (resto != null) {
				if (toUpdate)
					ok = service.restaurantUpdate(resto);
				else 
					ok = service.restaurantInsert(resto);
			}
			response.getWriter().println(ok ? RESPONSE_OK : RESPONSE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}

	public Restaurant readPost(HttpServletRequest request) {
		int id = (toUpdate && getFieldValue(request, FIELD_ID) != null) ? Integer.parseInt(getFieldValue(request, FIELD_ID)) : 0;
        String name = getFieldValue(request, FIELD_NAME);
        double lat = Double.parseDouble(getFieldValue(request, FIELD_LATITUDE));
        double lng = Double.parseDouble(getFieldValue(request, FIELD_LONGITUDE));
        String foursquare = getFieldValue(request, FIELD_FOURSQUARE);
        int partnership = Integer.parseInt(getFieldValue(request, FIELD_PARTNERSHIP));
        String url = getFieldValue(request, FIELD_URLIMAGE);
        String title = getFieldValue(request, FIELD_TITLEIMAGE);
        String desc = getFieldValue(request, FIELD_DESCIMAGE);
       	Restaurant resto = new Restaurant(id,name,lat,lng,foursquare,partnership,url,title,desc);
     
        return resto;
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
