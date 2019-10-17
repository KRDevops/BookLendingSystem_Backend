package com.ing.bms.service;

import org.springframework.stereotype.Service;

import com.ing.bms.dto.SearchDto;

@Service
public interface SearchService {

SearchDto search(String bookName, String authorName, Integer pageNumber);

}
