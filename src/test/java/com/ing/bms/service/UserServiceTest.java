package com.ing.bms.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

	UserRegisterResponseDTO userRegisterResponseDto;

	UserRegisterRequestDTO userRegisterRequestDto;

	UserLoginResponseDTO userLoginResponseDto;

	UserLoginRequestDTO userLoginRequestDto;
	User user = new User();
	User user2 = new User();
	List<User> users = new ArrayList<>();

	@Before
	public void setup() {

		user.setUserName("Pranaya");
		user.setPassword("gu32");
		user.setUserId(1L);
		user.setPhoneNumber(785659759L);
		user.setEmailId("k@gmail.com");

		user2.setEmailId("k@gmail.com");
		user2.setPassword("gktu");
		user2.setPhoneNumber(5786696L);
		user2.setUserId(2L);
		user2.setUserName("priya");
	}

	@Test
	public void testRegister()throws NoSuchAlgorithmException {
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		UserRegisterResponseDTO userRegisterResponseDto = userServiceImpl.register(userRegisterRequestDto);
		Assert.assertEquals("36429173934033", userRegisterResponseDto.getMessage());

	}

	@Before
	public void setup1() {
		userLoginRequestDto = new UserLoginRequestDTO();
		userLoginRequestDto.setEmailId("sukumar@hcl.com");
		userLoginRequestDto.setPassword("1");

	}

	@Test
	public void testLogin() {
		Optional<User> optUser = Optional.of(user);
		Mockito.when(userRepository.findByEmailIdAndPassword(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(optUser);
		UserLoginResponseDTO userLoginResponseDto = userServiceImpl.login(userLoginRequestDto);
		Assert.assertEquals(Long.valueOf(1L), userLoginResponseDto.getUserId());

	}

}
