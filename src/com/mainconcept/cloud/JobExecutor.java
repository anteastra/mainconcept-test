package com.mainconcept.cloud;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.mainconcept.cloud.handlers.KeyHandler;
import com.mainconcept.cloud.model.MachineIdent;
import com.mainconcept.cloud.model.Task;

public class JobExecutor {
	
	public static void main(String ... args) {
		System.out.println();
		System.out.println();
		System.out.println("---------Startup cloud---------");
				
		KeyHandler keyHandler = new KeyHandler(true, true, args);	
		
		List<Task> tasks = keyHandler.getTaskList();
		
		Collections.sort(tasks, new Comparator<Task>() {

			@Override
			public int compare(Task arg0, Task arg1) {
				return arg0.getPriority().getIntPriority() - 
						arg1.getPriority().getIntPriority();
			}
			
		});
		
		List<MachineIdent> machines = keyHandler.getMachinesIdentList();
		
		MachinesController controller = new MachinesController();
		controller.appendMachines(machines);
				
	}

}
