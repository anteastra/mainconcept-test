package com.mainconcept.cloud;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.mainconcept.cloud.Task.Priority;
import com.mainconcept.cloud.machines.Message;
import com.mainconcept.cloud.machines.Message.MessageType;

public class CloudClientTest {
	
	public static void main(String...strings) {
		try {
			@SuppressWarnings("resource")
			Socket s = new Socket("localhost", 7401);
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
			Task task = new Task("task 1", 5, 5, Priority.Normal);
			os.writeObject(new Message(MessageType.REQUEST_PERFORM_TASK, null, task));
			
			ObjectInputStream is = new ObjectInputStream(s.getInputStream());
			Message msg = (Message) is.readObject();
			
			if (msg.getMessageType() == MessageType.MESSAGE) {
				System.out.println(msg.getMessage());
				msg = (Message) is.readObject();
			}
			
			switch (msg.getMessageType()) {
			case TASK_START:
				System.out.println(msg.getMessage());
				handleTaskStart(is, os);				
				break;
			case TASK_BUSY:
				System.out.println(msg.getMessage());
				break;
			default:
				System.out.println(msg.getMessage());
				break;
			}
			
			is.close();
			os.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void handleTaskStart(ObjectInputStream is,
			ObjectOutputStream os) throws ClassNotFoundException, IOException {
		
		Message msg = (Message) is.readObject();
		switch (msg.getMessageType()) {
		case TASK_ERROR:
			System.out.println(msg.getMessage());
			break;
		case TASK_DONE:
			System.out.println(msg.getMessage());
			break;
		default:
			break;			
		}
		
	}

}
