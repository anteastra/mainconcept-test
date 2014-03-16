package com.mainconcept.cloud.machines;

import java.io.ObjectOutputStream;

import com.mainconcept.cloud.model.Task;
import com.mainconcept.cloud.model.Task.Priority;

public class TestMachine extends Machine{

	public TestMachine(String nName) {
		super(nName);
	}

	@Override
	public void submitStart(ObjectOutputStream os) {
		System.out.println(getStartString());
	}

	@Override
	public void submitEnd(ObjectOutputStream os) {
		System.out.println(getEndString());
	}
	
	@Override
	public void submitError(ObjectOutputStream os) {
		System.out.println(getErrorString());
	}
	
	@Override
	public void shutdownMessage(ObjectOutputStream os) {
		System.out.println(getShutdownString());
	}
	
	@Override
	public void submitMessage(ObjectOutputStream os, String message) {
		System.out.println(message);
	}	
	
	public static void main(String ...strings) {
		Task task = new Task("task n.1", 2, 2, Priority.High);
		
		Machine m = new TestMachine("machine n.1");
		m.performTask(task, null);
	}

	

	
}
