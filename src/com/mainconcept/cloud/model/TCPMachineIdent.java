package com.mainconcept.cloud.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.mainconcept.cloud.helpers.Streamer;
import com.mainconcept.cloud.model.Message.MessageType;

public class TCPMachineIdent extends MachineIdent{
	
	private String host;
	private int port;
	private static final String MACHINE_CLASS = "com.mainconcept.cloud.machines.TCPMachine";

	public TCPMachineIdent(String name,String host, int port) {
		super(name);
		this.port = port;
		this.host = host;
	}

	@Override
	public String getMachineClass() {
		return MACHINE_CLASS;
	}

	@Override
	public String getStartupParametrs() {
		return getName()+" "+getPort();
	}
	
	public int getPort() {
		return port; 
	}

	@Override
	public void stopMachine() {
		try {
			System.out.println(getName()+": stop machine");
			
			@SuppressWarnings("resource")
			Socket s = new Socket(host, getPort());
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
			os.writeObject(new Message(MessageType.REQUEST_PERFORM_SHUTDOWN, null, null));								
			ObjectInputStream is = new ObjectInputStream(s.getInputStream());
			Message msg = (Message) is.readObject();
			
			System.out.println(msg.getMessage());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void startMachine() {
		try {
			Streamer error;
			Streamer output;
			Process process = Runtime.getRuntime().exec("java -cp CloudDispatcher.jar " +
					getMachineClass()+ " " +
					getStartupParametrs());
			error = new Streamer(process.getErrorStream());
			output = new Streamer(process.getInputStream());
			
			System.out.println(getName()+": start machine");
			
			error.start();
			output.start();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
