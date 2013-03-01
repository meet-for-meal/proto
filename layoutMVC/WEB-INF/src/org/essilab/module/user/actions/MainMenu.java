package org.essilab.module.user.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.user.model.User;
import org.essilab.servlet.mvc.example.IAction;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainMenu implements IAction {

	private Map<String, Object> addNode(List<Map<String, Object>> parent, String keyName, Object keyValue) {
		Map<String, Object> object = new HashMap<String, Object>();
		object.put(keyName, keyValue);
		if (null != parent);
		parent.add(object);
		return object;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> list = new ArrayList<>();
		/*addNode(list, "title", "Utilisateurs");
		addNode(list, "title", "Produits");
		addNode(list, "user", new User("Michael", "THOMAS"));
		
		try {
			mapper.writeValue(response.getOutputStream(), list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);*/
	}

}
