package com.mainconcept.cloud;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class Machine {
	
	private String name;
	private int port = -1;
	
	public Machine(String nName, int nPort) {
		
		if (nPort < 0) {
			throw new IllegalArgumentException("cannot create " 
				+ nPort + " port, less than 0");
		}
		
		if (nName == null) {
			throw new IllegalArgumentException("cannot create machine with name: null");
		}
		
		name = nName;
		port = nPort;		
	}
	
	

	public static void main(String ... args) {
		
		if (args.length <1) {
			throw new IllegalArgumentException("there is no name parametr");
		} else if (args.length <2) {
			throw new IllegalArgumentException("there is no port parametr");
		}
		
		String name = args[0];
		int port = 0;
		
		try {
			port = Integer.valueOf(args[1]);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("illegal port number: " + args[1]);
		}
		
		Machine machine = new Machine(name, port);
		
		
		ServerSocket server;
		try {
			server = new ServerSocket(port, 0,
			        InetAddress.getByName("localhost"));
			while(true) {
	            // ждём нового подключения, после чего запускаем обработку клиента
	            // в новый вычислительный поток и увеличиваем счётчик на единичку
	            new MachineServer(machine, server.accept());
	        }
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}
