package com.ing.bms.service;

import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;

import com.ing.bms.dto.UserLoginRequestDTO;
import com.ing.bms.dto.UserLoginResponseDTO;
import com.ing.bms.dto.UserRegisterRequestDTO;
import com.ing.bms.dto.BMSResponseDTO;

public interface UserService {

	BMSResponseDTO register(UserRegisterRequestDTO userRegisterRequestDTO)
			throws NoSuchAlgorithmException, MessagingException;

	UserLoginResponseDTO login(UserLoginRequestDTO userLoginRequestDto);

}
