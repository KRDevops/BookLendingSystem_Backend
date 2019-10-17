package com.ing.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.bms.entity.Transaction;
import com.ing.bms.service.BorrowedService;

@RestController
@RequestMapping(value = "/api/v1")
public class BorrowedController {
	@Autowired
	BorrowedService borrowedService;

	@GetMapping(value = "/users/{userId}/books")
	public ResponseEntity<List<Transaction>> myBooks(@PathVariable Long userId) {
		return new ResponseEntity<>(borrowedService.borrow(userId), HttpStatus.OK);

	}

}
