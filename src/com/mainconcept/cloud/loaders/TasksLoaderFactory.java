package com.mainconcept.cloud.loaders;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.mainconcept.cloud.helpers.SimpleXMLErrorHandler;
import com.mainconcept.cloud.model.SrcType;

public class TasksLoaderFactory {

	public static TasksLoader getTasksLoader(String path) {		
		SrcType type = SrcType.UNKNOWN;
		
		if (checkOnXmlType(path)) type = SrcType.XML;
		if (checkOniniType(path)) type = SrcType.INI;
		
		switch (type) {
		case XML:
			return new XMLTasksLoader(path);
		case INI:
			return new INITasksLoader(path);
		case UNKNOWN:			
		default:
			throw new IllegalArgumentException("unknown task loader source: "+ path);
		}
	}

	private static boolean checkOniniType(String path) {
		return false;
	}

	private static boolean checkOnXmlType(String path) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(true);
		
		SimpleXMLErrorHandler handler = new SimpleXMLErrorHandler();

		try {
			SAXParser parser = factory.newSAXParser();

			XMLReader reader = parser.getXMLReader();
			reader.setErrorHandler(handler);
			
			reader.parse(new InputSource(path));
		} catch (Exception e) {
			return false;
		}
		
		if (handler.isValidXML()) {
			return true;
		}
		
		return false;
	}

}
