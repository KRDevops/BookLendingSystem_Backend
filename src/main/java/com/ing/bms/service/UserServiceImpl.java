package com.ing.bms.service;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ing.bms.dto.UserLoginRequestDTO;
import com.ing.bms.dto.UserLoginResponseDTO;
import com.ing.bms.dto.UserRegisterRequestDTO;
import com.ing.bms.dto.UserRegisterResponseDTO;
import com.ing.bms.entity.User;
import com.ing.bms.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public UserRegisterResponseDTO register(UserRegisterRequestDTO userRegisterRequestDto) {
		
		User register=new User();
		UserRegisterResponseDTO responseDTO=new UserRegisterResponseDTO();
		register.setUserName(userRegisterRequestDto.getUserName());
		register.setEmailId(userRegisterRequestDto.getEmailId());
		register.setPhoneNumber(userRegisterRequestDto.getPhoneNumber());
		register.setPassword(generatePassword());
		
		userRepository.save(register);
		responseDTO.setMessage("User Registered Succesfully");
		responseDTO.setStatusCode(200);
		return responseDTO;
}

	private String generatePassword() {
		SecureRandom r = new SecureRandom();
		final String alphaCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final String alpha = "abcdefghijklmnopqrstuvwxyz";
		final String numeric = "0123456789";
		final String specialChars = "!@#$%^&*_=+-/";
		int length = 5;
		String dic = alphaCaps + alpha + numeric + specialChars;
		String result = "";
		for (int i = 0; i < length; i++) {
			int index = r.nextInt(dic.length());
			result += dic.charAt(index);
		}
		return result;
	}

	
	public UserLoginResponseDTO login(UserLoginRequestDTO userLoginRequestDto) {
		User register = userRepository.findByUserNameAndPassword(userLoginRequestDto.getUserName(),
				userLoginRequestDto.getPassword());

		/*(if (customer == null) {
			throw new CommonException(ExceptionConstants.USER_NOT_FOUND);
		}*/
		UserLoginResponseDTO userLoginResponseDto = new UserLoginResponseDTO();
		userLoginResponseDto.setUserId(register.getUserId());
		userLoginResponseDto.setMessage("logged in successfully");
		return userLoginResponseDto;


		}

	
		
}
