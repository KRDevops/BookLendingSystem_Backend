package com.ing.bms.controller;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ing.bms.dto.UserLoginRequestDTO;
import com.ing.bms.dto.UserLoginResponseDTO;
import com.ing.bms.dto.UserRegisterRequestDTO;
import com.ing.bms.dto.BMSResponseDTO;
import com.ing.bms.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;
	
	UserRegisterRequestDTO userRegisterRequest=new UserRegisterRequestDTO();

	UserLoginRequestDTO userLoginRequest=new UserLoginRequestDTO();

	BMSResponseDTO bmsResponseDto=new BMSResponseDTO();

	UserLoginResponseDTO userLoginResponse=new UserLoginResponseDTO();

	@Before
	public void setup() {

		userRegisterRequest.setEmailId("tsb@gmail.com");
		userRegisterRequest.setPhoneNumber(8884148999L);
		userRegisterRequest.setUserName("tsb");

		userLoginRequest.setEmailId("tsb@gmail.com");
		userLoginRequest.setPassword("abc123");
		
		
		bmsResponseDto.setMessage("Success");
		bmsResponseDto.setStatusCode(201);
		
		userLoginResponse.setMessage("Success");
		userLoginResponse.setStatusCode(201);
		userLoginResponse.setUserId(1L);

	}

	@Test
	public void testRegister() throws NoSuchAlgorithmException, MessagingException {
		
		Mockito.when(userService.register(Mockito.any())).thenReturn(bmsResponseDto);
		ResponseEntity<BMSResponseDTO> actual = userController.register(userRegisterRequest);
		ResponseEntity<BMSResponseDTO> expected = new ResponseEntity<BMSResponseDTO>(
				bmsResponseDto, HttpStatus.CREATED);
		assertEquals(expected.getStatusCode().value(), actual.getStatusCodeValue());

	}

	@Test
	public void testLogin() {
		Mockito.when(userService.login(Mockito.any())).thenReturn(userLoginResponse);
		ResponseEntity<UserLoginResponseDTO> actual = userController.login(userLoginRequest);
		ResponseEntity<UserLoginResponseDTO> expected = new ResponseEntity<UserLoginResponseDTO>(userLoginResponse,
				HttpStatus.OK);
		assertEquals(expected.getStatusCode().value(), actual.getStatusCodeValue());

	}
}
