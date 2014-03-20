package com.mainconcept.cloud;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mainconcept.cloud.handlers.KeyHandler;

public class KeyHandlerTest {
	
	private static String[] testArray1 = {"-start","-stop","-fmachines","sample_machines_file.xml","-ftasks","sample_tasks_file.xml"};
	private static String[] testArray2 = {"-start","-fmachines","sample_machines_file.xml"};
	private static String[] testArray3 = {"-stop","-fmachines","sample_machines_file.xml"};
	
	@Test
	public void testGetTaskList() {
		KeyHandler kh = new KeyHandler(testArray1);
		assertNotNull(kh.getTaskList());
		assertEquals(5, kh.getTaskList().size());
		
		kh = new KeyHandler(testArray2);
		assertNotNull(kh.getTaskList());
		assertEquals(0, kh.getTaskList().size());
		
		kh = new KeyHandler(testArray3);
		assertNotNull(kh.getTaskList());
		assertEquals(0, kh.getTaskList().size());
	}

	@Test
	public void testGetMachinesIdentList() {
		KeyHandler kh = new KeyHandler(testArray1);
		assertNotNull(kh.getMachinesIdentList());
		assertEquals(3, kh.getMachinesIdentList().size());
		
		kh = new KeyHandler(testArray2);
		assertNotNull(kh.getMachinesIdentList());
		assertEquals(3, kh.getMachinesIdentList().size());
		
		kh = new KeyHandler(testArray3);
		assertNotNull(kh.getMachinesIdentList());
		assertEquals(3, kh.getMachinesIdentList().size());
	}

	@Test
	public void testIsStarting() {
		KeyHandler kh = new KeyHandler(testArray1);
		assertTrue(kh.isStarting());
		
		kh = new KeyHandler(testArray2);
		assertTrue(kh.isStarting());
		
		kh = new KeyHandler(testArray3);
		assertFalse(kh.isStarting());
	}

	@Test
	public void testIsStoping() {
		KeyHandler kh = new KeyHandler(testArray1);
		assertTrue(kh.isStoping());
		
		kh = new KeyHandler(testArray2);
		assertFalse(kh.isStoping());
		
		kh = new KeyHandler(testArray3);
		assertTrue(kh.isStoping());
	}

}
