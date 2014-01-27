package com.grooveshark;

import java.util.HashMap;

import com.http.connection.Requests;
import com.http.connection.Response;
import com.http.connection.callback.SuccessObject;
import com.http.util.SecurityHelper;
import com.http.util.UtilHelperFunctions;

public class GSMethods {
	private String sessionId="";
	private HashMap countryObject;
	private StreamInformation stream;
	
	public StreamInformation getStream() {
		return stream;
	}

	public void setStream(StreamInformation stream) {
		this.stream = stream;
	}

	public HashMap getCountryObject() {
		return countryObject;
	}

	public void setCountryObject(HashMap countryObject) {
		this.countryObject = countryObject;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void startSession(){
        try {
			Response resp = Requests.sendPostReq("startSession",null,false);
			if (resp.getError() == null){
				SuccessObject successObj = resp.getSuccess();
				Boolean success = (Boolean) successObj.getValueFromHashMap(true, "success");
				if(success) {
					sessionId = (String) successObj.getValueFromHashMap(true, "sessionID");
				}
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void registerUser(String emailAddress, String password, String fullName, String sessionId){
		registerUser(emailAddress, password, fullName, "", "", "",sessionId);
	}
	
	public void registerUser(String emailAddress, String password, String fullName, 
				String username, String gender, String birthDate, String sessionId){
		
		HashMap params = new HashMap();
		params.put("emailAddress", emailAddress);
		params.put("password", SecurityHelper.md5(password));
		params.put("fullName", fullName);
		params.put("username", username);
		params.put("gender", gender);
		params.put("birthDate", birthDate);
		params.put("sessionID", sessionId);
		
		try {
			Response resp = Requests.sendPostReq("registerUser", params, false);
			if (resp.getError() == null){
				SuccessObject successObj = resp.getSuccess();
				Boolean success = (Boolean) successObj.getValueFromHashMap(true, "success");
				if(success) {
					System.out.println("User created...");
				} else {
					System.out.println("Some problem has occurred...");
				}
			}			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public void authenticate(String login, String password, String sessionID){
		HashMap params = new HashMap();
		params.put("login", login);
		params.put("password", SecurityHelper.md5(password));
		params.put("sessionID", sessionId);
		try {
			Response resp = Requests.sendPostReq("authenticate", params, false);
			if (resp.getError() == null){
				SuccessObject successObj = resp.getSuccess();
				Boolean success = (Boolean) successObj.getValueFromHashMap(true, "success");
				if(success) {
					System.out.println("Authenticated user...");
				} else {
					System.out.println("Incorrect username/password...");
				}
			}			
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void logout(String sessionID){
		HashMap params = new HashMap();
		params.put("sessionID", sessionId);
		try {
			Response resp = Requests.sendPostReq("logout", params, false);
			if (resp.getError() == null){
				SuccessObject successObj = resp.getSuccess();
				Boolean success = (Boolean) successObj.getValueFromHashMap(true, "success");
				if(success) {
					System.out.println("logging out user...");
				} else {
					System.out.println("Some problem has occurred...");
				}
			}			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}
	
	public void getCountry(){
		try {
			Response resp = Requests.sendPostReq("getCountry", null, true);
			if (resp.getError() == null){
				SuccessObject successObj = resp.getSuccess();
				//String res = successObj.getResultMap().toString();
				countryObject=successObj.getResultMap();				
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	// http://tinysong.com/s/calvin+harris
	// ?format=json&limit=3&key=f1ac5d0128e864f69c052feed8f0d3a3
	
	public void getStreamKeyStreamServer(String sessionID, int songId){
		HashMap params = new HashMap();
		params.put("songID", songId);
		params.put("sessionID", sessionId);
		params.put("country", UtilHelperFunctions.convertHashMapToJSON(countryObject));		
		params.put("lowBitrate", "");
		try {
			Response resp = Requests.sendPostReq("getStreamKeyStreamServer",params,true);
			if(resp.getError() == null){
				HashMap results = resp.getSuccess().getResultMap();
				stream = new StreamInformation(results.get("StreamKey"), 
						results.get("StreamServerID"),
						results.get("uSecs"),
						results.get("String url"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void markStreamKeyOver30Secs(){
		HashMap params = new HashMap();
		params.put("streamKey", stream.getStreamKey());
		params.put("streamServerID", stream.getStreamServerID());
		params.put("sessionID", sessionId);

		try {
			Response resp = Requests.sendPostReq("markStreamKeyOver30Secs", params, true);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
