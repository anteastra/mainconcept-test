package com.mainconcept.cloud;

import java.util.Collections;
import java.util.List;

import com.mainconcept.cloud.model.MachineIdent;
import com.mainconcept.cloud.model.Task;

public class JobExecutor {
	
	@SuppressWarnings("unchecked")
	public static void dispatch(List<Task> tasks, List<MachineIdent> machines) {
		System.out.println();
		System.out.println("---------Startup cloud job executor---------");
		
		try {
			Collections.sort(tasks);
			MachinesController controller = new MachinesController();
			controller.setMachines(machines);
			
			for (Task task: tasks) {
				controller.sendToFreeMachine(task);
			}
			
			//TODO need wait here for completing all tasks
			
			List<String> msgs = controller.getExecutedTaskMessages();
			
			for (String msg: msgs) {
				System.out.println(msg);
			}
		
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
