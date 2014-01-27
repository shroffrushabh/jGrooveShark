package com.http.util;

import java.util.HashMap;
import java.util.Set;

import org.json.simple.JSONObject;

public class UtilHelperFunctions {

	public static HashMap<String, String> convertJSONToHashMap(JSONObject obj){
		HashMap result = new HashMap();				
		Set<String> keys = obj.keySet();
		for(String key: keys){
			result.put(key, obj.get(key));
		}
		return result;
	}
	
	public static String convertHashMapToJSON(HashMap hm){
		String res="{";
		for(Object key:hm.keySet()){
			res+="\""+key+"\":"+"\""+hm.get(key)+"\",";
		}
		res=res.substring(0, res.length() - 1);		
		res+="}";
		return res;
	}
	
	public static String createGroovesharkJsonWithParams(String method, HashMap params){
		String grooveSharkjson = "{\"method\":\""+method+"\",\"header\":{\"wsKey\":\""+Constants.groovesharkKey+"\",\"sessionID\":\""+params.get("sessionID")+"\"},\"parameters\":{";
		Boolean entered = false;
		for(Object key:params.keySet()) {
			if(!key.equals("sessionID") && !key.equals("country")) {
				entered = true;
				grooveSharkjson +="\"" + key + "\":\"" + params.get(key) +"\"," ;
			}
			if(key.equals("country")){
				entered = true;
				grooveSharkjson += "\"" + key + "\":" + params.get(key)+",";
			}
		}
		if(entered)
			grooveSharkjson=grooveSharkjson.substring(0, grooveSharkjson.length() - 1);
			
		grooveSharkjson += "}}";
		
    	return grooveSharkjson;
	}

	public static String addSongIdToJson(String json, String songId){
		json=json.substring(0, json.length() - 1);
		json+=",\"songID:\""+songId+"\"}";
		return json;
	}
}
