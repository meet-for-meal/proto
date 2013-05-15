package org.essilab.module.error.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.servlet.mvc.example.IAction;

public class ErrorAction implements IAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(404);
	}

}
