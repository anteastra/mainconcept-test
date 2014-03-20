package com.mainconcept.cloud;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.mainconcept.cloud.model.MachineIdent;
import com.mainconcept.cloud.model.Task;

public class MachinesController {
	
	private List<String> executedTaskMsgs = new ArrayList<String>();
	private List<Thread> executionThreads = new ArrayList<Thread>();
	
	private BlockingQueue<MachineIdent> freeMachinesQueue = new LinkedBlockingQueue<MachineIdent>();
	

	public void setMachines(List<MachineIdent> machinesIdent) {
		if (machinesIdent == null) {
			throw new IllegalArgumentException("machineHandlerList can`t be null");
		}
				
		for (MachineIdent mi: machinesIdent) {
			synchronized (freeMachinesQueue) {
				try {
					freeMachinesQueue.put(mi);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}		
	}

	public void sendToFreeMachine(final Task task) {
		if (freeMachinesQueue == null) {
			throw new IllegalStateException("machines list is null. Check you appended machines");
		}
		
		Thread thread =	new Thread(new Runnable() {
			@Override
			public void run() {
				MachineIdent mi;
				try {
					mi = freeMachinesQueue.take();
					mi.getMachineHandler().handleTask(task);
					executedTaskMsgs.add(mi.getMachineHandler().getResult());
					//TODO maybe where should be sychronized block
					freeMachinesQueue.put(mi);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		executionThreads.add(thread);
		thread.start();
	}

	public List<String> getExecutedTaskMessages() {
		return executedTaskMsgs;
	}

	public void waitForTasks() {
		
		for (Thread thread: executionThreads) {
			if (thread.isAlive()) {
				try {
					thread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		executionThreads.clear();
	}

}
