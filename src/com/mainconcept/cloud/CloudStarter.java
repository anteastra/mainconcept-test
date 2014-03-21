package com.mainconcept.cloud;

import java.util.List;

import com.mainconcept.cloud.model.MachineIdent;

/**This class represent logic to start machines
 * @author anteastra
 *
 */
public class CloudStarter {
	
	/**Static method to start machines from the list
	 * @param machines
	 */
	public static void startMachines(List<MachineIdent> machines) {
		
		System.out.println();
		System.out.println("---------Startup cloud---------");
		
		for (MachineIdent mi: machines) {
			mi.startMachine();
		}		
		waitForMachinesStarted();
	}

	private static void waitForMachinesStarted() {
		//TODO should use some different way
		try {
			Object o = new Object();
			synchronized (o) {
				o.wait(1000);
			}
		} catch (InterruptedException e) {
			System.out.println("error creating machines");
			throw new IllegalStateException("something went wrong during startup cloud");
		}
	}
}
