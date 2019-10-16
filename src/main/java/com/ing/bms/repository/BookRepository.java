package com.ing.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ing.bms.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
