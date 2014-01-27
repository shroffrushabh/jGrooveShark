package com.http.connection.callback;


import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.http.util.UtilHelperFunctions;

public class ErrorObject {
	// {"errors":[{"code":2,"message":"Method not found."}]}
	private Vector<HashMap> errors;
	
	public ErrorObject(JSONObject obj){
		errors = new Vector<HashMap>();
		
		JSONArray jsonErrorArray = (JSONArray) obj.get("errors");
		for(int i=0;i<jsonErrorArray.size();i++){
			errors.add(UtilHelperFunctions.convertJSONToHashMap
					((JSONObject)jsonErrorArray.get(i)));
		}
	}

	public Vector<HashMap> getErrors() {
		return errors;
	}
	public HashMap getError(int index){
		return errors.get(index);
	}
		
	public String convertToString(){
		String res="";
		for(int i=0;i<errors.size();i++){
			res += "error - \n";
			for(Object key: errors.get(i).keySet()){
				res += key + " - " + errors.get(i).get(key)+"\n";
			}	
		}		
		return res;
	}

}
