package com.ing.bms.service;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ing.bms.dto.BMSResponseDTO;
import com.ing.bms.entity.Book;
import com.ing.bms.entity.Transaction;
import com.ing.bms.entity.User;
import com.ing.bms.repository.BookRepository;
import com.ing.bms.repository.TransactionRepository;
import com.ing.bms.util.JavaMailUtil;

@RunWith(MockitoJUnitRunner.class)
public class SchedulerServiceTest {

	@InjectMocks
	SchedulerServiceImpl schedulerServiceImpl;

	@Mock
	BookRepository bookRepository;

	@Mock
	TransactionRepository transactionRepository;
	
	@Mock
	JavaMailUtil javaMailUtil;

	List<Book> bookList=new ArrayList<Book>();
	Book book;
	User user;
	Transaction transaction;
	Transaction requestTransaction;
	BMSResponseDTO bMSResponseDTO=new BMSResponseDTO();
	
	@Before
	public void setUp() {
		book = new Book();
		book.setAuthorName("Chetan Bhagat");
		book.setAvailabilityStatus("N");
		book.setBookName("A Tale Of Two Cities");
		book.setCategory("Comic");
		book.setIsbn(9837362455L);
		book.setPublicationYear(1969);
		book.setBookId(3L);
		book.setAvailabilityStatus("Y");

		bookList.add(book);
		
		User user=new User();
		user.setEmailId("afrinafi88077@gmail.com");
		user.setPassword("xyz111");
		user.setPhoneNumber(9837465298L);
		user.setUserId(1L);
		user.setUserName("Afrin");
		
		transaction = new Transaction();
		transaction.setTransactionType("Borrowed");
		transaction.setBookId(book);
		transaction.setUserId(user);
		transaction.setTransactionDate(LocalDate.of(2019, 10, 10));
		transaction.setTransactionId(1L);
		
		
		requestTransaction = new Transaction();
		requestTransaction.setBookId(book);
		requestTransaction.setUserId(user);
		requestTransaction.setTransactionId(1L);
		requestTransaction.setTransactionType("Request");
		requestTransaction.setTransactionDate(LocalDate.of(2019, 10, 10));
	}

	@Test
	public void testUpdateStatus() {
		Mockito.when(bookRepository.findByAvailabilityStatus(Mockito.any())).thenReturn(bookList);
		Mockito.when(transactionRepository.findByBookIdAndTransactionType(Mockito.any(), Mockito.any()))
				.thenReturn(Optional.of(transaction));
		Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);
		Mockito.when(transactionRepository
				.findTop1ByBookIdAndTransactionTypeOrderByTransactionDateAsc(Mockito.any(), Mockito.any())).thenReturn(Optional.of(requestTransaction));
		bMSResponseDTO=schedulerServiceImpl.updateStatus();
		
		Assert.assertEquals("Success", bMSResponseDTO.getMessage());
	}

}
