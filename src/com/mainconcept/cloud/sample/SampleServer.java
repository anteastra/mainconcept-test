package com.mainconcept.cloud.sample;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.mainconcept.cloud.Task;

public class SampleServer  {  
	
	public static void main(String args[]) {  
		int port = 2002;  
		try {  
			ServerSocket ss = new ServerSocket(port);  
			Socket s = ss.accept();  
			InputStream is = s.getInputStream();  
			ObjectInputStream ois = new ObjectInputStream(is);  
			Task task = (Task)ois.readObject();  

			if (task!=null){System.out.println(task.getName());}  
			System.out.println((String)ois.readObject());  
			is.close();  
			s.close();  
			ss.close();  
		} catch(Exception e) {
			System.out.println(e);
		}  
}  
} 