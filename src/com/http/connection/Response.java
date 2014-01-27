package com.http.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.http.connection.callback.CallbackImpl;
import com.http.connection.callback.CallbackInterface;
import com.http.connection.callback.ErrorObject;
import com.http.connection.callback.SuccessObject;

public class Response {

	private static CallbackInterface callback = new CallbackImpl();;
	private SuccessObject success = null;
	private ErrorObject error = null;
	public Response(BufferedReader reader) {
		String line = null;
		StringBuffer sb = new StringBuffer();
		JSONObject responseJson;
		try {
			while ((line = reader.readLine()) != null) {
			    sb.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response = sb.toString();
		responseJson = parseJSON(response);
		
		//System.out.println(responseJson.toJSONString());
		if(!responseJson.containsKey("errors")){
			success = callback.successCallback(responseJson);
			System.out.println(success.convertToString());
		}
		else {
			error = callback.errorCallback(responseJson);
			System.out.println(error.convertToString());
		}
	}
	
	public ErrorObject getError() {
		return error;
	}

	public void setError(ErrorObject error) {
		this.error = error;
	}

	public SuccessObject getSuccess() {
		return success;
	}

	public void setSuccess(SuccessObject success) {
		this.success = success;
	}

	public JSONObject parseJSON(String json){
		JSONObject obj = null;
		try {
			obj = (JSONObject)new JSONParser().parse(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	// {"header":{"hostname":"RHL073"},
	// "result":{"success":true,"sessionID":"d2503761f6856b52f00c2e7548175423"}}
	// {"errors":[{"code":2,"message":"Method not found."}]}
	
	
}
