package com.mainconcept.cloud.handlers;

import java.util.ArrayList;
import java.util.List;

import com.mainconcept.cloud.loaders.MachinesLoader;
import com.mainconcept.cloud.loaders.MachinesLoaderFactory;
import com.mainconcept.cloud.loaders.TasksLoader;
import com.mainconcept.cloud.loaders.TasksLoaderFactory;
import com.mainconcept.cloud.model.MachineIdent;
import com.mainconcept.cloud.model.Task;

public class KeyHandler {
	
	
	private static String MACHINE_KEY = "-fmachines";
	private static String TASK_KEY = "-ftasks";
	
	private List<MachineIdent> mList = new ArrayList<MachineIdent>();
	private List<Task> tList = new ArrayList<Task>();
	
	private String[] keys;
	
	public KeyHandler(boolean isMachinesExpected, boolean isTasksExpected, String ... args) {
		
		keys = args;		
		
		if (isMachinesExpected) {
			String machineSource = getMachineSource();
			MachinesLoader machinesLoader = MachinesLoaderFactory.getMachinesLoader(machineSource);
			mList = machinesLoader.getMachineIdentList();
		}
		
		if (isTasksExpected) {
			String taskSource = getTaskSource();
			TasksLoader tasksLoader = TasksLoaderFactory.getTasksLoader(taskSource);
			tList = tasksLoader.getTaskList();
		}
	}
	
	private String getTaskSource() {
		for (int i=0; i < (keys.length -1); i++) {
			if (keys[i].toLowerCase().equals(TASK_KEY)) {
				return keys[i+1];
			}
		}
		throw new IllegalArgumentException("there is no tasks key");
	}

	private String getMachineSource() {
		for (int i=0; i < (keys.length -1); i++) {
			if (keys[i].toLowerCase().equals(MACHINE_KEY)) {
				return keys[i+1];
			}
		}
		throw new IllegalArgumentException("there is no machines key");
	}

	public List<Task> getTaskList() {
		return tList;		
	}
	
	public List<MachineIdent> getMachinesIdentList() {
		return mList;		
	}

}
