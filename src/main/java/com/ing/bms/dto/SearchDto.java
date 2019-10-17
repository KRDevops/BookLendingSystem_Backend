package com.ing.bms.dto;

import java.util.List;

import com.ing.bms.entity.Book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchDto {
	
	private List<Book> data;
	private Integer count;

}
