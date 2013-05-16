package org.essilab.module.message.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.essilab.module.connect.Connect;
import org.essilab.module.user.model.User;

public class MessageDao {

	//List conversation with people for an User
	public static List<Message> findListConversation(int senderId) throws SQLException {
		/* http://stackoverflow.com/questions/11095024/select-the-latest-message-between-the-communication-of-two-user-in-mysql
		  	SELECT M.* FROM 
				(SELECT MAX(M.id) AS message_id 
			         FROM Message M
			         WHERE 1 IN (senderId, receiverId)
			         GROUP BY IF (1 = senderId, receiverId, senderId)) AS latest
			LEFT JOIN Message USING(message_id)
		 */
		
		/*  GOOD
		  	SELECT * FROM 
				(SELECT MAX(createdDate) AS createdDate 
			         FROM Message M 
			         WHERE 1 IN (senderId,receiverId)
			         GROUP BY IF (1 = senderId,receiverId,senderId)) AS latest
			LEFT JOIN Message M ON latest.createdDate = M.createdDate AND 1 IN (M.senderId, M.receiverId)
			GROUP BY IF (1 = M.senderId,M.receiverId,M.senderId)
		 */
		
		String request = 
			"SELECT M.id, M.createdDate, M.senderId sid, M.receiverId rid, M.content, " +
				"S.firstname sfirstname, S.lastname slastname, S.email semail, " +
				"R.firstname rfirstname, R.lastname rlastname, R.email remail "+
			"FROM (" +
				"SELECT MAX(createdDate) AS createdDate "+
				"FROM Message M "+
				"WHERE "+senderId+" IN (senderId,receiverId) "+
				"GROUP BY IF ("+senderId+" = senderId,receiverId,senderId)) AS latest "+
			"LEFT JOIN Message M ON latest.createdDate = M.createdDate AND "+senderId+" IN (M.senderId, M.receiverId) "+
			"INNER JOIN User S ON S.id = M.senderId "+
			"INNER JOIN User R ON R.id = M.receiverId "+
			"GROUP BY IF ("+senderId+" = M.senderId,M.receiverId,M.senderId)";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(request);
		ResultSet result = ps.executeQuery();
		Connect.getConnection().close();
		
		return MessageDao.createMessages(result);
	}
	
	//List Messages for a conversation between 2 User
	public static List<Message> findListMessage(int userId1, int userId2) throws SQLException {
		/*
		  	SELECT M.createdDate, M.senderId sid, M.receiverId rid, M.content, S.firstname sfirstname, S.lastname slastname, S.email semail, R.firstname rfirstname, R.lastname rlastname, R.email remail
			FROM Message M
			INNER JOIN User S ON S.id = M.senderId
			INNER JOIN User R ON R.id = M.receiverId
			WHERE senderId =1 AND receiverId = 2
            OR (senderId = 2 AND receiverId = 1)
			ORDER BY M.createdDate ASC ;
		*/
		String request = "SELECT M.id, M.createdDate, M.senderId sid, M.receiverId rid, M.content, " +
				"S.firstname sfirstname, S.lastname slastname, S.email semail, " +
				"R.firstname rfirstname, R.lastname rlastname, R.email remail "+
				"FROM Message M "+
				"INNER JOIN User S ON S.id = M.senderId "+
				"INNER JOIN User R ON R.id = M.receiverId "+
				"WHERE senderId = "+userId1+" AND receiverId = "+userId2+" "+
				"OR (senderId = "+userId2+" AND receiverId = "+userId1+") "+
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
				Message msg = new Message(result.getInt("id"), result.getString("content"), result.getDate("createdDate"));	
				msg.setSender(new User(result.getInt("sid"), result.getString("slastname"), result.getString("sfirstname"),	result.getString("semail")));
				msg.setReceiver(new User(result.getInt("rid"), result.getString("rlastname"), result.getString("rfirstname"), result.getString("remail")));
				messages.add(msg);	
			}
		} catch (SQLException e) { e.printStackTrace();	}
		return messages;
	}
	
	
	//MAIN TEST
	public static void main(String[] args)  {
		try {
			List<Message> items = findListConversation(1);
			for (Message i : items) 
				System.out.println(i.getSentDate()+" : "+i.getUser1().getFirstname()+" "+i.getUser1().getLastname()+" ˆ "+i.getUser2().getFirstname()+" "+i.getUser2().getLastname()+"\nLast message : "+i.getContent());
			System.out.println("Nb conversation = "+items.size()+"\n");
			
			items = findListMessage(1, 2);
			for (Message i : items) 
				System.out.println(i.getSentDate()+" : "+i.getUser1().getFirstname()+" "+i.getUser1().getLastname()+" ˆ "+i.getUser2().getFirstname()+" "+i.getUser2().getLastname()+"\nMessage : "+i.getContent());
			System.out.println("Nb message = "+items.size()+"\n");
			
		} catch (SQLException e) { e.printStackTrace(); }
	}

}
