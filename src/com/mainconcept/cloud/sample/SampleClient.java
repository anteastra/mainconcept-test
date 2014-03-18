package com.mainconcept.cloud.sample;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.mainconcept.cloud.model.Priority;
import com.mainconcept.cloud.model.Task;

public class SampleClient {  
	public static void main(String args[]){  
		try{  
			Socket s = new Socket("localhost",2002);  
			OutputStream os = s.getOutputStream();  
			ObjectOutputStream oos = new ObjectOutputStream(os);  
			Task to = new Task("name 1" , 5, 5, Priority.NORMAL);			
			oos.writeObject(to);  
			oos.writeObject(new String("another object from the client"));  
			oos.close();
			os.close(); 
			s.close(); 
		} catch(Exception e) {
			System.out.println(e);
		}  
	}  
}