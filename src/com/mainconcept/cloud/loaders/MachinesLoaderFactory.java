package com.mainconcept.cloud.loaders;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.mainconcept.cloud.helpers.SimpleXMLErrorHandler;
import com.mainconcept.cloud.model.SrcType;

public class MachinesLoaderFactory {

	public static MachinesLoader getMachinesLoader(String path) {
		SrcType type = SrcType.UNKNOWN;
		
		if (checkOnXmlType(path)) type = SrcType.XML;
		if (checkOniniType(path)) type = SrcType.INI;
		
		switch (type) {
		case XML:
			return new XMLMachinesLoader(path);
		case INI:
			return new INIMachinesLoader(path);
		case UNKNOWN:			
		default:
			throw new IllegalArgumentException("unknown machine loader source: "+ path);
		}
		
	}
	
	private static boolean checkOniniType(String fileName) {
		return false;
	}

	private static boolean checkOnXmlType(String fileName) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(true);
		
		SimpleXMLErrorHandler handler = new SimpleXMLErrorHandler();

		try {
			SAXParser parser = factory.newSAXParser();

			XMLReader reader = parser.getXMLReader();
			reader.setErrorHandler(handler);
			
			reader.parse(new InputSource(fileName));
		} catch (Exception e) {
			return false;
		}
		
		if (handler.isValidXML()) {
			return true;
		}
		
		return false;
	}
}
