package org.essilab.module.user.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.user.UserService;
import org.essilab.module.user.model.User;
import org.essilab.servlet.mvc.example.IAction;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserAjax implements IAction{
	UserService service = UserService.getInstance();
	ObjectMapper mapper = new ObjectMapper();
	int id;
	
	public UserAjax(int id) {
		this.id = id;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Get From Persistence Layer.
		try {
			User user = service.userSelect(id);
			response.setContentType("text/x-javascript;charset=UTF-8");
			mapper.writeValue(response.getOutputStream(), user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}
}
