package com.mainconcept.cloud;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.mainconcept.cloud.model.MachineIdent;
import com.mainconcept.cloud.model.Task;

/**
 * Main executor class
 * 
 * @author anteastra
 *
 */
public class MachinesController {
	
	private List<String> executedTaskMsgs = new ArrayList<String>();
	private List<Thread> executionThreads = new ArrayList<Thread>();
	
	private BlockingQueue<MachineIdent> freeMachinesQueue = new LinkedBlockingQueue<MachineIdent>();
	

	/**
	 * Before use this class, you should perform this method 
	 * 
	 * @param machinesIdent
	 */
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

	/**
	 * This method use queue of free machines.
	 * Task will be send to free machines. If there is no free machines
	 * It will wait for it.
	 * 
	 * @param task
	 */
	public void sendToFreeMachine(final Task task) {
		if ((freeMachinesQueue == null)||(freeMachinesQueue.size()==0)) {
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

	/**
	 * Return results of executed tasks
	 * 
	 * @return
	 */
	public List<String> getExecutedTaskMessages() {
		return executedTaskMsgs;
	}

	/**
	 * This method should be used after jobs are sent to machines.
	 * Results will be available after this method complete
	 */
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
