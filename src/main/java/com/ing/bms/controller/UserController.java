package com.ing.bms.controller;

import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.bms.dto.UserLoginRequestDTO;
import com.ing.bms.dto.UserLoginResponseDTO;
import com.ing.bms.dto.UserRegisterRequestDTO;
import com.ing.bms.dto.UserRegisterResponseDTO;
import com.ing.bms.service.UserService;
import com.ing.bms.util.BMSUtil;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("api/v1")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Value("${statuscode.success}")
	private String statusCode;

	@PostMapping("/registration")
	public ResponseEntity<UserRegisterResponseDTO> register(@RequestBody UserRegisterRequestDTO userRegisterRequestDto)
			throws NoSuchAlgorithmException, MessagingException {
		LOGGER.info("register in user controller method started");
		UserRegisterResponseDTO userRegisterResponseDto = userService.register(userRegisterRequestDto);
		userRegisterResponseDto.setMessage(BMSUtil.SUCCESS);
		userRegisterResponseDto.setStatusCode(200);
		LOGGER.info("register in user controller method ended");
		return new ResponseEntity<>(userRegisterResponseDto, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginRequestDTO userLoginRequestDto) {
		LOGGER.info("login in user controller method started");
		UserLoginResponseDTO userLoginResponseDto = userService.login(userLoginRequestDto);
		userLoginResponseDto.setMessage(BMSUtil.LOGIN_SUCCESS);
		userLoginResponseDto.setStatusCode(200);
		LOGGER.info("login in user controller method ended");
		return new ResponseEntity<>(userLoginResponseDto, HttpStatus.OK);
	}

}
