package com.ing.bms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@ToString
public class Book implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookId;
	
	@Column(name = "book_name", nullable = false)
	private String bookName;
	
	@Column(name = "author_name", nullable = false)
	private String authorName;
	
	@Column(name = "category", nullable = false)
	private String category;
	
	@Column(name = "publication_year", nullable = false)
	private String publicationYear;
	
	@Column(name = "availability_status", nullable = false)
	private String availabilityStatus = "Available";
	
	@Column(name = "isbn", nullable = false, unique= true)
	private String isbn;

}
