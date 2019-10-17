package com.ing.bms.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookAddRequestDto {

	private Long userId;
	private String bookName;
	private String authorName;
	private String category;
	private Integer publicationYear;
	private Long isbn;
}
