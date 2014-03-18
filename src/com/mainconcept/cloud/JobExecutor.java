package com.mainconcept.cloud;

import java.util.Collections;
import java.util.List;

import com.mainconcept.cloud.handlers.KeyHandler;
import com.mainconcept.cloud.model.MachineIdent;
import com.mainconcept.cloud.model.Task;

public class JobExecutor {
	
	@SuppressWarnings("unchecked")
	public static void main(String ... args) {
		System.out.println();
		System.out.println();
		System.out.println("---------Startup cloud---------");
		
		try {
				
			KeyHandler keyHandler = new KeyHandler(true, true, args);	
			
			List<Task> tasks = keyHandler.getTaskList();
			
			Collections.sort(tasks);
			
			List<MachineIdent> machines = keyHandler.getMachinesIdentList();
			
			MachinesController controller = new MachinesController();
			controller.appendMachines(machines);
		
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
				
	}

}
