package com.ing.bms.dto;

public class UserRegisterResponseDTO {
	private String message;
	private int statusCode;
	public String getMessage() {
		return message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	

}
