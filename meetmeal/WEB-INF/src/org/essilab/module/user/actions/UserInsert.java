package org.essilab.module.user.actions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.user.UserService;
import org.essilab.module.user.model.User;
import org.essilab.servlet.mvc.example.IAction;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserInsert implements IAction{

	UserService service = UserService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		/*String json = request.getParameter("json");
		ObjectMapper mapper = new ObjectMapper();
		try {
			User u = mapper.readValue(json, User.class);
			
			service.userInsert(u);
			
			response.getWriter().print("{\"success\":true}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);*/
	}

}
