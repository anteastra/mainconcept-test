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

import com.mainconcept.cloud.model.Priority;
import com.mainconcept.cloud.model.Task;

public class XMLTasksLoader implements TasksLoader{

	List<Task> list = new ArrayList<Task>();
	
	public XMLTasksLoader(String fileName) {
		try {
			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("task");
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					String name = element.getAttribute("name");
					Priority priority = Priority.valueOf(element.getAttribute("priority").toUpperCase());
					int mintime = Integer.parseInt(element.getAttribute("mintime"));
					int maxtime = Integer.parseInt(element.getAttribute("maxtime"));
					list.add(new Task(name, mintime, maxtime, priority));
				}
			}
		} catch(Exception e) {
			throw new RuntimeException("Error handle file "+fileName);
		}
		
	}	
	
	@Override
	public List<Task> getTaskList() {
		return list;
	}

}
