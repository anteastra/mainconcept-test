package com.mainconcept.cloud;

import java.util.List;

import com.mainconcept.cloud.helpers.Streamer;
import com.mainconcept.cloud.model.MachineIdent;

public class CloudStarter {
	
	public static void startMachines(List<MachineIdent> machines) {
		
		System.out.println();
		System.out.println("---------Startup cloud---------");
		
		for (MachineIdent mi: machines) {
			try {
				Streamer error;
				Streamer output;
				Process process = Runtime.getRuntime().exec("java -cp CloudDispatcher.jar " +
						mi.getMachineClass()+ " " +
						mi.getStartupParametrs());
				error = new Streamer(process.getErrorStream());
				output = new Streamer(process.getInputStream());
				
				System.out.println(mi.getName()+": start machine");
				
				error.start();
				output.start();
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		waitForMachinesStarted();
	}

	private static void waitForMachinesStarted() {
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
