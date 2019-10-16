package com.ing.bms.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
import com.ing.bms.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import com.ing.bms.repository.TransactionRepository;

/**
 * @since 2019-10-16 This class includes methods for add a book
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
	
	@Autowired
    private JavaMailSender javaMailSender;

	@Value("${user.notfound}")
	private String userNotFound;

	@Value("${book.invalid}")
	private String bookNotFound;
	
	private static final String TRANSACTIONSTATUS="Borrowed";

	/**
	 * @param bookAddRequestDto
	 * @return BookAddResponseDto which includes user id,message and status code.
	 *         This method will save detail of per book into the respective table.
	 */
	@Override
	public BookAddResponseDto add(BookAddRequestDto bookAddRequestDto) {

		log.info("Into Adding Book Service");

		BookAddResponseDto bookAddResponseDto = new BookAddResponseDto();

		if (bookAddRequestDto.getUserId() != null) {
			Optional<User> user = userRepository.findById(bookAddRequestDto.getUserId());
			if (!user.isPresent()) {
				throw new BookException(userNotFound);
			}
			//Creating Book
			Book book = new Book();
			BeanUtils.copyProperties(bookAddRequestDto, book);
			Book bookResponse = bookRepository.save(book);
			bookAddResponseDto.setBookId(bookResponse.getBookId());
		}

		return bookAddResponseDto;
	}

	/**
	 * @param bookTransactionAddRequestDto
	 * @return BookTransactionResponseDto which includes transaction id,message and status Code.
	 *         This method handles the functionality of borrow and request a book.
	 */
	@Override
	public BookTransactionResponseDto request(BookTransactionRequestDto bookTransactionAddRequestDto) {
		
		log.info("Into Request/Borrow Service");

		Optional<Book> book = bookRepository.findById(bookTransactionAddRequestDto.getBookId());
		Optional<User> user = userRepository.findById(bookTransactionAddRequestDto.getUserId());
		Transaction transaction = new Transaction();
		BookTransactionResponseDto bookTransactionResponseDto = new BookTransactionResponseDto();
		
		if (! user.isPresent()) {
			throw new BookException(userNotFound);
		}
		if (! book.isPresent()) {
			throw new BookException(bookNotFound);
		}
		//Updating As Not Available For Borrowed Book
		if(bookTransactionAddRequestDto.getTransactionType() == TRANSACTIONSTATUS) {
			book.get().setAvailabilityStatus("N");
			bookRepository.save(book.get());
		}
		//Creating Transaction
		transaction.setBookId(book.get());
		transaction.setUserId(user.get());
		transaction.setTransactionType(bookTransactionAddRequestDto.getTransactionType());
		Transaction transactionResponse = transactionRepository.save(transaction);
		//Setting Response
		bookTransactionResponseDto.setTransactionId(transactionResponse.getTransactionId());
		
		return bookTransactionResponseDto;
	}
	
	@Override
	public void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("afrinafi88077@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }

}
