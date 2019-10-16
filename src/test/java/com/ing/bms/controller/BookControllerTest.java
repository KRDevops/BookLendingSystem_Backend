package com.ing.bms.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.bms.dto.BookAddRequestDto;
import com.ing.bms.dto.BookAddResponseDto;
import com.ing.bms.dto.BookTransactionRequestDto;
import com.ing.bms.dto.BookTransactionResponseDto;
import com.ing.bms.service.BookService;


@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class BookControllerTest {

	@Mock
	BookService bookService;
	
	@InjectMocks
	BookController bookcontroller;
	
	BookAddRequestDto bookAddRequestDto = new BookAddRequestDto();
	BookAddResponseDto bookAddResponseDto = new BookAddResponseDto();
	
	BookTransactionRequestDto bookTransactionRequestDto=new BookTransactionRequestDto();
	BookTransactionResponseDto bookTransactionResponsetDto=new BookTransactionResponseDto();
	
	@Before
	public void setUp() {
		
		bookAddRequestDto.setAuthorName("Charles Dickens");
		bookAddRequestDto.setBookName("A Tale Of Two Cities");
		bookAddRequestDto.setCategory("Comic");
		bookAddRequestDto.setIsbn(9837362455L);
		bookAddRequestDto.setPublicationYear(1969);
		bookAddRequestDto.setUserId(1L);
		
		bookAddResponseDto.setBookId(3L);
		bookAddResponseDto.setMessage("Success");
		bookAddResponseDto.setStatusCode(200);
		
		bookTransactionRequestDto.setBookId(3L);
		bookTransactionRequestDto.setTransactionType("Borrowed");
		bookTransactionRequestDto.setUserId(1L);
		
		bookTransactionResponsetDto.setMessage("Success");
		bookTransactionResponsetDto.setStatusCode(200);
		bookTransactionResponsetDto.setTransactionId(5L);
	}
	
	@Test
	public void testAdd() throws Exception {
		Mockito.when(bookService.add(Mockito.any())).thenReturn(bookAddResponseDto);
		BookAddResponseDto actual=bookcontroller.add(bookAddRequestDto);
		Assert.assertEquals(bookAddResponseDto, actual);
	}
	
	@Test
	public void testRequest() throws Exception {
		Mockito.when(bookService.request(Mockito.any())).thenReturn(bookTransactionResponsetDto);
		BookTransactionResponseDto actual=bookcontroller.request(bookTransactionRequestDto);
		Assert.assertEquals(bookTransactionResponsetDto, actual);
	}
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
