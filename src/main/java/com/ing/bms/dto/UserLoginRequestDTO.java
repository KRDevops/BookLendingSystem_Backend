package com.ing.bms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDTO {
	private String emailId;
	private String password;
}
