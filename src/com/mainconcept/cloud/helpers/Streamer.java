package com.mainconcept.cloud.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Streamer extends Thread {
    private InputStream input;
    public Streamer(InputStream in) {
        input = in;
    }
    
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line = null;
            
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}