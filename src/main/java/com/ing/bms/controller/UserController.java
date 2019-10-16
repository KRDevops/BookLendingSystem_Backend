package com.ing.bms.controller;

import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.bms.dto.UserRegisterRequestDTO;
import com.ing.bms.dto.UserRegisterResponseDTO;
import com.ing.bms.service.UserService;


@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/bms/api/v1")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@PostMapping("/registration")
	public ResponseEntity<UserRegisterResponseDTO> register(@RequestBody UserRegisterRequestDTO userRegisterRequestDto)throws NoSuchAlgorithmException {
		LOGGER.info("inside user controller");
		UserRegisterResponseDTO userRegisterResponseDto = userService.register(userRegisterRequestDto);
		return new ResponseEntity<>(userRegisterResponseDto, HttpStatus.CREATED);
	}


	

}
