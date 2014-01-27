package com.http.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Constants {
	public static String groovesharkSecret; 
	public static String groovesharkKey;
	public final static String groovesharkAPI = "/ws3.php";
	public final static String groovesharkStreaming = "/ws/3.0/";
	public final static String groovesharkURL = "api.grooveshark.com";
	public final static String http = "http://";
	public final static String https = "https://";
	
	public static void initializeConstants(){
		File file = new File("/Users/shroffrushabh/Documents/workspace/Grooveshark/src/com/http/util/ImportantKeys");
		BufferedReader reader = null;

		try {
		    reader = new BufferedReader(new FileReader(file));
		    String line = null;

		    while ((line = reader.readLine()) != null) {
		    	String [] temp = line.split("-");
		    	if (temp[0].trim().equals("secret")) {
		    		Constants.groovesharkSecret = temp[1].trim();		    			
		    	}
		    	if(temp[0].trim().equals("key")) {
		    		Constants.groovesharkKey = temp[1].trim();
		    	}
		    }		    
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}

	}

}
