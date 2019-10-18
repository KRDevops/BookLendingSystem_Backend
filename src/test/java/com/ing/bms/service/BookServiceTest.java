package com.ing.bms.service;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ing.bms.dto.BookAddRequestDto;
import com.ing.bms.dto.BookAddResponseDto;
import com.ing.bms.dto.BookTransactionRequestDto;
import com.ing.bms.dto.BookTransactionResponseDto;
import com.ing.bms.entity.Book;
import com.ing.bms.entity.Transaction;
import com.ing.bms.entity.User;
import com.ing.bms.exception.BookException;
import com.ing.bms.repository.BookRepository;
import com.ing.bms.repository.TransactionRepository;
import com.ing.bms.repository.UserRepository;


@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

	@Mock
	BookRepository bookRepository;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	TransactionRepository transactionRepository;
	
	@InjectMocks
	BookServiceImpl bookServiceImpl;
	
	User user = new User();
	Book book = new Book();
	Transaction transaction = new Transaction();
	
	BookAddRequestDto bookAddRequestDto = new BookAddRequestDto();
	BookAddResponseDto bookAddResponseDto = new BookAddResponseDto();
	BookTransactionRequestDto bookTransactionRequestDto=new BookTransactionRequestDto();
	BookTransactionResponseDto bookTransactionResponsetDto=new BookTransactionResponseDto();
	
	@Before
	public void setup() {
		bookAddRequestDto.setAuthorName("Charles Dickens");
		bookAddRequestDto.setBookName("A Tale Of Two Cities");
		bookAddRequestDto.setCategory("Comic");
		bookAddRequestDto.setIsbn(9837362455L);
		bookAddRequestDto.setPublicationYear(1969);
		bookAddRequestDto.setUserId(1L);
		
		user.setEmailId("afrinafi88077@gmail.com");
		user.setPassword("xyz111");
		user.setPhoneNumber(9837465298L);
		user.setUserId(1L);
		user.setUserName("Afrin");
		
		book.setAuthorName("Charles Dickens");
		book.setBookName("A Tale Of Two Cities");
		book.setCategory("Comic");
		book.setIsbn(9837362455L);
		book.setPublicationYear(1969);
		book.setBookId(3L);
		book.setAvailabilityStatus("Y");
		
		transaction.setBookId(book);
		transaction.setUserId(user);
		transaction.setTransactionId(5L);
		transaction.setTransactionType("Borrowed");
		transaction.setTransactionDate(LocalDate.of(2019, 10, 16));
		
		bookTransactionRequestDto.setBookId(3L);
		bookTransactionRequestDto.setTransactionType("Borrowed");
		bookTransactionRequestDto.setUserId(1L);
		
		
	}
	
	@Test
	public void positiveTestAdd() {
		
		Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
		Mockito.when(bookRepository.findByIsbn(bookAddRequestDto.getIsbn())).thenReturn(null);
		Mockito.when(bookRepository.save(Mockito.any())).thenReturn(book);
		bookAddResponseDto=bookServiceImpl.add(bookAddRequestDto);
		Assert.assertEquals(Long.valueOf(3), bookAddResponseDto.getBookId());
	}
	
	@Test(expected=BookException.class)
	public void negativeTestAdd() {
		Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		Mockito.when(bookRepository.findByIsbn(bookAddRequestDto.getIsbn())).thenReturn(null);
		bookAddResponseDto=bookServiceImpl.add(bookAddRequestDto);
		Assert.assertEquals(null, bookAddResponseDto.getBookId());
	}
	
	@Test(expected=BookException.class)
	public void negative1TestAdd() {
		Mockito.when(bookRepository.findByIsbn(bookAddRequestDto.getIsbn())).thenReturn(book);
		bookAddResponseDto=bookServiceImpl.add(bookAddRequestDto);
		Assert.assertEquals(null, bookAddResponseDto.getBookId());
	}
	
	@Test
	public void positiveTestRequest() {
		
		Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
		Mockito.when(bookRepository.findById(Mockito.any())).thenReturn(Optional.of(book));
		Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);
		//Mockito.when(bookRepository.save(Mockito.any())).thenReturn(book);
		bookTransactionResponsetDto=bookServiceImpl.request(bookTransactionRequestDto);
		Assert.assertEquals(Long.valueOf(5L), bookTransactionResponsetDto.getTransactionId());
	}
	
	@Test(expected=BookException.class)
	public void negativeTestRequest() {
		Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
		Mockito.when(bookRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		bookTransactionResponsetDto=bookServiceImpl.request(bookTransactionRequestDto);
		Assert.assertEquals(Long.valueOf(5L), bookTransactionResponsetDto.getTransactionId());
	}
}
