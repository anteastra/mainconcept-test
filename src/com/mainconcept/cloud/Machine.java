package com.mainconcept.cloud;

public class Machine {

	public static void main(String ... args) {
		try {
			System.out.println("start machine");
			Thread.sleep(30000);
			System.out.println("end machine");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
