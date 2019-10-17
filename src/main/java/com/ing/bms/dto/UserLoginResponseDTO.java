package com.ing.bms.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserLoginResponseDTO {
	
	private Long userId;
	private String message;
	private int statusCode;
}
