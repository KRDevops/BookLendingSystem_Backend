package com.ing.bms.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ing.bms.entity.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book,Integer> {
@Query("select c from Book c where c.authorName=:author or c.bookName=:book")
	List<Book> findByAuthorNameOrBookName(@Param("author") String authorName,@Param("book") String bookName,Pageable paging);


@Query("select c from Book c where c.authorName=:author and c.bookName=:book")
List<Book> findByAuthorNameAndBookName(@Param("author") String authorName,@Param("book")  String bookName, Pageable paging);

}
