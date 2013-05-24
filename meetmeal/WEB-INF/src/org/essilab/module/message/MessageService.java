package org.essilab.module.message;

import java.sql.SQLException;
import java.util.List;

import org.essilab.module.message.model.Message;
import org.essilab.module.message.model.MessageDao;

public class MessageService {

	private static MessageService instance = null;
	private MessageService() { }
	public static MessageService getInstance(){
		if ( null == instance)
			instance = new MessageService();
		return instance;
	}
	
	public List<Message> messageListConversation(int senderId) {
		try {
			return MessageDao.findListConversation(senderId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Message> messageListMessages(int senderId, int receiverId) {
		try {
			return MessageDao.findListMessage(senderId, receiverId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean messageInsert(Message m){
		try {
			return MessageDao.insert(m);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
