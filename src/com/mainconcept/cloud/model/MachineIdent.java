package com.mainconcept.cloud.model;

public abstract class MachineIdent {
	
	private String name;

	public abstract String getMachineClass();
	public abstract String getStartupParametrs();
	
	public MachineIdent(String name) {
		this.name = name;
	}

	public String getName(){
		return name;
	}

}
