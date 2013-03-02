package org.essilab.servlet.mvc.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.error.actions.ErrorAction;
import org.essilab.module.user.actions.MainMenu;
import org.essilab.module.user.actions.UserInsert;
import org.essilab.module.user.actions.UserListAjax;
import org.essilab.module.user.actions.UserListDisplay;

public class FrontController extends HttpServlet {

	Map<String, IAction> actions = new HashMap<>();
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		actions.put("user/display", new UserListDisplay());
		actions.put("user/display.ajax", new UserListAjax());
		actions.put("user/insert.ajax", new UserInsert());
		actions.put("user/mainmenu.ajax", new MainMenu());
		actions.put("error/error", new ErrorAction());
	}
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request
				.getRequestURI()
				.substring(request.getContextPath().length()+1);


		if (url.trim().isEmpty())
			url = "index/index";


		request.setAttribute("url", url);
		
		IAction action = actions.get(url);
		if (null != action)
			action.execute(request, response);
		
		if (null == request.getAttribute("render"))
			request
			.getRequestDispatcher(url.endsWith(".ajax") ? "/WEB-INF/views/"+url+".jsp" : "/layout.jsp")
			.include(request, response);

	}

}
