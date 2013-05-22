package org.essilab.module.message.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.message.MessageService;
import org.essilab.module.message.model.Message;
import org.essilab.servlet.mvc.example.IAction;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageListConversationAjax implements IAction{
	MessageService service = MessageService.getInstance();
	ObjectMapper mapper = new ObjectMapper();
	int senderId;
	
	public MessageListConversationAjax(int senderId) {
		this.senderId = senderId;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<Message> messages = service.messageListConversation(senderId);
		try {
			response.setContentType("text/x-javascript;charset=UTF-8");
			mapper.writeValue(response.getOutputStream(), messages);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}
}
