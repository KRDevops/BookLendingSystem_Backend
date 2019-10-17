package com.ing.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.bms.entity.Transaction;
import com.ing.bms.entity.User;
import com.ing.bms.repository.BookRepository;
import com.ing.bms.repository.TransactionRepository;

@Service
public class BorrowedServiceImpl implements BorrowedService {
	
	@Autowired
	TransactionRepository transactionsRepository;
	@Autowired
	BookRepository bookRepository;

	@Override

	public List<Transaction> borrow(Long userId) {
		User user = new User();
		user.setUserId(userId);
		return transactionsRepository.findByUserId(user);
	}
}
