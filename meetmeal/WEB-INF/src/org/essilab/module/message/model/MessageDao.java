package org.essilab.module.message.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.essilab.module.connect.Connect;
import org.essilab.module.user.model.User;
import org.essilab.module.user.model.UserDao;

public class MessageDao {

	//Insert
	public static boolean insert(Message msg) throws SQLException{
		boolean ok = false;
		if (msg != null && msg.getSender() != null && msg.getReceiver() != null) {
			String request = "INSERT INTO Message VALUES (NULL, "+
				" "+ msg.getSender().getId() +"," +
				" "+ msg.getReceiver().getId() +"," +
				" \""+ msg.getContent() +"\"," +
				" "+ (msg.getSentDate() != null ? "'"+new java.sql.Date(msg.getSentDate().getTime())+" "+new java.sql.Time(msg.getSentDate().getTime())+"'" : "NULL")+")";
			System.out.println(request+"\n");
			PreparedStatement ps = Connect.getConnection().prepareStatement(request);
			ps.executeUpdate();
			Connect.getConnection().close();
			ok = true;
		}
		return ok;
	}
	
	//List conversation with people for an User
	public static List<Message> findListConversation(int senderId) throws SQLException {
		/* http://stackoverflow.com/questions/11095024/select-the-latest-message-between-the-communication-of-two-user-in-mysql */
		
		String request = 
			"SELECT M.id, M.createdDate, M.senderId sid, M.receiverId rid, M.content " +
			"FROM (" +
				"SELECT MAX(createdDate) AS createdDate "+
				"FROM Message M "+
				"WHERE "+senderId+" IN (senderId, receiverId) "+
				"GROUP BY IF ("+senderId+" = senderId,receiverId,senderId)) AS latest "+
			"LEFT JOIN Message M ON latest.createdDate = M.createdDate AND "+senderId+" IN (M.senderId, M.receiverId) "+
			"GROUP BY IF ("+senderId+" = M.senderId,M.receiverId,M.senderId) ";
		
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return MessageDao.createMessages(result);
	}
	
	//List Messages for a conversation between 2 User
	public static List<Message> findListMessage(int senderId, int receiverId) throws SQLException {
		String request = "SELECT M.id, M.createdDate, M.senderId sid, M.receiverId rid, M.content " +
				"FROM Message M "+
				"WHERE senderId = "+senderId+" AND receiverId = "+receiverId+" "+
				"OR (senderId = "+receiverId+" AND receiverId = "+senderId+") "+
				"ORDER BY M.createdDate ASC;";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return MessageDao.createMessages(result);
	}
	
	//Create Messages
	private static List<Message> createMessages(ResultSet result) {
		List<Message> messages = new ArrayList<Message>();
		try {
			while (result.next()) {
				Message msg = new Message(result.getInt("id"), result.getString("content"), result.getTimestamp("createdDate"));	
				msg.setSender(UserDao.getUser(result.getInt("sid")));
				msg.setReceiver(UserDao.getUser(result.getInt("rid")));
				messages.add(msg);	
			}
		} catch (SQLException e) { e.printStackTrace();	}
		return messages;
	}
	
	
	//MAIN TEST
	public static void main(String[] args)  {
		try {
			//Insert
			Message msg = new Message(0, "Tu veux venir ?", UserDao.getUser(1), UserDao.getUser(2), new Date());
//			insert(msg);
			
			//Find List Conversation
			List<Message> items = findListConversation(1);
			for (Message i : items) 
				System.out.println(i.getSentDate()+" : "+i.getSender().getFirstname()+" "+i.getSender().getLastname()+" ˆ "+i.getReceiver().getFirstname()+" "+i.getReceiver().getLastname()+"\nLast message : "+i.getContent());
			System.out.println("Nb conversation = "+items.size()+"\n");
			
			//Find List Message
			items = findListMessage(1, 2);
			for (Message i : items) 
				System.out.println(i.getSentDate()+" : "+i.getSender().getFirstname()+" "+i.getSender().getLastname()+" ˆ "+i.getReceiver().getFirstname()+" "+i.getReceiver().getLastname()+"\nMessage : "+i.getContent());
			System.out.println("Nb message = "+items.size()+"\n");
			
		} catch (SQLException e) { e.printStackTrace(); }
	}

}
