package com.mainconcept.cloud;

import java.net.Socket;

public class MachineServer extends Thread{
	
	private Machine machine;
	
	public MachineServer(Machine nMachine, Socket socket) {
		
		machine = nMachine;
		
		start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
