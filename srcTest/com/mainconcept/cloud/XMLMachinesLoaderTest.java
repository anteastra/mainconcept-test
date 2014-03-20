package com.mainconcept.cloud;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mainconcept.cloud.loaders.XMLMachinesLoader;

public class XMLMachinesLoaderTest {

	private static String path = "sample_machines_file.xml";
	private static String fakePath = "fake.xml";
	
	@Test (expected = RuntimeException.class)
	public void testXMLMachinesLoader() {
		new XMLMachinesLoader(fakePath);
	}
	
	@Test
	public void testGetMachineIdentList() {
		XMLMachinesLoader loader = new XMLMachinesLoader(path);
		assertNotNull(loader.getMachineIdentList());
		assertEquals(3, loader.getMachineIdentList().size());
	}

}
