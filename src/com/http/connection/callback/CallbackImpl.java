package com.http.connection.callback;

import org.json.simple.JSONObject;

public class CallbackImpl implements CallbackInterface{

	public ErrorObject errorCallback(JSONObject obj){
		ErrorObject err = new ErrorObject(obj); 
		return err;
	}
	public SuccessObject successCallback(JSONObject obj){
		SuccessObject success = new SuccessObject(obj);		
		return success;
	}

}
