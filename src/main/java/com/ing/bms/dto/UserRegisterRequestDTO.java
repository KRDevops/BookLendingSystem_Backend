package com.ing.bms.dto;

public class UserRegisterRequestDTO {
	
	private String userName;
	private String emailId;
	private Long phoneNumber;
	public String getUserName() {
		return userName;
	}
	public String getEmailId() {
		return emailId;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	

}
