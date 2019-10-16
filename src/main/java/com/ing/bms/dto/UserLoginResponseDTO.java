package com.ing.bms.dto;

public class UserLoginResponseDTO {
	
	private Long userId;
	private String message;
	private int statusCode;
	public Long getUserId() {
		return userId;
	}
	public String getMessage() {
		return message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	

}
