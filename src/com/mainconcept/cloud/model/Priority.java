package com.mainconcept.cloud.model;

import java.io.Serializable;

public enum Priority implements Serializable {
	High (1),
	Normal (2),
	Low (3);
	
	private int priorityNumber;
	
	Priority(int i) {
		priorityNumber = i;
	}
	
	public int getIntPriority() {
		return priorityNumber;
	}
}