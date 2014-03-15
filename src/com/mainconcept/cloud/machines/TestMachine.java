package com.mainconcept.cloud.machines;

import java.util.Date;

import com.mainconcept.cloud.Task;

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
	
	
	public static void main(String ...strings) {
		Task task = new Task();
		task.Name = "task n.1";
		task.maxDuration = 5;
		task.minDuration = 5;
		
		Machine m = new TestMachine("machine n.1");
		m.performTask(task);
	}
}
