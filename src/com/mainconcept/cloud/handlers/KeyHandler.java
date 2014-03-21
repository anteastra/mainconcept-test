package com.mainconcept.cloud.handlers;

import java.util.ArrayList;
import java.util.List;

import com.mainconcept.cloud.loaders.MachinesLoader;
import com.mainconcept.cloud.loaders.MachinesLoaderFactory;
import com.mainconcept.cloud.loaders.TasksLoader;
import com.mainconcept.cloud.loaders.TasksLoaderFactory;
import com.mainconcept.cloud.model.MachineIdent;
import com.mainconcept.cloud.model.Task;

/**
 * Class to handle key parametrs
 * 
 * @author anteastra
 *
 */
public class KeyHandler {
	
	
	private static String MACHINE_KEY = "-fmachines";
	private static String TASK_KEY = "-ftasks";
	private static String START = "-start";
	private static String STOP = "-stop";
	
	private List<MachineIdent> mList = new ArrayList<MachineIdent>();
	private List<Task> tList = new ArrayList<Task>();
	private boolean start = false;
	private boolean stop = false;
	
	private String[] keys;
	
	/**
	 * You should put here the main arguments 
	 * @param args
	 */
	public KeyHandler(String ... args) {
		
		keys = args;
		
		String machineSource = getMachineSource();

		if (machineSource != null) {
			MachinesLoader machinesLoader = MachinesLoaderFactory.getMachinesLoader(machineSource);
			mList = machinesLoader.getMachineIdentList();
		}
		
		if (getMachineStop()) {
			stop = true;
		} 

		if (getMachineStart()) {
			start = true;
		}
	
		String taskSource = getTaskSource();
		
		if (taskSource != null) {
			TasksLoader tasksLoader = TasksLoaderFactory.getTasksLoader(taskSource);
			tList = tasksLoader.getTaskList();
		}
	}	

	/**
	 * 
	 * @return task list, if it exists
	 */
	public List<Task> getTaskList() {
		return tList;		
	}
	
	/**
	 * @return  machine list, if it exists
	 */
	public List<MachineIdent> getMachinesIdentList() {
		return mList;		
	}
	
	/**
	 * @return true if there start key
	 */
	public boolean isStarting() {
		return start;
	}

	/**
	 * @return true if there stop key
	 */
	public boolean isStoping() {
		return stop;
	}
	
	private String getTaskSource() {
		for (int i=0; i < (keys.length -1); i++) {
			if (keys[i].toLowerCase().equals(TASK_KEY)) {
				return keys[i+1];
			}
		}
		return null;
	}

	private String getMachineSource() {
		for (int i=0; i < (keys.length -1); i++) {
			if (keys[i].toLowerCase().equals(MACHINE_KEY)) {
				return keys[i+1];
			}
		}
		return null;
	}
	

	private boolean getMachineStop() {
		for (int i=0; i < (keys.length); i++) {
			if (keys[i].toLowerCase().equals(STOP)) {
				return true;
			}
		}
		return false;
	}

	private boolean getMachineStart() {
		for (int i=0; i < (keys.length); i++) {
			if (keys[i].toLowerCase().equals(START)) {
				return true;
			}
		}
		return false;
	}

}
