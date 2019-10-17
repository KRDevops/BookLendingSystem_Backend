package com.ing.bms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ing.bms.entity.Book;
import com.ing.bms.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	Optional<Transaction> findByBookIdAndTransactionType(Book bookId, String transactionType);

	Optional<Transaction> findTop1ByBookIdAndTransactionTypeOrderByTransactionDateAsc(Book bookId, String transactionType);
}
