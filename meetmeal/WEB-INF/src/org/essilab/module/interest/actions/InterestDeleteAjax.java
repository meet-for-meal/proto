package org.essilab.module.interest.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.interest.InterestService;
import org.essilab.servlet.mvc.example.IAction;

public class InterestDeleteAjax implements IAction{
	InterestService service = InterestService.getInstance();
	int id;
	
	public InterestDeleteAjax(int id) {
		this.id = id;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType(HEADER_TYPE_JSON);
			boolean ok = service.interestDelete(id);
			response.getWriter().print(ok ? RESPONSE_OK : RESPONSE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}
}
