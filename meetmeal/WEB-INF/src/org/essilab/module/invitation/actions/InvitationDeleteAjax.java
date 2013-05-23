package org.essilab.module.invitation.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.invitation.InvitationService;
import org.essilab.servlet.mvc.example.IAction;

public class InvitationDeleteAjax implements IAction{
	InvitationService service = InvitationService.getInstance();
	int id;
	
	public InvitationDeleteAjax(int id) {
		this.id = id;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType(HEADER_TYPE_JSON);
			boolean ok = service.invitationDelete(id);
			response.getWriter().println(ok ? RESPONSE_OK : RESPONSE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}
}
