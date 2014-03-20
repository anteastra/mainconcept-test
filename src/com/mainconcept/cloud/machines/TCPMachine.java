package com.mainconcept.cloud.machines;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;

import com.mainconcept.cloud.model.Message;
import com.mainconcept.cloud.model.Message.MessageType;

public class TCPMachine extends Machine{
	
	public static String DEFAULT_HOST = "localhost"; 
	
	private String host;
	private int port;

	public TCPMachine(String name, int port) {
		super(name);
		if (port < 0) {
			throw new IllegalArgumentException(getTag()+"cannot create tcp machine with " 
				+ port + " port, less than 0");
		}
		
		if (port > 65535) {
			throw new IllegalArgumentException(getTag()+"cannot create tcp machine with " 
					+ port + " port, more than 65535");
		}
		
		this.port = port;
		host = DEFAULT_HOST;
		
		startupServer();
	}
	
	public TCPMachine(String name, int port, String host) {
		this(name, port);
		
		if (host == null) {
			throw new IllegalArgumentException(getTag()+"cannot create tcp machine with host: null");
		}
		
		this.host = host;
	}

	private void startupServer() {
		try {
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(port, 0,
					InetAddress.getByName(host));
			System.out.println(getTag()+"machine \""+getName()+"\" started");
			while(true)	{
				new TCPMachineServer(this, server.accept());
			}
			
		} catch(Exception e) {
			System.out.println(getTag()+"tcp machine init error: "+e);
		} 
	}

	@Override
	public void submitStart(ObjectOutputStream os) {
		try {
			os.writeObject(new Message(MessageType.TASK_START, getStartString(), getCurrentTask()));
			System.out.println(getTag()+"submitStart: "+getStartString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void submitEnd(ObjectOutputStream os) {
		try {
			os.writeObject(new Message(MessageType.TASK_DONE, getEndString(), getCurrentTask()));
			System.out.println(getTag()+"submitEnd: "+getEndString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Override
	public void submitError(ObjectOutputStream os) {
		try {
			os.writeObject(new Message(MessageType.TASK_ERROR, getErrorString(), getCurrentTask()));
			System.out.println(getTag()+"submitError: "+getErrorString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void submitMessage(ObjectOutputStream os, String string) {
		try {
			os.writeObject(new Message(MessageType.MESSAGE, string, getCurrentTask()));
			System.out.println(getTag()+"submitMessage: "+ string);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void shutdownMessage(ObjectOutputStream os) {
		try {
			os.writeObject(new Message(MessageType.SHUTDOWNED, getTag()+getShutdownString(), null));
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(getTag()+"shutdown");
	}
	
	public static void main(String ... args) {
		if (args.length <1) {
			throw new IllegalArgumentException("Error creating TCPMachine. There is no name parametr");
		} else if (args.length <2) {
			throw new IllegalArgumentException("Error creating TCPMachine. There is no port parametr");
		}
		
		String name = args[0];
		int port = 0;
		
		try {
			port = Integer.valueOf(args[1]);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Error creating TCPMachine. Illegal port number: " + args[1]);
		}
		
		TCPMachine machine = new TCPMachine(name, port);
		machine.startupServer();
	}
}
