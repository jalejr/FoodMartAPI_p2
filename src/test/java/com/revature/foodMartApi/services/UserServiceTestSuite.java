package com.revature.foodMartApi.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.revature.foodMartApi.exceptions.InvalidRequestException;
import com.revature.foodMartApi.models.*;
import com.revature.foodMartApi.daos.UserDAO;
import com.revature.foodMartApi.services.UserService;

class UserServiceTestSuite {
	UserService sut;
	UserDAO mockUserDAO;
	
	@BeforeEach
	void setUp() throws Exception {
		mockUserDAO = mock(UserDAO.class);
		sut = new UserService(mockUserDAO);
	}

	
	@Test
	void test_addUser_returnsTrue_givenValidUser() {
		User validUser = new User();

		when(mockUserDAO.save(validUser)).thenReturn(validUser);

		boolean actualResult = !(sut.addUser(validUser) == null);

		Assertions.assertTrue(actualResult);
		verify(mockUserDAO, times(1)).save(validUser);
	}
	
	@Test
	void test_addUser_throwsInvalidRequestException_givenInvalidUser() {
		
		InvalidRequestException thrown = Assertions.assertThrows(InvalidRequestException.class, () -> {
	           //Code under test
			when(mockUserDAO.save(null)).thenReturn(null);
			
			boolean actualResult = !(sut.addUser(null) == null);
			Assertions.assertFalse(actualResult);
			verify(mockUserDAO, times(1)).save(null);
	  });
		//Assertions.assertEquals("some message", thrown.getMessage());
		//assertThat(response.getBody()).isEqualTo("Greetings from Spring Boot!");

		
	}

}
