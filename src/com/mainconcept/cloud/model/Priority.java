package com.mainconcept.cloud.model;

import java.io.Serializable;

public enum Priority implements Serializable {
	HIGH (1,"HIGH"),
	NORMAL (2, "NORMAL"),
	LOW (3, "LOW");
	
	private int priorityNumber;
	private String priorityString;
	
	Priority(int i, String str) {
		priorityNumber = i;
		priorityString = str;
	}
	
	public int getIntPriority() {
		return priorityNumber;
	}
	
	public String getStringPriority() {
		return priorityString;
	}
	
	@Override
	public String toString() {
		return priorityString;
	}
}