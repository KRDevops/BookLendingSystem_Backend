package com.ing.bms.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.ing.bms.entity.Transaction;
import com.ing.bms.entity.User;
import com.ing.bms.service.BorrowedService;

@RunWith(MockitoJUnitRunner.class)
public class BorrowedControllerTest {
	@InjectMocks
	BorrowedController borrowedController;
	@Mock
	BorrowedService borrowedService;
	@Test
	public void testBorrowed(){
	User user=new User();
	user.setUserId(1L);
	Transaction transaction=new Transaction();
	transaction.setTransactionType("request");
	transaction.setUserId(user);
	List<Transaction> transactions=new ArrayList<>();
	transactions.add(transaction);
	 Mockito.when(borrowedService.borrow(user.getUserId())).thenReturn(transactions);
	 ResponseEntity s=borrowedController.myBooks(user.getUserId());
	 assertEquals(200,s.getStatusCodeValue());
	   
	}
	}

