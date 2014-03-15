package com.mainconcept.cloud.machines;

import com.mainconcept.cloud.Task;
import com.mainconcept.cloud.Task.Priority;

public class TestMachine extends Machine{

	public TestMachine(String nName) {
		super(nName);
	}

	@Override
	public void submitStart() {
		System.out.println(getStartString());
	}

	@Override
	public void submitEnd() {
		System.out.println(getEndString());
	}
	
	@Override
	public void submitError() {
		System.out.println(getErrorString());
	}
	
	@Override
	public void submitMessage(String message) {
		System.out.println(message);
	}	
	
	public static void main(String ...strings) {
		Task task = new Task("task n.1", 2, 2, Priority.High);
		
		Machine m = new TestMachine("machine n.1");
		m.performTask(task);
	}

	
}
