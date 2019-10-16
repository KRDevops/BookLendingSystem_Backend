package com.ing.bms.dto;


public class BookAddRequestDto {

	private Long userId;
	private String bookName;
	private String authorName;
	private String category;
	private Integer publicationYear;
	private Integer isbn;
	public Long getUserId() {
		return userId;
	}
	public String getBookName() {
		return bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public String getCategory() {
		return category;
	}
	public Integer getPublicationYear() {
		return publicationYear;
	}
	public Integer getIsbn() {
		return isbn;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}
	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}

	
}
