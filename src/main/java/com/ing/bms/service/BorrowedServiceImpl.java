package com.ing.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.bms.entity.Transaction;
import com.ing.bms.entity.User;
import com.ing.bms.repository.BooksRepository;
import com.ing.bms.repository.TransactionsRepository;

@Service
public class BorrowedServiceImpl implements BorrowedService {
	@Autowired
	TransactionsRepository transactionsRepository;
	@Autowired
	BooksRepository bookRepository;

	@Override

	public List<Transaction> borrow(Long userId) {
		User user = new User();
		user.setUserId(userId);

		return transactionsRepository.findByUserId(user);
	}
}
