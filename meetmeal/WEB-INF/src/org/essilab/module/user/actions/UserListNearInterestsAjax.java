package org.essilab.module.user.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.user.UserService;
import org.essilab.module.user.model.User;
import org.essilab.servlet.mvc.example.IAction;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserListNearInterestsAjax implements IAction{
	UserService service = UserService.getInstance();
	ObjectMapper mapper = new ObjectMapper();
	int userId;
	
	public UserListNearInterestsAjax(int id) {
		this.userId = id;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<User> users = service.nearUsers(userId);
		for (User u : users)
			service.userInterests(u);
		try {
			response.setContentType(HEADER_TYPE_JSON);
			mapper.writeValue(response.getOutputStream(), users);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}
}
