package com.ing.bms.service;

import java.security.NoSuchAlgorithmException;

import com.ing.bms.dto.UserLoginRequestDTO;
import com.ing.bms.dto.UserLoginResponseDTO;
import com.ing.bms.dto.UserRegisterRequestDTO;
import com.ing.bms.dto.UserRegisterResponseDTO;

public interface UserService {

	UserRegisterResponseDTO register(UserRegisterRequestDTO userRegisterRequestDTO) throws NoSuchAlgorithmException;

	UserLoginResponseDTO login(UserLoginRequestDTO userLoginRequestDto);

}
