package com.mainconcept.cloud.model;

public class TCPMachineIdent extends MachineIdent{
	
	private int port;
	private static final String MACHINE_CLASS = "com.mainconcept.cloud.machines.TCPMachine";

	public TCPMachineIdent(String name, int port) {
		super(name);
		this.port = port;
	}

	@Override
	public String getMachineClass() {
		return MACHINE_CLASS;
	}

	@Override
	public String getStartupParametrs() {
		// TODO Auto-generated method stub
		return getName()+" "+getPort();
	}
	
	public int getPort() {
		return port; 
	}
}
