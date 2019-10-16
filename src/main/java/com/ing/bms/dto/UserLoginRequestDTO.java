package com.ing.bms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDTO {
	private String emailId;
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String password;
}
