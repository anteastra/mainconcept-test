package com.mainconcept.cloud;

import java.util.List;
import com.mainconcept.cloud.model.MachineIdent;

/**
 * This class represents logic to shutdown machines 
 * @author anteastra
 *
 */
public class CloudKiller {

	/**
	 * Static method to kill machines from the list
	 * @param machines
	 */
	public static void downMachines(List<MachineIdent> machines) {
		
		System.out.println();
		System.out.println("---------Shutdown cloud---------");
				
		for (MachineIdent mi: machines) {
			mi.stopMachine();
		}	
	}	
}
