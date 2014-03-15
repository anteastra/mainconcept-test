package com.mainconcept.cloud.machines;

import java.util.Date;
import java.util.Random;

import com.mainconcept.cloud.Task;

public abstract class Machine {

	private String name;
	private boolean busy = false;
	private Task currentTask;
	private int expectDuration;
	private int duration;
	
	public Machine(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public boolean performTask(Task task) {
		if (isBusy()) {
			return false;
		}
		
		busy = true;
		currentTask = task;
		
		
		Random r = new Random();
		expectDuration = r.nextInt(currentTask.getMaxDuration() - currentTask.getMinDuration()+1) 
				+ currentTask.getMinDuration();
		submitMessage("task: \""+task.getName() + "\"; expect duration: " + expectDuration + " sec.");
		submitStart();
		
		long durationMs = System.currentTimeMillis();
		try {
			 
			task.perform(expectDuration);
			durationMs = System.currentTimeMillis() - durationMs;
			duration = (int) (durationMs/1000);
			
		} catch (Exception e) {
			durationMs = System.currentTimeMillis() - durationMs;
			duration = (int) (durationMs/1000);
			submitError();
			return false;
		}
		
		submitEnd();
		return true;
	}

	private boolean isBusy() {
		synchronized (this) {
			return busy;
		}
	}
	
	public Task getCurrentTask() {
		return currentTask;
	}
	
	public String getStartString() {
		return "started \""+ getCurrentTask().getName() + "\" on \"" 
				+ getName() + "\" at " + new Date();
	}
	
	public String getEndString() {
		return "finished \""+ getCurrentTask().getName() + "\" on \"" 
				+ getName() + "\" at "+ new Date() + ", duration " + duration + " sec.";
	}
	
	public String getErrorString() {
		return "error \""+ getCurrentTask().getName() + "\" on \"" 
				+ getName() + "\" at "+ new Date() + ", duration " + duration + " sec.";
	}
	
	public abstract void submitStart();
	public abstract void submitEnd();
	public abstract void submitError();
	public abstract void submitMessage(String string);
	
}
