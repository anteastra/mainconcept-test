package com.mainconcept.cloud;

import java.util.ArrayList;
import java.util.List;

import com.mainconcept.cloud.model.MachineIdent;
import com.mainconcept.cloud.model.Task;

public class MachinesController {
	
	private List<String> executedTaskMsgs = new ArrayList<String>();
	private List<MachineIdent> machinesIdent;

	public void appendMachines(List<MachineIdent> machinesIdent) {
		if (machinesIdent == null) {
			throw new IllegalArgumentException("machineHandlerList can`t be null");
		}
		
		this.machinesIdent = machinesIdent;
	}

	public void sendToFreeMachine(Task task) {
		if (machinesIdent == null) {
			throw new IllegalStateException("machines list is null. Check you appended machines");
		}
		
		
	}

	public List<String> getExecutedTaskMessages() {
		return executedTaskMsgs;
	}

}
