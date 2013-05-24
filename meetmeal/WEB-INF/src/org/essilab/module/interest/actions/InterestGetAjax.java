package org.essilab.module.interest.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.interest.InterestService;
import org.essilab.module.interest.model.Interest;
import org.essilab.servlet.mvc.example.IAction;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InterestGetAjax implements IAction{
	InterestService service = InterestService.getInstance();
	ObjectMapper mapper = new ObjectMapper();
	int id;
	
	public InterestGetAjax(int id) {
		this.id = id;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Interest interest = service.interestSelect(id);
		try {
			response.setContentType(HEADER_TYPE_JSON);
			if (interest != null) 
				mapper.writeValue(response.getOutputStream(), interest);
			else 
				response.getWriter().print(RESPONSE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}
}
