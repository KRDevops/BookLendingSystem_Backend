package com.ing.bms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ing.bms.dto.SearchDto;
import com.ing.bms.service.SearchService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping(value = "/api/v1")
public class SearchController {
	@Autowired
	SearchService searchService;

	@GetMapping(value = "/books")
	public ResponseEntity<SearchDto> search(@RequestParam(required = false) String bookName,
			@RequestParam(required = false) String authorName, @RequestParam Integer pageNumber) {
		return new ResponseEntity<>(searchService.search(bookName, authorName, pageNumber), HttpStatus.OK);
	}

}
