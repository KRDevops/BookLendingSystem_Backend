package com.ing.bms.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookTransactionRequestDto {

	private Long bookId;
	private Long userId;
	private String transactionType;
	
}
