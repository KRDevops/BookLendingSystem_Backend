package com.ing.bms.service;

import java.util.List;

import org.springframework.stereotype.Service;



import com.ing.bms.entity.Transaction;

@Service
public interface BorrowedService {

	List<Transaction> borrow(Long userId) ;
	
}
