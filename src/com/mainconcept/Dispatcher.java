package com.mainconcept;

import com.mainconcept.cloud.CloudStarter;

public class Dispatcher {

	public static void main(String ... args) {
		try {
			System.out.println("start of dispatcher!!!");
			int i =0;
			for (String parametr: args) {
				System.out.println(i++ + " "+ parametr);
			}
			runMachine();
			Thread.sleep(4000);			
			System.out.println("end of main dispatcher");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void runMachine() {
		CloudStarter.startMachine();
	}
}
