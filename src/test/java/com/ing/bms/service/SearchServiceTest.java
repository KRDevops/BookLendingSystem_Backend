package com.ing.bms.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.ing.bms.dto.SearchDto;
import com.ing.bms.entity.Book;
import com.ing.bms.repository.BookRepository;

@RunWith(MockitoJUnitRunner.class)
public class SearchServiceTest {

	@Mock
	BookRepository booksRepository;

	@InjectMocks
	SearchServiceImpl searchService;
	
	@Test
	public void testSearch()
	{
		 Pageable paging = PageRequest.of(0,1);
		 Book book=new Book();
		 book.setBookName("s");
		 book.setAuthorName("s");
		 
		 List<Book> books1=new ArrayList<>();
		 books1.add(book);
		 SearchDto dto=new SearchDto();
		 dto.setCount(1);
		 dto.setData(books1);
		 Mockito.when(booksRepository.findByAuthorNameAndBookName("s","s")).thenReturn(books1);
		 Mockito.when(booksRepository.findByAuthorNameAndBookName("s","s", paging)).thenReturn(books1);
		 SearchDto books=searchService.search("s","s", 0);
		 assertEquals(books.getCount(),dto.getCount());
		 
		 
	}
}

