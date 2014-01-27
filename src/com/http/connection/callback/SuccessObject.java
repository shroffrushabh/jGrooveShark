package com.http.connection.callback;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONObject;

import com.http.util.UtilHelperFunctions;

public class SuccessObject {
	// {"header":{"hostname":"RHL073"},
	// "result":{"success":true,"sessionID":"d2503761f6856b52f00c2e7548175423"}}
	private HashMap resultMap;
	private HashMap headerMap;

	public HashMap getResultMap() {
		return resultMap;
	}
	
	public HashMap getHeaderMap() {
		return headerMap;
	}

	public SuccessObject(JSONObject obj){
		resultMap = UtilHelperFunctions.convertJSONToHashMap((JSONObject) obj.get("result"));
		headerMap = UtilHelperFunctions.convertJSONToHashMap((JSONObject) obj.get("header"));		
	}

	public Object getValueFromHashMap(Boolean resultMapBoolean, Object key){
		Object value="";
		
		if(resultMapBoolean) {
			value = resultMap.get(key);
		} else {
			value = headerMap.get(key);
		}
		
		return value;
	}
	
	
	public String convertToString(){
		String res="";
		res+="Result - \n";
		for(Object key: resultMap.keySet()){
			res+=(key + " - " + resultMap.get(key))+"\n";
		}

		res+="Header - \n";
		for(Object key: headerMap.keySet()){
			res+=(key + " - " + headerMap.get(key))+"\n";
		}
		return res;
	}
}