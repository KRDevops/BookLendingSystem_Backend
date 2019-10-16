package com.ing.bms.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ing.bms.dto.UserLoginRequestDTO;
import com.ing.bms.dto.UserLoginResponseDTO;
import com.ing.bms.dto.UserRegisterRequestDTO;
import com.ing.bms.dto.UserRegisterResponseDTO;
import com.ing.bms.entity.User;
import com.ing.bms.repository.UserRepository;


	
	@RunWith(MockitoJUnitRunner.class)
	public class UserServiceTest {
		
		@Mock
		UserRepository userRepository;
		
		@InjectMocks
		UserServiceImpl userServiceImpl;
		
		@Mock
		User user;
		
		UserRegisterResponseDTO userRegisterResponseDto;
		
		UserRegisterRequestDTO userRegisterRequestDto;
		
		UserLoginResponseDTO userLoginResponseDto;
		
		UserLoginRequestDTO userLoginRequestDto;
		
		@Before
		public void setup() {
			
			User register=new User();
			register.setUserName("Pranaya");
			register.setPassword("gu32");
			register.setUserId(1L);
			register.setPhoneNumber(785659759L);
			register.setEmailId("k@gmail.com");
			
			
		}
		@Test
		public void testRegister() 
		{
			//Mockito.when(userRepository.save(Mockito.any())).thenReturn(register);
			//UserRegisterResponseDTO userRegisterResponseDto = userServiceImpl.register(userRegisterRequestDto);
			Assert.assertEquals("36429173934033", userRegisterResponseDto.getMessage());

		}
		

}
