package com.ing.bms.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ing.bms.dto.SearchDto;
import com.ing.bms.entity.Book;
import com.ing.bms.repository.BookRepository;

@RunWith(MockitoJUnitRunner.class)
public class SearchServiceTest {

	@Mock
	BookRepository booksRepository;

	@InjectMocks
	SearchServiceImpl searchService;

	Book book = new Book();
	Book negativeBook = new Book();
	List<Book> books1 = new ArrayList<>();
	List<Book> bookList = new ArrayList<>();
	SearchDto dto = new SearchDto();
	SearchDto dto1 = new SearchDto();
	@Before
	public void setUp() {
		book.setBookName("s");
		book.setAuthorName("s");

		negativeBook.setBookId(1L);
		negativeBook.setBookName("s");

		books1.add(book);
		bookList.add(negativeBook);

		dto.setCount(1);
		dto.setData(books1);
		dto1.setData(books1);
	}

	@Test
	public void testSearch() {
		Mockito.when(booksRepository.findByAuthorNameAndBookName(Mockito.any(), Mockito.any())).thenReturn(books1);
		SearchDto books = searchService.search("s", "s", 0);
		assertEquals(dto.getCount(), books.getCount());

	}

	@Test
	public void negativeTestSearch() {
		Mockito.when(booksRepository.findByAuthorNameOrBookName(Mockito.any(), Mockito.any())).thenReturn(books1);
		SearchDto books = searchService.search("s", null, 0);
		assertEquals(dto.getCount(), books.getCount());

	}
}
