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
				error.start();
				output.start();			
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
