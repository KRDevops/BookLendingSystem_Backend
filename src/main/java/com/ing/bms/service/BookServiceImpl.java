package com.ing.bms.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.bms.dto.BookAddRequestDto;
import com.ing.bms.dto.BookAddResponseDto;
import com.ing.bms.dto.BookTransactionRequestDto;
import com.ing.bms.dto.BookTransactionResponseDto;
import com.ing.bms.entity.Book;
import com.ing.bms.entity.Transaction;
import com.ing.bms.entity.User;
import com.ing.bms.exception.BookException;
import com.ing.bms.repository.BookRepository;
import com.ing.bms.repository.TransactionRepository;
import com.ing.bms.repository.UserRepository;
import com.ing.bms.util.BMSUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @since 2019-10-16 This class includes methods for adding a book and
 *        request/borrow a book.
 * 
 */
@Service
@Slf4j
public class BookServiceImpl implements BookService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	TransactionRepository transactionRepository;

	private static final String TRANSACTIONSTATUS = "Borrow";

	/**
	 * @param bookAddRequestDto
	 * @return BookAddResponseDto which includes user id,message and status code.
	 *         This method will save detail of per book into the respective table.
	 */
	@Override
	public BookAddResponseDto add(BookAddRequestDto bookAddRequestDto) {

		log.info("Into Adding Book Service");

		if( bookRepository.findByIsbn(bookAddRequestDto.getIsbn()) != null ) {
			throw new BookException(BMSUtil.ISBNFOUND);
		}
		BookAddResponseDto bookAddResponseDto = new BookAddResponseDto();

		if (bookAddRequestDto.getUserId() != null) {
			Optional<User> user = userRepository.findById(bookAddRequestDto.getUserId());
			if (!user.isPresent()) {

				throw new BookException(BMSUtil.USER_NOT_FOUND);
			}
			// Creating Book
			Book book = new Book();
			BeanUtils.copyProperties(bookAddRequestDto, book);
			Book bookResponse = bookRepository.save(book);
			bookAddResponseDto.setBookId(bookResponse.getBookId());
		}

		return bookAddResponseDto;
	}

	/**
	 * @param bookTransactionAddRequestDto
	 * @return BookTransactionResponseDto which includes transaction id,message and
	 *         status Code. This method handles the functionality of borrow and
	 *         request a book.
	 */
	@Override
	public BookTransactionResponseDto request(BookTransactionRequestDto bookTransactionAddRequestDto) {

		log.info("Into Request/Borrow Service");

		Optional<Book> book = bookRepository.findById(bookTransactionAddRequestDto.getBookId());
		Optional<User> user = userRepository.findById(bookTransactionAddRequestDto.getUserId());
		Transaction transaction = new Transaction();
		BookTransactionResponseDto bookTransactionResponseDto = new BookTransactionResponseDto();

		if (!user.isPresent()) {
			throw new BookException(BMSUtil.USER_NOT_FOUND);
		}
		if (!book.isPresent()) {
			throw new BookException(BMSUtil.BOOK_NOT_FOUND);
		}
		// Updating As Not Available For Borrowed Book
		if (bookTransactionAddRequestDto.getTransactionType().equals(TRANSACTIONSTATUS)) {
			book.get().setAvailabilityStatus(BMSUtil.AVAILABILITY_STATUS_N);
			bookRepository.save(book.get());
		}
		// Creating Transaction
		transaction.setBookId(book.get());
		transaction.setUserId(user.get());
		transaction.setTransactionType(bookTransactionAddRequestDto.getTransactionType());
		Transaction transactionResponse = transactionRepository.save(transaction);
		// Setting Response
		bookTransactionResponseDto.setTransactionId(transactionResponse.getTransactionId());

		return bookTransactionResponseDto;
	}

}
