package com.ing.bms.service;

import com.ing.bms.dto.BookAddRequestDto;
import com.ing.bms.dto.BookAddResponseDto;
import com.ing.bms.dto.BookTransactionRequestDto;
import com.ing.bms.dto.BookTransactionResponseDto;

public interface BookService {

	public BookAddResponseDto add(BookAddRequestDto bookAddRequestDto);
	
	public BookTransactionResponseDto request(BookTransactionRequestDto bookTransactionAddRequestDto);
}
