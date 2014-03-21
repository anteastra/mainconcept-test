package com.mainconcept.cloud.handlers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.mainconcept.cloud.model.Message;
import com.mainconcept.cloud.model.Task;
import com.mainconcept.cloud.model.Message.MessageType;

/**
 * TCP realisation of machine
 * @author anteastra
 *
 */
public class TCPMachineHandler implements MachineHandler{
	
	private String host;
	private String name;
	private int port;
	
	private String result;

	/**
	 * @param host
	 * @param port
	 * @param name
	 */
	public TCPMachineHandler(String host, int port, String name) {
		this.host = host;
		this.port = port;	
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see com.mainconcept.cloud.handlers.MachineHandler#handleTask(com.mainconcept.cloud.model.Task)
	 */
	@Override
	public void handleTask(Task task) {
		try {
			@SuppressWarnings("resource")
			//TODO change localhost to parametr
			Socket s = new Socket(host, port);
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
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
				handleTaskStart(is, os, task);				
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
	
	/* (non-Javadoc)
	 * @see com.mainconcept.cloud.handlers.MachineHandler#getResult()
	 */
	@Override
	public String getResult() {
		return result;
	}
	
	private void handleTaskStart(ObjectInputStream is,
			ObjectOutputStream os, Task task) throws ClassNotFoundException, IOException {
		
		Message msg = (Message) is.readObject();
		switch (msg.getMessageType()) {
		case TASK_ERROR:
			System.out.println(msg.getMessage());
			result = "ERROR: \""+task.getName()+"\" on machine \""+name+"\"";
			break;
		case TASK_DONE:
			System.out.println(msg.getMessage());
			result = "DONE: \""+task.getName()+"\" on machine \""+name+"\"";
			break;
		default:
			break;			
		}
		
	}

}
