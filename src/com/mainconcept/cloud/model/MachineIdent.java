package com.mainconcept.cloud.model;

import com.mainconcept.cloud.handlers.MachineHandler;

public abstract class MachineIdent {
	
	private String name;
	private MachineHandler machineHandler;

	public abstract String getMachineClass();
	public abstract String getStartupParametrs();
	
	public MachineIdent(String name) {
		this.name = name;
	}

	public String getName(){
		return name;
	}
	
	public void setHandler(MachineHandler machineHandler) {
		if (machineHandler == null) {
			throw new IllegalArgumentException("machineHandler can`t be null");
		}
		
		this.machineHandler = machineHandler;
	}
	
	public MachineHandler getMachineHandler() {
		if (machineHandler == null) {
			throw new IllegalStateException("machineHandler can`t be null");
		}
		return machineHandler;
	}

}
