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
	

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	

	public String getAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	



	@Column(name = "author_name", nullable = false)
	private String authorName;
	@Column(name = "book_name", nullable = false)
	private String bookName;
	@Column(name = "category", nullable = false)
	private String category;
	
	@Column(name = "publication_year", nullable = false)
	private Integer publicationYear;
	
	public Integer getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}

	public Integer getIsbn() {
		return isbn;
	}

	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}





	@Column(name = "availability_status", nullable = false)
	private String availabilityStatus = "Available";
	
	@Column(name = "isbn", nullable = false, unique= true)
	private Integer isbn;

}
