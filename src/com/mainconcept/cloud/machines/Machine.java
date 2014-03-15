package com.mainconcept.cloud.machines;

import java.util.Date;
import java.util.Random;

import com.mainconcept.cloud.Task;

public abstract class Machine {

	private String name;
	private boolean busy = false;
	private Task currentTask;
	private int currentDuration;
	private Object taskMonitor = new Object();
	
	public Machine(String nName) {
		name = nName;
	}

	public String getName() {
		return name;
	}
	
	public boolean performTask(Task task) {
		if (isBusy()) return false;
		
		busy = true;
		currentTask = task;
		
		submitStart();
		Random r = new Random();
		currentDuration = r.nextInt(currentTask.maxDuration - currentTask.minDuration+1) 
				+ currentTask.minDuration;
		
		try {
			synchronized (taskMonitor) {
				taskMonitor.wait(currentDuration*1000);
			}			
		} catch (InterruptedException e) {
			e.printStackTrace();
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
		return "started "+ getCurrentTask().Name + " on " 
				+ getName() + " at " + new Date();
	}
	
	public String getEndString() {
		return "finished "+ getCurrentTask().Name + " on " 
				+ getName() + " at "+ new Date() + ", duration " + currentDuration + " sec.";
	}
	
	public abstract void submitStart();
	public abstract void submitEnd();
	
}
