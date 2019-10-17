package com.ing.bms.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BMSResponseDTO {
	private String message;
	private int statusCode;
}
