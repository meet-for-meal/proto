package org.essilab.module.restaurant.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.restaurant.RestaurantService;
import org.essilab.module.restaurant.model.Restaurant;
import org.essilab.servlet.mvc.example.IAction;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestaurantListAjax implements IAction{
	RestaurantService service = RestaurantService.getInstance();
	ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<Restaurant> restos = service.restaurantList();
		try {
			response.setContentType(HEADER_TYPE_JSON);
			mapper.writeValue(response.getOutputStream(), restos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}
}
