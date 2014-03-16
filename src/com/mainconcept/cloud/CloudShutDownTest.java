package com.mainconcept.cloud;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.mainconcept.cloud.Task.Priority;
import com.mainconcept.cloud.machines.Message;
import com.mainconcept.cloud.machines.Message.MessageType;

public class CloudShutDownTest {

	public static void main(String...strings) {
		try {
			Socket s = new Socket("localhost", 7401);
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
			os.writeObject(new Message(MessageType.REQUEST_PERFORM_SHUTDOWN, null, null));
			
			ObjectInputStream is = new ObjectInputStream(s.getInputStream());
			Message msg = (Message) is.readObject();
			
			System.out.println(msg.getMessage());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		try {
			Socket s = new Socket("localhost", 7402);
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
			os.writeObject(new Message(MessageType.REQUEST_PERFORM_SHUTDOWN, null, null));
			
			ObjectInputStream is = new ObjectInputStream(s.getInputStream());
			Message msg = (Message) is.readObject();
			
			System.out.println(msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Socket s = new Socket("localhost", 7403);
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
			os.writeObject(new Message(MessageType.REQUEST_PERFORM_SHUTDOWN, null, null));
			
			ObjectInputStream is = new ObjectInputStream(s.getInputStream());
			Message msg = (Message) is.readObject();
			
			System.out.println(msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
	}
}
