package com.ing.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ing.bms.entity.Book;
import com.ing.bms.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	Transaction findByBookIdAndTransactionType(Book bookId, String transactionType);
}
