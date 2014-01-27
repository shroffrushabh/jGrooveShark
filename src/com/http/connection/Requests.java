package com.http.connection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import com.http.util.Constants;
import com.http.util.SecurityHelper;
import com.http.util.UtilHelperFunctions;

public class Requests {
	
	public static Response sendPostReq(String method, HashMap params, Boolean http) throws Exception{
    	String grooveSharkjson = "{\"method\":\""+method+"\",\"header\":{\"wsKey\":\""+Constants.groovesharkKey+"\"},\"parameters\":[]}";
    	if(params != null){
    		grooveSharkjson = UtilHelperFunctions.createGroovesharkJsonWithParams(method, params);
    	}
    	
    	String sig = SecurityHelper.getHmacMD5(grooveSharkjson, Constants.groovesharkSecret);

    	String urlString = "";
    	if(http)
    		urlString+=Constants.http+Constants.groovesharkURL+Constants.groovesharkAPI;
    	else
    		urlString+=Constants.https+Constants.groovesharkURL+Constants.groovesharkAPI;
    	
    	
		URL url = new URL(urlString+"?sig=" + sig);
    			
    	URLConnection connection = url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);

		connection.connect();
		
		OutputStream os = connection.getOutputStream();
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
		pw.write(grooveSharkjson);
		pw.close();

		InputStream is = connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		
		Response resp = new Response(reader);
		is.close();	
		return resp;
	}
	
}
