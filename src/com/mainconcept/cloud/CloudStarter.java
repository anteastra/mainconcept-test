package com.mainconcept.cloud;

import java.io.IOException;

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
		
		if ((args.length < 2) || (!args[0].toLowerCase().equals(MACHINES_KEY))) {
			throw new IllegalArgumentException(MACHINES_KEY + " file expected");
		}
		
		MachinesLoader machinesLoader = MachinesLoaderFactory.getMachinesLoader(args[1]);
		if (machinesLoader.getMachineIdentList() == null) {
			System.out.println("error in machine loader");
			System.exit(0);
		}
		
		if (machinesLoader.getMachineIdentList().size() == 0) {
			System.out.println("there is no machine start");
			System.exit(0);
		}		
		
		for (MachineIdent mi: machinesLoader.getMachineIdentList()) {
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
