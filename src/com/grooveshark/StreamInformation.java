package com.grooveshark;

public class StreamInformation {
	private Object StreamKey;
	private Object StreamServerID;
	private Object uSecs;
	private Object url;

	public Object getStreamKey() {
		return StreamKey;
	}
	public void setStreamKey(Object streamKey) {
		StreamKey = streamKey;
	}
	public Object getStreamServerID() {
		return StreamServerID;
	}
	public void setStreamServerID(Object streamServerID) {
		StreamServerID = streamServerID;
	}
	public Object getuSecs() {
		return uSecs;
	}
	public void setuSecs(Object uSecs) {
		this.uSecs = uSecs;
	}
	public Object getUrl() {
		return url;
	}
	public void setUrl(Object url) {
		this.url = url;
	}
	public StreamInformation(Object streamKey, Object streamServerID,
			Object uSecs, Object url) {
		this.StreamKey = streamKey;
		this.StreamServerID = streamServerID;
		this.uSecs = uSecs;
		this.url = url;
	}
	
	
}
