package com.ing.bms.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookTransactionResponseDto {

	private Long transactionId;
	private String message;
	private Integer statusCode;
}
