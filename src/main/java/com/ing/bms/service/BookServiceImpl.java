package com.ing.bms.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ing.bms.dto.BookAddRequestDto;
import com.ing.bms.dto.BookAddResponseDto;
import com.ing.bms.entity.Book;
import com.ing.bms.entity.User;
import com.ing.bms.exception.BookException;
import com.ing.bms.repository.BookRepository;
import com.ing.bms.repository.UserRepository;

/**
 * @since 2019-10-16 This class includes methods for add a book
 *      
 */
@Service
public class BookServiceImpl implements BookService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Value("${user.notfound}")
	private String userNotFound;
	
	/**
	 * @param bookAddRequestDto
	 * @return BookAddResponseDto which includes user id,status code and
	 *         success message. This method will save detail of per book into the respective table. 
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

	@Override
	public BookAddResponseDto request(BookAddRequestDto bookAddRequestDto) {
		
		return null;
	}

}
