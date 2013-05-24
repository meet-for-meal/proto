package org.essilab.module.interest.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.interest.InterestService;
import org.essilab.module.interest.model.Interest;
import org.essilab.module.user.UserService;
import org.essilab.servlet.IAction;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InterestListAjax implements IAction{
	InterestService service = InterestService.getInstance();
	ObjectMapper mapper = new ObjectMapper();
	boolean withId = true;
	
	public InterestListAjax(boolean wi) {
		this.withId = wi;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<Interest> interests = service.interestList();
		try {
			response.setContentType(HEADER_TYPE_JSON);
			if (withId) {
				mapper.writeValue(response.getOutputStream(), interests);
			} else {
				List<String> listInterest = new ArrayList<String>();
				for (Interest i : interests)
					listInterest.add(i.getTag());
				mapper.writeValue(response.getOutputStream(), listInterest);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}
}
