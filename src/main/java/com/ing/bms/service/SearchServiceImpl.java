package com.ing.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ing.bms.entity.Book;
import com.ing.bms.repository.BooksRepository;

@Service
public class SearchServiceImpl implements SearchService {
@Autowired
BooksRepository booksRepository;
	@Override
	public List<Book> search(String bookName, String authorName, Integer pageNumber) {
		
		Pageable paging = PageRequest.of(pageNumber,2);
		 
		if(bookName!=null&&authorName!=null){
			List<Book> books=booksRepository.findByAuthorNameAndBookName(authorName,bookName,paging);
return books;
		}
		else
		{
	List<Book> books=booksRepository.findByAuthorNameOrBookName(authorName,bookName,paging);

	return books;
	}
	}
}
