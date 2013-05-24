package org.essilab.module.restaurant.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.restaurant.RestaurantService;
import org.essilab.servlet.IAction;

public class RestaurantDeleteAjax implements IAction{
	RestaurantService service = RestaurantService.getInstance();
	int id;
	
	public RestaurantDeleteAjax(int id) {
		this.id = id;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType(HEADER_TYPE_JSON);
			boolean ok = service.restaurantDelete(id);
			response.getWriter().println(ok ? RESPONSE_OK : RESPONSE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}
}
