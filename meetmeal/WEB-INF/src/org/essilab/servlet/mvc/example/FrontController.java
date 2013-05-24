package org.essilab.servlet.mvc.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.essilab.module.announce.actions.AnnounceDeleteAjax;
import org.essilab.module.announce.actions.AnnounceGetAjax;
import org.essilab.module.announce.actions.AnnounceListAjax;
import org.essilab.module.error.actions.ErrorAction;
import org.essilab.module.interest.InterestService;
import org.essilab.module.interest.actions.InterestDeleteAjax;
import org.essilab.module.interest.actions.InterestGetAjax;
import org.essilab.module.interest.actions.InterestInsertAjax;
import org.essilab.module.interest.actions.InterestListAjax;
import org.essilab.module.message.actions.MessageInsertAjax;
import org.essilab.module.interest.model.Interest;
import org.essilab.module.restaurant.actions.RestaurantDeleteAjax;
import org.essilab.module.restaurant.actions.RestaurantGetAjax;
import org.essilab.module.restaurant.actions.RestaurantListAjax;
import org.essilab.module.restaurant.actions.RestaurantPostAjax;
import org.essilab.module.user.actions.FriendDeleteAjax;
import org.essilab.module.user.actions.FriendInsertAjax;
import org.essilab.module.user.actions.MainMenu;
import org.essilab.module.user.actions.UserDeleteAjax;
import org.essilab.module.user.actions.UserGetAjax;
import org.essilab.module.user.actions.UserListAjax;
import org.essilab.module.user.actions.UserListDisplay;
import org.essilab.module.user.actions.UserPostAjax;
import org.essilab.module.user.model.User;

public class FrontController extends HttpServlet {
	public static final String ATT_SESSION_INTERESTS = "interests";
	private static final long serialVersionUID = 1L;
	Map<String, IAction> actions = new HashMap<String, IAction>();
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		actions.put("ajax/users", new UserListAjax());
		actions.put("ajax/interests", new InterestListAjax(true));
		actions.put("ajax/interests/false", new InterestListAjax(false));
		actions.put("ajax/restaurants", new RestaurantListAjax());
		actions.put("ajax/announces", new AnnounceListAjax());
		actions.put("user/display", new UserListDisplay());
		actions.put("user/display.ajax", new UserListAjax());
		actions.put("user/insert.ajax", new UserPostAjax(false));
		actions.put("user/mainmenu.ajax", new MainMenu());
		actions.put("error/error", new ErrorAction());
	}
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURI().substring(request.getContextPath().length()+1);

		HttpSession session = request.getSession();
		if(session.getAttribute("sessionUser") != null){
			InterestService interestService = InterestService.getInstance();
			User user = (User) session.getAttribute("sessionUser");
	        List<Interest>  interest = interestService.getInterestByUser(user);
	        
	        session.setAttribute(ATT_SESSION_INTERESTS, interest);
		}
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
		
		
		/* Handle ajax */
		if (null != action)
			action.execute(request, response);
		else {
			try {
				System.out.println(request.getMethod());
				if (url.contains("ajax/user")) {				//USER
					if (url.contains("ajax/user/update") || request.getMethod().equalsIgnoreCase("PUT")) {		
						action = new UserPostAjax(true);
					} else if (request.getMethod().equalsIgnoreCase("POST")) {
						action = new UserPostAjax(false);
					} else {
						int slashIndex = url.lastIndexOf('/');
						int endValue = Integer.parseInt(url.substring(slashIndex+1));
						if (endValue > 0) {
							if (request.getMethod().equalsIgnoreCase("GET")) {
								action = new UserGetAjax(endValue);
							} else if (request.getMethod().equalsIgnoreCase("DELETE")) {
								action = new UserDeleteAjax(endValue);
							}
						}
					}
					action.execute(request, response);
				} else if (url.contains("ajax/interest")) {		//INTEREST
					if (request.getMethod().equalsIgnoreCase("POST")) {
						action = new InterestInsertAjax();
					} else {
						int slashIndex = url.lastIndexOf('/');
						int endValue = Integer.parseInt(url.substring(slashIndex+1));
						if (endValue > 0) {
							if (request.getMethod().equalsIgnoreCase("GET")) {
								action = new InterestGetAjax(endValue);
							} else if (request.getMethod().equalsIgnoreCase("DELETE")) {
								action = new InterestDeleteAjax(endValue);
							}
						}
					}
					action.execute(request, response);
				} else if (url.contains("ajax/restaurant")) {	//RESTAURANT
					if (request.getMethod().equalsIgnoreCase("POST")) {
						action = new RestaurantPostAjax(false);
					} else if (request.getMethod().equalsIgnoreCase("PUT")) {
						action = new RestaurantPostAjax(true);
					} else {
						int slashIndex = url.lastIndexOf('/');
						int endValue = Integer.parseInt(url.substring(slashIndex+1));
						if (endValue > 0) {
							if (request.getMethod().equalsIgnoreCase("GET")) {
								action = new RestaurantGetAjax(endValue);
							} else if (request.getMethod().equalsIgnoreCase("DELETE")) {
								action = new RestaurantDeleteAjax(endValue);
							}
						}
					}
					action.execute(request, response);
				} else if (url.contains("ajax/announce")) {		//ANNOUNCE
					if (request.getMethod().equalsIgnoreCase("POST")) {
						action = new RestaurantPostAjax(false);
					} else if (request.getMethod().equalsIgnoreCase("PUT")) {
						action = new RestaurantPostAjax(true);
					} else {
						int slashIndex = url.lastIndexOf('/');
						int endValue = Integer.parseInt(url.substring(slashIndex+1));
						if (endValue > 0) {
							if (request.getMethod().equalsIgnoreCase("GET")) {
								action = new AnnounceGetAjax(endValue);
							} else if (request.getMethod().equalsIgnoreCase("DELETE")) {
								action = new AnnounceDeleteAjax(endValue);
							}
						}
					}
					action.execute(request, response);
				} else if (url.contains("ajax/message")) {		//MESSAGE
					if (request.getMethod().equalsIgnoreCase("POST")) 
						action = new MessageInsertAjax();
					action.execute(request, response);
				} else if (url.contains("ajax/friend")) {		//FRIEND
					if (request.getMethod().equalsIgnoreCase("POST")) {
						action = new FriendInsertAjax();
					} else if (request.getMethod().equalsIgnoreCase("DELETE")) {
						action = new FriendDeleteAjax();
					}
					action.execute(request, response);
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
