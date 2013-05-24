package org.essilab.module.user.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.user.UserService;
import org.essilab.module.user.model.User;
import org.essilab.servlet.IAction;

public class UserListDisplay implements IAction {

	UserService service = UserService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		// TODO Get From Persistence Layer.
		List<User> users = service.userList();
		
		request.setAttribute("users", users);
		request.setAttribute("title", "Liste des utilisateurs");
	}

}
