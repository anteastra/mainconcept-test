package com.mainconcept.cloud;

import java.util.List;

import com.mainconcept.cloud.handlers.KeyHandler;
import com.mainconcept.cloud.model.MachineIdent;
import com.mainconcept.cloud.model.Task;

public class Dispatcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KeyHandler keyHandler = new KeyHandler(args);
		
		List<Task> tasks = keyHandler.getTaskList();
		List<MachineIdent> machines = keyHandler.getMachinesIdentList();
		
		if (keyHandler.isStarting()) {
			CloudStarter.startMachines(machines);
		}
		
		if (keyHandler.getTaskList().size()>0) {
			JobExecutor.dispatch(tasks, machines);
		}
		
		if (keyHandler.isStoping()) {
			CloudKiller.downMachines(machines);
		}
	}

}
