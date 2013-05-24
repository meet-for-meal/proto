package org.essilab.module.user.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.user.UserService;
import org.essilab.module.user.model.User;
import org.essilab.servlet.IAction;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserListFriendsAjax implements IAction{
	UserService service = UserService.getInstance();
	ObjectMapper mapper = new ObjectMapper();
	int userId;
	
	public UserListFriendsAjax(int id) {
		this.userId = id;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<User> friends = service.userFriends(userId);
		try {
			response.setContentType(HEADER_TYPE_JSON);
			mapper.writeValue(response.getOutputStream(), friends);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}
}
