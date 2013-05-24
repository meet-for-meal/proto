package org.essilab.module.restaurant.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.interest.model.Interest;
import org.essilab.module.restaurant.RestaurantService;
import org.essilab.module.restaurant.model.Restaurant;
import org.essilab.servlet.mvc.example.IAction;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestaurantListAjax implements IAction{
	RestaurantService service = RestaurantService.getInstance();
	ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<Restaurant> restaurants = service.restaurantList();
		try {
			response.setContentType(HEADER_TYPE_JSON);
			//List<String> listResto = new ArrayList<String>();
			//for (Restaurant r : restaurants)
			//	listResto.add(r.getFoursquareId());
			//mapper.writeValue(response.getOutputStream(), listResto);
			mapper.writeValue(response.getOutputStream(), restaurants);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}
}
