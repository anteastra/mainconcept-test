package com.mainconcept.cloud.helpers;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class SimpleXMLErrorHandler implements ErrorHandler {
	
	private int troubles = 0;	
	
    public void warning(SAXParseException e) throws SAXException {
    	troubles++;
    	System.out.println("found warning");
    }

    public void error(SAXParseException e) throws SAXException {
    	troubles++;
    	System.out.println("found error");
    }

    public void fatalError(SAXParseException e) throws SAXException {
    	troubles++;
    	System.out.println("found fatalError");
    }

	public boolean isValidXML() {
		return (troubles == 0);
	}
}