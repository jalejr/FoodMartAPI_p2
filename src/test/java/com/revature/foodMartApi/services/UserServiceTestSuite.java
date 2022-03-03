package com.revature.foodMartApi.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.foodMartApi.exceptions.InvalidRequestException;
import com.revature.foodMartApi.exceptions.ResourcePersistenceException;
import com.revature.foodMartApi.models.User;
import com.revature.foodMartApi.daos.UserDAO;

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
	void test_addUser_throwsInvalidRequestException_givenNullUser() {
		InvalidRequestException thrown = Assertions.assertThrows(InvalidRequestException.class, () -> {
			sut.addUser(null);
		});
		Assertions.assertEquals("Invalid user data provided.", thrown.getMessage());
	}

	@Test
	void test_addUser_throwsInvalidRequestException_givenInvalidUsername() {
		InvalidRequestException thrown = Assertions.assertThrows(InvalidRequestException.class, () -> {
			// TODO create user with invalid username
			sut.addUser(null);
		});
		Assertions.assertEquals("Invalid user data provided.", thrown.getMessage());
	}

	@Test
	void test_addUser_throwsInvalidRequestException_givenInvalidEmail() {
		InvalidRequestException thrown = Assertions.assertThrows(InvalidRequestException.class, () -> {
			// TODO create user with invalid email
			sut.addUser(null);
		});
		Assertions.assertEquals("Invalid user data provided.", thrown.getMessage());
	}

	@Test
	void test_addUser_throwsInvalidRequestException_givenInvalidPassword() {
		InvalidRequestException thrown = Assertions.assertThrows(InvalidRequestException.class, () -> {
			// TODO create user with invalid password
			sut.addUser(null);
		});
		Assertions.assertEquals("Invalid user data provided.", thrown.getMessage());
	}

	@Test
	void test_addUser_throwsResourcePersistenceException_givenDuplicateUser() {
		User validUser = new User();
		when(mockUserDAO.save(validUser)).thenReturn(null);
		ResourcePersistenceException thrown = Assertions.assertThrows(ResourcePersistenceException.class, () -> {
			// Code under test
			boolean actualResult = !(sut.addUser(validUser) == null);
			Assertions.assertFalse(actualResult);

		});
		Assertions.assertEquals("User was not persisted.", thrown.getMessage());
		verify(mockUserDAO, times(1)).save(validUser);
	}

	@Test
	void test_findAllUsers_returnsCollection() {
		List<User> users = new ArrayList<>();

		when(mockUserDAO.findAll()).thenReturn(users);

		boolean actualResult = !(sut.findAllUsers() == null);

		Assertions.assertTrue(actualResult);
		verify(mockUserDAO, times(1)).findAll();
	}

	// @Test
	// void test_findAllUsers_returnsNull() {
	// List<User> users = new ArrayList<>();

	// when(mockUserDAO.findAll()).thenReturn(users);

	// boolean actualResult = !(sut.findAllUsers() == null);

	// Assertions.assertTrue(actualResult);
	// verify(mockUserDAO, times(1)).findAll();
	// }
	@Test
	void test_findUserById_returnsUser_givenValidId() {

	}
	@Test
	void test_findUserById_returnsNull_givenInvalidId() {

	}

	@Test
	void test_findUserByUsername_returnsUser_givenValidUsername() {

	}
	@Test
	void test_findUserByUsername_returnsNull_givenInvalidUsername() {

	}

	@Test
	void test_findUserByEmail_returnsUser_givenValidEmail() {

	}

	@Test
	void test_findUserByEmail_returnsNull_givenInvalidEmail() {

	}

	@Test
	void test_authenticateUser_returnsUser_givenValidUser() {

	}

	@Test
	void test_authenticateUser_throwsAuthenticationException_givenInvalidUser() {

	}

	@Test
	void test_updateUser_returnsTrue_givenValidUser() {

	}
	//throw ResourcePersistenceException?
	@Test
	void test_updateUser_returnsFalse_givenInvalidUser() {

	}

	@Test
	void test_deleteUser_returnsTrue_givenValidUser() {

	}
	//throw ResourcePersistenceException?
	@Test
	void test_deleteUser_returnsFalse_givenInvalidUser() {

	}

	@Test
	void test_isValidUser_returnsTrue_givenValidUser() {

	}

	@Test
	void test_isValidUser_returnsFalse_givenInvalidUser() {

	}
}
