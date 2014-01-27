package com.http.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

// "HmacMD5"
public class SecurityHelper{

	public static String getHmacMD5(String payload, String secret) {
		String sEncodedString = null;
		try {
			SecretKeySpec key = new SecretKeySpec((secret).getBytes("UTF-8"), "HmacMD5");
			Mac mac = Mac.getInstance("HmacMD5");
			mac.init(key);

			byte[] bytes = mac.doFinal(payload.getBytes("UTF-8"));

			StringBuffer hash = new StringBuffer();

			for (int i=0; i<bytes.length; i++) {
				String hex = Integer.toHexString(0xFF &  bytes[i]);
				if (hex.length() == 1) {
					hash.append('0');
				}
				hash.append(hex);
			}
			sEncodedString = hash.toString();
		}
		catch (UnsupportedEncodingException e) {}
		catch(InvalidKeyException e){}
		catch (NoSuchAlgorithmException e) {}

		return sEncodedString ;
	}

	public static String md5(String message){ 
		String digest = null; 
		try { 
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			byte[] hash = md.digest(message.getBytes("UTF-8")); 
			
			StringBuilder sb = new StringBuilder(2*hash.length); 
			for(byte b : hash) { 
				sb.append(String.format("%02x", b&0xff)); 
			} 
			digest = sb.toString(); 
		} catch (UnsupportedEncodingException ex) { 
		} catch (NoSuchAlgorithmException ex) { 
		} return digest; 
	} 

	public static void main(String [] args){
		//String grooveSharkjson = "{'method':'addUserFavoriteSong','parameters':{'songID':30547543},'header':{'wsKey':'key','sessionID':'df8fec35811a6b240808563d9f72fa2'}}";
		//System.out.println(SecurityHelper.getHmacMD5(grooveSharkjson, "secret"));

		//String json = "{\"method\":\"addUserFavoriteSong\",\"parameters\":{\"songID\":\"30547543\"},\"header\":{\"wsKey\":\"key\",\"sessionID\":\"df8fec35811a6b240808563d9f72fa2\"}}";
		//System.out.println("f699614eba23b4b528cb830305a9fc77");
		String json = "{\"method\":\"getStreamKeyStreamServer\",\"header\":{\"wsKey\":\"stack_tasks\",\"sessionID\":\"59177143e51d2ed29bca13e23e272c19\"},\"parameters\":{\"songID\":\"33031232\",\"country\":{\"IPR\":\"0\",\"DMA\":\"506\",\"ID\":\"223\",\"CC3\":\"0\",\"CC2\":\"0\",\"CC1\":\"0\",\"CC4\":\"1073741824\"},\"lowBitrate\":\"\"}}";
		System.out.println(json);
		System.out.println(SecurityHelper.getHmacMD5(json, Constants.groovesharkSecret));
	}


}
