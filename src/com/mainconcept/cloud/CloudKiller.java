package com.mainconcept.cloud;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import com.mainconcept.cloud.model.MachineIdent;
import com.mainconcept.cloud.model.Message;
import com.mainconcept.cloud.model.Message.MessageType;
import com.mainconcept.cloud.model.TCPMachineIdent;

public class CloudKiller {

	public static void downMachines(List<MachineIdent> machines) {
		
		System.out.println();
		System.out.println("---------Shutdown cloud---------");
				
		for (MachineIdent mi: machines) {
			try {
				@SuppressWarnings("resource")
				Socket s = new Socket("localhost", ((TCPMachineIdent)mi).getPort());
				ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
				os.writeObject(new Message(MessageType.REQUEST_PERFORM_SHUTDOWN, null, null));
				
				ObjectInputStream is = new ObjectInputStream(s.getInputStream());
				Message msg = (Message) is.readObject();
				
				System.out.println(msg.getMessage());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
}
