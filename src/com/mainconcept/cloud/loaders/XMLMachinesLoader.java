package com.mainconcept.cloud.loaders;

import java.util.ArrayList;
import java.util.List;

import com.mainconcept.cloud.model.MachineIdent;
import com.mainconcept.cloud.model.TCPMachineIdent;

public class XMLMachinesLoader implements MachinesLoader{
	
	List<MachineIdent> list = new ArrayList<MachineIdent>();

	public XMLMachinesLoader(String fileName) {
		// TODO Auto-generated constructor stub
		list.add(new TCPMachineIdent("name1", 7401));
	}

	@Override
	public List<MachineIdent> getMachineIdentList() {		
		return list;
	}

}
