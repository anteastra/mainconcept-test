package com.mainconcept.cloud.machines;

import java.io.ObjectOutputStream;
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
		if (name == null) {
			throw new IllegalArgumentException("cannot create machine with name: null");
		}
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public boolean performTask(Task task, ObjectOutputStream os) {
		
		synchronized (this) {
			if (isBusy()) {
				return false;
			}			
			busy = true;
		}
		
		currentTask = task;
		Random r = new Random();
		expectDuration = r.nextInt(currentTask.getMaxDuration() - currentTask.getMinDuration()+1) 
				+ currentTask.getMinDuration();
		submitMessage(os, getExpectedTimeString());
		
		submitStart(os);
		
		long durationMs = System.currentTimeMillis();
		try {
			task.perform(expectDuration);
			durationMs = System.currentTimeMillis() - durationMs;
			duration = (int) (durationMs/1000);
			
		} catch (Exception e) {
			durationMs = System.currentTimeMillis() - durationMs;
			duration = (int) (durationMs/1000);			
			submitError(os);
			busy = false;
			return true;
		}
		
		submitEnd(os);
		busy = false;		
		return true;
	}

	private boolean isBusy() {
		return busy;
	}
	
	public Task getCurrentTask() {
		return currentTask;
	}
	
	public String getStartString() {
		return "started \""+ getCurrentTask().getName() + "\" on \"" 
				+ getName() + "\" at " + new Date();
	}
	
	public String getExpectedTimeString() {
		return "task: \""+getCurrentTask().getName() + "\"; expect duration: "
				+ expectDuration + " sec.";
	}
	
	public String getEndString() {
		return "finished \""+ getCurrentTask().getName() + "\" on \"" 
				+ getName() + "\" at "+ new Date() + ", duration " + duration + " sec.";
	}
	
	public String getErrorString() {
		return "error \""+ getCurrentTask().getName() + "\" on \"" 
				+ getName() + "\" at "+ new Date() + ", duration " + duration + " sec.";
	}
	
	public String getShutdownString() {
		return "shutdown machine \""+ getName() + "\" at " + new Date();
	}
	
	public String getStartUpString() {
		return "startup machine \""+ getName() + "\" at " + new Date();
	}
	
	public String getBusyString() {
		return "busy machine \""+ getName() + "\" task \""+getCurrentTask().getName()
				+"\" at " + new Date();
	}
	
	public abstract void submitStart(ObjectOutputStream os);
	public abstract void submitEnd(ObjectOutputStream os);
	public abstract void submitError(ObjectOutputStream os);
	public abstract void submitMessage(ObjectOutputStream os, String string);
	public abstract void shutdownMessage(ObjectOutputStream os);
	
}
