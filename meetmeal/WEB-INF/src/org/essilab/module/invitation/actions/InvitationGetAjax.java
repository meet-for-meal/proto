package org.essilab.module.invitation.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.invitation.InvitationService;
import org.essilab.module.invitation.model.Invitation;
import org.essilab.servlet.mvc.example.IAction;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InvitationGetAjax implements IAction{
	InvitationService service = InvitationService.getInstance();
	ObjectMapper mapper = new ObjectMapper();
	int id;
	
	public InvitationGetAjax(int id) {
		this.id = id;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType(HEADER_TYPE_JSON);
			Invitation invit = service.invitationSelect(id);
			if (invit != null) 
				mapper.writeValue(response.getOutputStream(), invit);
			else
				response.getWriter().println(RESPONSE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}
}
