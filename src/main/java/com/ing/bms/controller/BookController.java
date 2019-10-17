package com.ing.bms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.bms.dto.BookAddRequestDto;
import com.ing.bms.dto.BookAddResponseDto;
import com.ing.bms.dto.BookTransactionRequestDto;
import com.ing.bms.dto.BookTransactionResponseDto;
import com.ing.bms.service.BookService;
import com.ing.bms.util.BMSUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@Slf4j
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@Value("${book.add.successcode}")
	private Integer successCode;
	
	@Value("${book.add.successmessage}")
	private String message;
	
	@PostMapping("/add")
	public BookAddResponseDto add(@Valid @RequestBody BookAddRequestDto bookAddRequestDto) {
		
		log.info("Into Book Add Controller");
		
		BookAddResponseDto bookAddResponseDto=bookService.add(bookAddRequestDto);
		if(bookAddResponseDto.getBookId() != null) {
			bookAddResponseDto.setMessage(BMSUtil.GENERICSUCCESSMESSAGE);
			bookAddResponseDto.setStatusCode(BMSUtil.GENERICSUCCESSCODE);
		}
		else {
			bookAddResponseDto.setMessage(BMSUtil.GENERICFAILUREMESSAGE);
			bookAddResponseDto.setStatusCode(BMSUtil.GENERICFAILURECODE);
		}
		return bookAddResponseDto;
	}

	@PostMapping("/requests")
	public BookTransactionResponseDto request(@Valid @RequestBody BookTransactionRequestDto bookTransactionAddRequestDto) {
		
		log.info("Into Book Request/Borrow Controller");
		
		BookTransactionResponseDto bookTransactionResponseDto=bookService.request(bookTransactionAddRequestDto);
		if(bookTransactionResponseDto.getTransactionId() != null) {
			bookTransactionResponseDto.setMessage(BMSUtil.GENERICSUCCESSMESSAGE);
			bookTransactionResponseDto.setStatusCode(BMSUtil.GENERICSUCCESSCODE);
		}
		else {
			bookTransactionResponseDto.setMessage(BMSUtil.GENERICFAILUREMESSAGE);
			bookTransactionResponseDto.setStatusCode(BMSUtil.GENERICFAILURECODE);
		}
		return bookTransactionResponseDto;
	}
	
}
