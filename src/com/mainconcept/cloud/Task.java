package com.mainconcept.cloud;

public class Task {
	private int minDuration;
	private int maxDuration;
	private String name;
	private Priority priority;
	
	private Object taskMonitor = new Object();
	
	public Task(String name, int minDuration, int maxDuration, Priority priority) {
		this.name = name;
		this.minDuration = minDuration;
		this.maxDuration = maxDuration;
		this.priority = priority;
	}	
	
	public int getMinDuration() {
		return minDuration;
	}	
	
	public int getMaxDuration() {
		return maxDuration;
	}	
	
	public String getName() {
		return name;
	}
	
	public Priority getPriority() {
		return priority;
	}
	
	public void perform(int expectSeconds) {
		synchronized (taskMonitor) {
			try {
				taskMonitor.wait(expectSeconds*1000);				
				if (Math.random()>0.5) {
					taskMonitor.wait((long) (expectSeconds*Math.random()*1000));
					throw new RuntimeException();
				}
			} catch (InterruptedException e) {
				throw new RuntimeException();
			}
		}		
	}
	

	public enum Priority {
		High,
		Normal,
		Low
	}
	
}


