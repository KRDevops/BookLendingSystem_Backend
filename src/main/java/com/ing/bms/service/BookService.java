package com.ing.bms.service;

import com.ing.bms.dto.BookAddRequestDto;
import com.ing.bms.dto.CommonResponseDto;

public interface BookService {

	public CommonResponseDto add(BookAddRequestDto bookAddRequestDto);
}
