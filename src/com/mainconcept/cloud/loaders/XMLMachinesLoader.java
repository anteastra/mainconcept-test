package com.mainconcept.cloud.loaders;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mainconcept.cloud.handlers.TCPMachineHandler;
import com.mainconcept.cloud.model.MachineIdent;
import com.mainconcept.cloud.model.TCPMachineIdent;

public class XMLMachinesLoader implements MachinesLoader{
	
	List<MachineIdent> list = new ArrayList<MachineIdent>();

	public XMLMachinesLoader(String fileName) {
		try {
			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("machine");
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					String name = element.getAttribute("name");
					int port = Integer.parseInt(element.getAttribute("port"));
					String host = element.getAttribute("host");
					MachineIdent mi = new TCPMachineIdent(name, port);
					mi.setHandler(new TCPMachineHandler(host, port, name));
					list.add(mi);
				}
			}
		} catch(Exception e) {
			//TODO change exception handle
		}
		
	}

	@Override
	public List<MachineIdent> getMachineIdentList() {		
		return list;
	}

}
