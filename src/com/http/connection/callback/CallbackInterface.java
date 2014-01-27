package com.http.connection.callback;

import org.json.simple.JSONObject;

public interface CallbackInterface {
	public ErrorObject errorCallback(JSONObject obj);
	public SuccessObject successCallback(JSONObject obj);
}
