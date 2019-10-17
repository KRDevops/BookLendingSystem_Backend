package com.ing.bms.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookAddResponseDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String message;
	private Integer statusCode;
	private Long bookId;
}
