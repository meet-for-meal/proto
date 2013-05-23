package org.essilab.servlet.mvc.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.essilab.module.error.actions.ErrorAction;
import org.essilab.module.interest.actions.InterestDeleteAjax;
import org.essilab.module.interest.actions.InterestGetAjax;
import org.essilab.module.interest.actions.InterestInsertAjax;
import org.essilab.module.interest.actions.InterestListAjax;
import org.essilab.module.user.actions.MainMenu;
import org.essilab.module.user.actions.UserDeleteAjax;
import org.essilab.module.user.actions.UserGetAjax;
import org.essilab.module.user.actions.UserInsertAjax;
import org.essilab.module.user.actions.UserListAjax;
import org.essilab.module.user.actions.UserListDisplay;

public class FrontController extends HttpServlet {

	Map<String, IAction> actions = new HashMap<String, IAction>();
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		actions.put("ajax/users", new UserListAjax());
		actions.put("ajax/interests", new InterestListAjax(true));
		actions.put("ajax/interests/false", new InterestListAjax(false));
		actions.put("user/display", new UserListDisplay());
		actions.put("user/display.ajax", new UserListAjax());
		actions.put("user/insert.ajax", new UserInsertAjax());
		actions.put("user/mainmenu.ajax", new MainMenu());
		actions.put("error/error", new ErrorAction());
	}
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request
				.getRequestURI()
				.substring(request.getContextPath().length()+1);

		HttpSession session = request.getSession();
		if (url.trim().isEmpty()){
			if(session.getAttribute("sessionUser") != null){
				response.sendRedirect( request.getContextPath() + "/index" );
				return;
			}else{
				response.sendRedirect( request.getContextPath() + "/homepage" );
				return;
			}
		}
		request.setAttribute("url", url);

		IAction action = actions.get(url);
		
		if (null != action)
			action.execute(request, response);
		else {

				try {
					if (url.contains("ajax/user/")) {
						if (request.getMethod().equalsIgnoreCase("POST")) {
							action = new InterestInsertAjax();
						} else {
							int slashIndex = url.lastIndexOf('/');
							int endValue = Integer.parseInt(url.substring(slashIndex+1));
							if (endValue > 0) {
								if (request.getMethod().equalsIgnoreCase("GET")) {
									action = new UserGetAjax(endValue);
								} else if (request.getMethod().equalsIgnoreCase("PUT")) {
									action = new UserInsertAjax(endValue);
								} else if (request.getMethod().equalsIgnoreCase("DELETE")) {
									action = new UserDeleteAjax(endValue);
								}
								action.execute(request, response);
							}
						}
					} else if (url.contains("ajax/interest")) {
						if (request.getMethod().equalsIgnoreCase("POST")) {
							action = new InterestInsertAjax();
						} else {
							int slashIndex = url.lastIndexOf('/');
							int endValue = Integer.parseInt(url.substring(slashIndex+1));
							if (endValue > 0) {
								if (request.getMethod().equalsIgnoreCase("GET")) {
									action = new InterestGetAjax(endValue);
								} else if (request.getMethod().equalsIgnoreCase("PUT")) {
									action = new InterestInsertAjax();
								} else if (request.getMethod().equalsIgnoreCase("DELETE")) {
									action = new InterestDeleteAjax(endValue);
								}
								action.execute(request, response);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		if (null == request.getAttribute("render")) {
			if(url.substring(0,5).equals("admin")){
				request
				.getRequestDispatcher("/WEB-INF/views/admin/index.jsp")
				.include(request, response);
			}
			else{
				request
				.getRequestDispatcher(url.endsWith(".ajax") ? "/WEB-INF/views/"+url+".jsp" : "/layout.jsp")
				.include(request, response);				
			}
		}
	}

}
