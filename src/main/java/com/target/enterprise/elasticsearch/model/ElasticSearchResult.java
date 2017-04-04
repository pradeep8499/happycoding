package com.target.enterprise.elasticsearch.model;

public class ElasticSearchResult 
{
	private String errorMessage;
	private boolean isSucceded;
	private String response;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public boolean isSucceded() {
		return isSucceded;
	}
	public void setSucceded(boolean isSucceded) {
		this.isSucceded = isSucceded;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
}
