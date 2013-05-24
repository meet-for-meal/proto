package org.essilab.module.announce.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.announce.AnnounceService;
import org.essilab.servlet.IAction;

public class AnnounceDeleteAjax implements IAction{
	AnnounceService service = AnnounceService.getInstance();
	int id;
	
	public AnnounceDeleteAjax(int id) {
		this.id = id;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType(HEADER_TYPE_JSON);
			boolean ok = service.announceDelete(id);
			response.getWriter().println(ok ? RESPONSE_OK : RESPONSE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}
}
