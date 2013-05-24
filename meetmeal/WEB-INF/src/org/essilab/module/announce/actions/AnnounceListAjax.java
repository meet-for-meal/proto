package org.essilab.module.announce.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.announce.AnnounceService;
import org.essilab.module.announce.model.Announce;
import org.essilab.servlet.IAction;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AnnounceListAjax implements IAction{
	AnnounceService service = AnnounceService.getInstance();
	ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<Announce> announces = service.announceList();
		try {
			response.setContentType(HEADER_TYPE_JSON);
			mapper.writeValue(response.getOutputStream(), announces);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}
}
