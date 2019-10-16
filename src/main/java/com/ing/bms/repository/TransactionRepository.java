package com.ing.bms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ing.bms.entity.Book;
import com.ing.bms.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByBookIdAndTransactionType(Book bookId,String transactionType);
}
