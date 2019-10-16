package com.ing.bms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ing.bms.entity.Book;

@Service
public interface SearchService {

	List<Book> search(String bookName, String authorName, Integer pageNumber);

}
