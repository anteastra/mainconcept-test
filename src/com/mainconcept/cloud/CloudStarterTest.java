package com.mainconcept.cloud;

import java.io.IOException;

public class CloudStarterTest {
	
	public static void main(String ... args) {
		System.out.println("start of cloud starter");
		startMachine();
		System.out.println("cloud started!!!");		
	}
	
	public static void startMachine() {
		try {
			Runtime.getRuntime().exec("java -cp CloudDispatcher.jar com.mainconcept.cloud.machines.TCPMachine name1 7401");
			Runtime.getRuntime().exec("java -cp CloudDispatcher.jar com.mainconcept.cloud.machines.TCPMachine name2 7402");
			Runtime.getRuntime().exec("java -cp CloudDispatcher.jar com.mainconcept.cloud.machines.TCPMachine name3 7403");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
