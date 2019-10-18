package com.ing.bms.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ing.bms.entity.Book;
import com.ing.bms.entity.Transaction;
import com.ing.bms.entity.User;
import com.ing.bms.repository.TransactionRepository;


@RunWith(MockitoJUnitRunner.class)
public class BorrowedServiceTest {
	@Mock
	TransactionRepository transactionRepository;

	@InjectMocks
	BorrowedServiceImpl borrowedService;
	@Mock
	User user=new User();
	Transaction transaction=new Transaction();
	List<Transaction> transactions=new ArrayList<Transaction>();
	Book book=new Book();
	
	@Before
	public void setup() {
		
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
		
		transaction.setTransactionType("Request");
		transaction.setUserId(user);
		transaction.setTransactionId(2L);
		transaction.setTransactionDate(LocalDate.of(2019, 10, 9));
		transaction.setBookId(book);
		
		transactions.add(transaction);
	}

	@Test
	public void testBorrow(){
		 
		 Mockito.when(transactionRepository.findByUserId(Mockito.any())).thenReturn(transactions);
		 List<Transaction> transac=borrowedService.borrow(user.getUserId());
		        assertEquals(transactions.size(),transac.size());
	 
		}

}