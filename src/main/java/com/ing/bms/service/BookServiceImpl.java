package com.ing.bms.service;

import org.springframework.beans.BeanUtils;

import com.ing.bms.dto.BookAddRequestDto;
import com.ing.bms.dto.CommonResponseDto;
import com.ing.bms.entity.Book;

public class BookServiceImpl implements BookService{

	@Override
	public CommonResponseDto add(BookAddRequestDto bookAddRequestDto) {
		Book book=new Book();
		BeanUtils.copyProperties(bookAddRequestDto, book);
		return null;
	}

}
