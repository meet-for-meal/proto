package org.essilab.module.announce.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.announce.AnnounceService;
import org.essilab.module.announce.model.Announce;
import org.essilab.servlet.mvc.example.IAction;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AnnounceGetAjax implements IAction{
	AnnounceService service = AnnounceService.getInstance();
	ObjectMapper mapper = new ObjectMapper();
	int id;
	
	public AnnounceGetAjax(int id) {
		this.id = id;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType(HEADER_TYPE_JSON);
			Announce announce = service.announceSelect(id);
			if (announce != null) 
				mapper.writeValue(response.getOutputStream(), announce);
			else
				response.getWriter().println(RESPONSE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}
}
