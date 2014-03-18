package com.mainconcept.cloud;

import java.util.List;

import com.mainconcept.cloud.handlers.KeyHandler;
import com.mainconcept.cloud.loaders.MachinesLoader;
import com.mainconcept.cloud.loaders.MachinesLoaderFactory;
import com.mainconcept.cloud.loaders.TasksLoader;
import com.mainconcept.cloud.loaders.TasksLoaderFactory;
import com.mainconcept.cloud.model.MachineIdent;
import com.mainconcept.cloud.model.Task;

public class JobExecutor {
	
	public static void main(String ... args) {
		System.out.println();
		System.out.println();
		System.out.println("---------Startup cloud---------");
				
		KeyHandler keyHandler = new KeyHandler(true, true, args);	
		
		List<Task> tasks = keyHandler.getTaskList();
		List<MachineIdent> machines = keyHandler.getMachinesIdentList();
		
		MachinesController controller = new MachinesController();
		controller.appendMachines(machines);
				
	}

}
