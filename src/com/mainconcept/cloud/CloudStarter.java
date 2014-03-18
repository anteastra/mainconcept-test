package com.mainconcept.cloud;

import com.mainconcept.cloud.handlers.KeyHandler;
import com.mainconcept.cloud.helpers.Streamer;
import com.mainconcept.cloud.loaders.MachinesLoader;
import com.mainconcept.cloud.loaders.MachinesLoaderFactory;
import com.mainconcept.cloud.model.MachineIdent;

public class CloudStarter {
	
	private static final String MACHINES_KEY = "-fmachines";

	public static void main(String ... args) {
		
		System.out.println();
		System.out.println();
		System.out.println("---------Startup cloud---------");
				
		KeyHandler keyHandler = new KeyHandler(true, false, args);			
		
		for (MachineIdent mi: keyHandler.getMachinesIdentList()) {
			startMachine(mi);
		}
	}
	
	public static void startMachine(MachineIdent mi) {
		try {
			Streamer error;
			Streamer output;
			Process process = Runtime.getRuntime().exec("java -cp CloudDispatcher.jar " +
					mi.getMachineClass()+ " " +
					mi.getStartupParametrs());
			error = new Streamer(process.getErrorStream());
			output = new Streamer(process.getInputStream());
			error.start();
			output.start();			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
