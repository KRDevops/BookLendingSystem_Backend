package com.ing.bms.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.bms.entity.Book;
import com.ing.bms.entity.Transaction;
import com.ing.bms.repository.BookRepository;
import com.ing.bms.repository.TransactionRepository;

/**
 * @since 2019-10-16 This class includes method for scheduling transactions to
 *        return status when it crosses 3 days
 */
@Service
public class SchedulerServiceImpl implements SchedulerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerServiceImpl.class);

	@Autowired
	BookRepository bookRepository;

	@Autowired
	TransactionRepository transactionRepository;

	/**
	 * Updates the status of the user to returned who has crossed 3 days from
	 * borrowing date
	 * 
	 */
	public void updateStatus() {

		LOGGER.info("updateStatus method in Scheduler Service started");
		List<Book> books = bookRepository.findByAvailabilityStatus("N");
		books.stream().forEach(book -> {
			Transaction transaction = transactionRepository.findByBookIdAndTransactionType(book, "Borrowed");
			transaction.getTransactionDate();
			if (LocalDate.now().compareTo(transaction.getTransactionDate().plusDays(3)) > 0) {
				book.setAvailabilityStatus("Available");
				bookRepository.save(book);

				transaction.setTransactionType("Returned");
				transactionRepository.save(transaction);
			}

		});
		LOGGER.info("updateStatus method in Scheduler Service ended");
	}

}
