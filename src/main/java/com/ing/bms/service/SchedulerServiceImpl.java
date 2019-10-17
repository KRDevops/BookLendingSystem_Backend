package com.ing.bms.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ing.bms.entity.Book;
import com.ing.bms.entity.Transaction;
import com.ing.bms.repository.BookRepository;
import com.ing.bms.repository.TransactionRepository;
import com.ing.bms.repository.UserRepository;
import com.ing.bms.util.JavaMailUtil;

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

	@Autowired
	JavaMailUtil javaMailUtil;

	@Autowired
	UserRepository userRepository;

	@Value("${status.notavailable}")
	private String notAvailable;

	@Value("${status.available}")
	private String availableStatus;

	@Value("${status.returned}")
	private String returnStatus;

	@Value("${transactionType}")
	private String transactionType;

	@Value("${availabilityMessage}")
	private String availabilityMessage;

	/**
	 * Updates the status of the user who has exceeded 3 days from borrowing date as
	 * "returned" to make it available for another user
	 * 
	 */
	public void updateStatus() {

		LOGGER.info("updateStatus method in Scheduler Service started");
		List<Book> books = bookRepository.findByAvailabilityStatus(notAvailable);
		books.stream().forEach(book -> {
			Optional<Transaction> transaction = transactionRepository.findByBookIdAndTransactionType(book,
					transactionType);
			if (!transaction.isPresent()) {
				LOGGER.info("No transactions found with borrowed transaction type");
			} else {
				if (LocalDate.now().compareTo(transaction.get().getTransactionDate().plusDays(3)) > 0) {
					book.setAvailabilityStatus(availableStatus);
					bookRepository.save(book);

					transaction.get().setTransactionType(returnStatus);
					transactionRepository.save(transaction.get());
					Optional<Transaction> requestedTransaction = transactionRepository
							.findByBookIdAndTransactionType(book, "Requested");
					if (requestedTransaction.isPresent()) {
						try {
							javaMailUtil.sendMail(requestedTransaction.get().getUserId().getEmailId(),
									availabilityMessage);
						} catch (MessagingException e) {
							LOGGER.error(e.getMessage());
						}
					}
				}
			}

		});
		LOGGER.info("updateStatus method in Scheduler Service ended");
	}

}
