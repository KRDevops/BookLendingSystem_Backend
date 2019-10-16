package com.ing.bms.service;

import com.ing.bms.dto.UserLoginRequestDTO;
import com.ing.bms.dto.UserLoginResponseDTO;
import com.ing.bms.dto.UserRegisterRequestDTO;
import com.ing.bms.dto.UserRegisterResponseDTO;

public interface UserService {

	UserRegisterResponseDTO register(UserRegisterRequestDTO userRegisterRequestDTO);

	UserLoginResponseDTO login(UserLoginRequestDTO userLoginRequestDto);

}
