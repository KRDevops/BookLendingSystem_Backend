package com.ing.bms.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ing.bms.entity.Book;
import com.ing.bms.entity.Transaction;
import com.ing.bms.repository.BookRepository;
import com.ing.bms.repository.TransactionRepository;

@RunWith(MockitoJUnitRunner.class)
public class SchedulerServiceTest {

	@InjectMocks
	SchedulerServiceImpl schedulerServiceImpl;

	@Mock
	BookRepository bookRepository;

	@Mock
	TransactionRepository transactionRepository;

	List<Book> books;
	Book book;
	Transaction transaction;

	@Before
	public void setUp() {
		book = new Book();
		book.setAuthorName("Chetan Bhagat");
		book.setAvailabilityStatus("N");

		books.add(book);
		transaction = new Transaction();
		transaction.setTransactionType("Borrowed");

	}

	@Test
	public void testUpdateStatus() {
		Mockito.when(bookRepository.findByAvailabilityStatus(Mockito.any())).thenReturn(books);
		Mockito.when(transactionRepository.findByBookIdAndTransactionType(Mockito.any(), Mockito.any()))
				.thenReturn(Optional.of(transaction));
		schedulerServiceImpl.updateStatus();
		assertNotNull(transaction);
	}

}
