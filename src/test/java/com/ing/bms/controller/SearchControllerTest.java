package com.ing.bms.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.ing.bms.dto.SearchDto;
import com.ing.bms.entity.Book;
import com.ing.bms.service.SearchService;

@RunWith(MockitoJUnitRunner.class)
public class SearchControllerTest {
	@InjectMocks
	SearchController searchController;
	@Mock
	SearchService searchService;

	@Test
	public void testSearch(){
		Book book=new Book();
		book.setBookName("s");
		book.setAuthorName("s");
 
		List<Book> books1=new ArrayList<>();
		books1.add(book);
		SearchDto dto=new SearchDto();
		dto.setCount(1);
		dto.setData(books1);
 
		Mockito.when(searchService.search("s", "s",0)).thenReturn(dto);
		ResponseEntity s=searchController.search("s", "s",0);
		assertEquals(200,s.getStatusCodeValue());
 
	}
}