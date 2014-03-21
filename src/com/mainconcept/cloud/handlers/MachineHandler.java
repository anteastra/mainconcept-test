package com.mainconcept.cloud.handlers;

import com.mainconcept.cloud.model.Task;

/**Interface to handle machines
 * @author anteastra
 *
 */
public interface MachineHandler {

	/**
	 * Handle some task
	 * @param task
	 */
	void handleTask(Task task);
	
	/**
	 * @return result of handled task
	 */
	String getResult();

}
