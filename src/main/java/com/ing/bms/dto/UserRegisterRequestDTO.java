package com.ing.bms.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRegisterRequestDTO {
	
	private String userName;
	private String emailId;
	private Long phoneNumber;

}
