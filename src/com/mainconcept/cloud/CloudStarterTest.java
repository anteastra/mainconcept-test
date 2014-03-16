package com.mainconcept.cloud;

import java.io.IOException;

import com.mainconcept.cloud.helpers.Streamer;

public class CloudStarterTest {
	
	public static void main(String ... args) {
		System.out.println("start of cloud starter");
		startMachine();
		System.out.println("cloud started!!!");		
	}
	
	public static void startMachine() {
		try {
			Streamer error;
			Streamer output;
			Process process = Runtime.getRuntime().exec("java -cp CloudDispatcher.jar com.mainconcept.cloud.machines.TCPMachine name1 7401");
			error = new Streamer(process.getErrorStream());
			output = new Streamer(process.getInputStream());
			error.start();
			output.start();
			
			process = Runtime.getRuntime().exec("java -cp CloudDispatcher.jar com.mainconcept.cloud.machines.TCPMachine name2 7402");
			error = new Streamer(process.getErrorStream());
			output = new Streamer(process.getInputStream());
			error.start();
			output.start();			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
