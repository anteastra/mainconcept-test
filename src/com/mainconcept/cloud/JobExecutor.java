package com.mainconcept.cloud;

import java.util.List;

import com.mainconcept.cloud.loaders.MachinesLoader;
import com.mainconcept.cloud.loaders.MachinesLoaderFactory;
import com.mainconcept.cloud.loaders.TasksLoader;
import com.mainconcept.cloud.loaders.TasksLoaderFactory;
import com.mainconcept.cloud.model.Task;

public class JobExecutor {
	
	public static void main(String ... args) {
		TasksLoader taskLoader = TasksLoaderFactory.getTasksLoader(args[0]);
		MachinesLoader machinesLoader = MachinesLoaderFactory.getMachinesLoader(args[1]);
		
		List<Task> tasks = taskLoader.getTaskList();
		MachinesController controller = new MachinesController();
		controller.appendMachines(machinesLoader.getMachineIdentList());
				
	}

}
