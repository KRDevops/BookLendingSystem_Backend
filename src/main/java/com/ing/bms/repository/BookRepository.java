package com.ing.bms.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ing.bms.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByAvailabilityStatus(String availabitilyStatus);
	@Query("select c from Book c where c.authorName like %:author% or c.bookName like %:book%")
	List<Book> findByAuthorNameOrBookName(@Param("author") String authorName,@Param("book") String bookName,Pageable paging);
	@Query("select c from Book c where c.authorName like %:author% and c.bookName like %:book%")
	List<Book> findByAuthorNameAndBookName(@Param("author") String authorName,@Param("book")  String bookName, Pageable paging);
	@Query("select c from Book c where c.authorName like %:author% and c.bookName like %:book%")
	
	List<Book> findByAuthorNameAndBookName(@Param("author") String authorName, @Param("book") String bookName);
	
	@Query("select c from Book c where c.authorName like %:author% or c.bookName like %:book%")
	List<Book> findByAuthorNameOrBookName(@Param("author")  String authorName, @Param("book") String bookName);

}
