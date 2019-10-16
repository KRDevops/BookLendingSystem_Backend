package com.ing.bms.service;

import com.ing.bms.dto.BookAddRequestDto;
import com.ing.bms.dto.BookAddResponseDto;

public interface BookService {

	public BookAddResponseDto add(BookAddRequestDto bookAddRequestDto);
	
	public BookAddResponseDto request(BookAddRequestDto bookAddRequestDto);
}
