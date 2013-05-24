package org.essilab.module.restaurant.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.restaurant.RestaurantService;
import org.essilab.module.restaurant.model.Restaurant;
import org.essilab.servlet.mvc.example.IAction;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestaurantGetAjax implements IAction{
	RestaurantService service = RestaurantService.getInstance();
	ObjectMapper mapper = new ObjectMapper();
	int id;
	
	public RestaurantGetAjax(int id) {
		this.id = id;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType(HEADER_TYPE_JSON);
			Restaurant resto = service.restaurantSelect(id);
			if (resto != null) 
				mapper.writeValue(response.getOutputStream(), resto);
			else
				response.getWriter().println(RESPONSE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}
}
