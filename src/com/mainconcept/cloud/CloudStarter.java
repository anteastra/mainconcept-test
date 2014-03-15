package com.mainconcept.cloud;

import java.io.IOException;

public class CloudStarter {
	
	public static void main(String ... args) {
		System.out.println("start of cloud starter");
		startMachine();
		System.out.println("cloud starter!!!");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void startMachine() {
		try {
			Runtime.getRuntime().exec("java -cp CloudDispatcher.jar com.mainconcept.cloud.Machine");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
