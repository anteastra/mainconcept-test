package com.mainconcept.cloud;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.mainconcept.cloud.model.MachineIdent;
import com.mainconcept.cloud.model.Task;

/**
 * This class represents logic of job execution
 * 
 * @author anteastra
 *
 */
public class JobExecutor {
	
	/**
	 * For start perform tasks, send here task list and machine idents list
	 *  
	 * @param tasks
	 * @param machines
	 */
	public static void dispatch(List<Task> tasks, List<MachineIdent> machines) {
		System.out.println();
		System.out.println("---------Startup cloud job executor---------");
		
		try {
			Collections.sort(tasks);
			
			System.out.println("Task list in perform order:");
			for (Task task: tasks) {
				System.out.println(task);
			}
			System.out.println();
			
			MachinesController controller = new MachinesController();
			controller.setMachines(machines);
			
			for (Task task: tasks) {
				controller.sendToFreeMachine(task);
			}
			
			controller.waitForTasks();
			
			List<String> msgs = controller.getExecutedTaskMessages();
			
			System.out.println();
			System.out.println("result:");
			
			synchronized (msgs) {
			      Iterator<String> i = msgs.iterator(); 
			      while (i.hasNext()) {
			    	  System.out.println(i.next());
				}
			}
		
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
