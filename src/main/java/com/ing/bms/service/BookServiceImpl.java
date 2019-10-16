package com.ing.bms.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class BookServiceImpl implements BookService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Value("${user.notfound}")
	private String userNotFound;
	
	@Value("${book.invalid}")
	private String bookNotFound;
	
	
	/**
	 * @param bookAddRequestDto
	 * @return BookAddResponseDto which includes user id,message and status code.
	 *         This method will save detail of per book into the respective table. 
	 */
	@Override
	public BookAddResponseDto add(BookAddRequestDto bookAddRequestDto) {
		
	
		
		BookAddResponseDto bookAddResponseDto=new BookAddResponseDto();
		
		if(bookAddRequestDto.getUserId() != null) {
			Optional<User> user=userRepository.findById(bookAddRequestDto.getUserId());
			if(!user.isPresent()) {
				throw new BookException(userNotFound);
			}
			Book book=new Book();
			BeanUtils.copyProperties(bookAddRequestDto, book);
			Book bookResponse=bookRepository.save(book);
			bookAddResponseDto.setBookId(bookResponse.getBookId());
		}
			
		return bookAddResponseDto;
	}
	
	/**
	 * @param bookTransactionAddRequestDto
	 * @return BookTransactionResponseDto which includes transaction id,message and status code.
	 */
	@Override
	public BookTransactionResponseDto request(BookTransactionRequestDto bookTransactionAddRequestDto) {
	
		
		Book book=new Book();
		User user=new User();
		Transaction transaction=new Transaction();
		BookTransactionResponseDto bookTransactionResponseDto=new BookTransactionResponseDto();
		if(! userRepository.findById(bookTransactionAddRequestDto.getUserId()).isPresent()) {
			throw new BookException(userNotFound);
		}
		if(! bookRepository.findById(bookTransactionAddRequestDto.getBookId()).isPresent()) {
			throw new BookException(bookNotFound);
		}
		book.setBookId(bookTransactionAddRequestDto.getBookId());
		user.setUserId(bookTransactionAddRequestDto.getUserId());
		transaction.setBookId(book);
		transaction.setUserId(user);
		transaction.setTransactionType(bookTransactionAddRequestDto.getTransactionType());
		Transaction transactionResponse=transactionRepository.save(transaction);
		bookTransactionResponseDto.setTransactionId(transactionResponse.getTransactionId());
		return bookTransactionResponseDto;
	}

}
