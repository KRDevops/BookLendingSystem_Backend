package com.ing.bms.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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
	User user;

	@Test
	public void testBorrow(){
	 
		 User user=new User();
		 user.setUserId(1L);
		 Transaction transaction=new Transaction();
		 transaction.setTransactionType("request");
		 transaction.setUserId(user);
		 List<Transaction> transactions=new ArrayList<>();
		 transactions.add(transaction);
		 
		 Mockito.when(transactionRepository.findByUserId(user)).thenReturn(transactions);
		 List<Transaction> transac=borrowedService.borrow(user.getUserId());
		        assertEquals(transac.size(),transactions.size());
	 
		}

}