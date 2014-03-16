package com.mainconcept.cloud.machines;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.mainconcept.cloud.machines.Message.MessageType;

public class TCPMachineServer extends Thread{
	
	private TCPMachine machine;
	private Socket socket;
	
	public TCPMachineServer(TCPMachine machine, Socket socket){
		this.machine = machine;
		this.socket = socket;
		
		setDaemon(true);
		setPriority(NORM_PRIORITY);
		start();
	}
	
	@Override
	public void run() {
		try {
			
			ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
			
			Message msg = (Message) is.readObject();
			
			switch (msg.getMessageType()) {
			
			case REQUEST_PERFORM_SHUTDOWN:
				machine.shutdownMessage(os);
				socket.close();
				System.exit(0);
				break;
			
			case REQUEST_PERFORM_TASK:
				if (!machine.performTask(msg.getTask(), os)) {
					os.writeObject(new Message(MessageType.TASK_BUSY, machine.getBusyString(), null));
					System.out.println(machine.getBusyString());
				}
				os.close();
				break;
			default:
				break;				
			}
			
			socket.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
