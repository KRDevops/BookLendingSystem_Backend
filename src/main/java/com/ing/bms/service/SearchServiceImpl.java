package com.ing.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ing.bms.dto.SearchDto;
import com.ing.bms.entity.Book;
import com.ing.bms.repository.BookRepository;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	BookRepository booksRepository;

	@Override
	public SearchDto search(String bookName, String authorName, Integer pageNumber) {

		Pageable paging = PageRequest.of(pageNumber, 1);

		if (bookName != null && authorName != null) {
			List<Book> books = booksRepository.findByAuthorNameAndBookName(authorName, bookName);
			List<Book> books1 = booksRepository.findByAuthorNameAndBookName(authorName, bookName, paging);
			SearchDto searchDto = new SearchDto();
			searchDto.setData(books1);
			searchDto.setCount(books.size());

			return searchDto;
		} else {
			List<Book> books = booksRepository.findByAuthorNameOrBookName(authorName, bookName);
			List<Book> books1 = booksRepository.findByAuthorNameOrBookName(authorName, bookName, paging);
			SearchDto searchDto = new SearchDto();
			searchDto.setData(books1);
			searchDto.setCount(books.size());
			return searchDto;
		}
	}
}
