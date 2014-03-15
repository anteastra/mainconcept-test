package com.mainconcept.cloud.machines;

public class TCPMachine extends Machine{
	
	private int port;

	public TCPMachine(String name, int port) {
		super(name);
		if (port <= 0 ) {
			throw new IllegalArgumentException("port number cannot be less or equal 0");
		}
		
		this.port = port;
	}

	@Override
	public void submitStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submitEnd() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void submitError() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submitMessage(String string) {
		// TODO Auto-generated method stub
		
	}
	
}
