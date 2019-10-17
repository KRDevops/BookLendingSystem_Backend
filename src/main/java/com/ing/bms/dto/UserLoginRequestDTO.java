package com.ing.bms.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserLoginRequestDTO {
	private String emailId;
	private String password;
}
