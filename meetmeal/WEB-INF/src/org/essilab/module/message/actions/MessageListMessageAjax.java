package org.essilab.module.message.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.message.MessageService;
import org.essilab.module.message.model.Message;
import org.essilab.servlet.IAction;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageListMessageAjax implements IAction{
	MessageService service = MessageService.getInstance();
	ObjectMapper mapper = new ObjectMapper();
	int senderId, receiverId;
	
	public MessageListMessageAjax(int senderId, int receiverId) {
		this.senderId = senderId;
		this.receiverId = receiverId;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<Message> messages = service.messageListMessages(senderId, receiverId);
		try {
			response.setContentType(HEADER_TYPE_JSON);
			mapper.writeValue(response.getOutputStream(), messages);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}
}
