package com.ing.bms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ing.bms.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByAvailabilityStatus(String availabitilyStatus);
}
