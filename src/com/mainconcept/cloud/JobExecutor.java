package com.mainconcept.cloud;

import java.util.Collections;
import java.util.List;

import com.mainconcept.cloud.model.MachineIdent;
import com.mainconcept.cloud.model.Task;

public class JobExecutor {
	
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
			
			//TODO here should be result of performing tasks
			for (String msg: msgs) {
				System.out.println(msg);
			}
		
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
