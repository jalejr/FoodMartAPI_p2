package com.revature.foodMartApi.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	// create
	@Test
	void test_addUser_returnsTrue_givenValidUser() {
		User validUser = new User("valid", "valid", "valid");
		when(mockUserDAO.findByEmail(validUser.getEmail())).thenReturn(null);
		when(mockUserDAO.findByUsername(validUser.getUsername())).thenReturn(null);
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
			User invalidUser = new User("", "valid", "valid");
			sut.addUser(invalidUser);
		});
		Assertions.assertEquals("Invalid user data provided.", thrown.getMessage());

		thrown = Assertions.assertThrows(InvalidRequestException.class, () -> {
			User invalidUser = new User(null, "valid", "valid");
			Assertions.assertNull(sut.addUser(invalidUser));
		});
		Assertions.assertEquals("Invalid user data provided.", thrown.getMessage());
	}

	@Test
	void test_addUser_throwsInvalidRequestException_givenInvalidEmail() {
		InvalidRequestException thrown = Assertions.assertThrows(InvalidRequestException.class, () -> {
			User invalidUser = new User("valid", "valid", "");
			sut.addUser(invalidUser);
		});
		Assertions.assertEquals("Invalid user data provided.", thrown.getMessage());

		thrown = Assertions.assertThrows(InvalidRequestException.class, () -> {
			User invalidUser = new User("valid", "valid", null);
			sut.addUser(invalidUser);
		});
		Assertions.assertEquals("Invalid user data provided.", thrown.getMessage());
	}

	@Test
	void test_addUser_throwsInvalidRequestException_givenInvalidPassword() {
		InvalidRequestException thrown = Assertions.assertThrows(InvalidRequestException.class, () -> {
			User invalidUser = new User("valid", "", "valid");
			sut.addUser(invalidUser);
		});
		Assertions.assertEquals("Invalid user data provided.", thrown.getMessage());

		thrown = Assertions.assertThrows(InvalidRequestException.class, () -> {
			User invalidUser = new User("valid", null, "valid");
			sut.addUser(invalidUser);
		});
		Assertions.assertEquals("Invalid user data provided.", thrown.getMessage());
	}

	@Test
	void test_addUser_throwsResourcePersistenceException_givenDuplicateUser() {
		User validUser = new User("valid", "valid", "valid");
		when(mockUserDAO.findByEmail(validUser.getEmail())).thenReturn(null);
		when(mockUserDAO.findByUsername(validUser.getUsername())).thenReturn(null);
		when(mockUserDAO.save(validUser)).thenReturn(null);
		ResourcePersistenceException thrown = Assertions.assertThrows(ResourcePersistenceException.class, () -> {
			Assertions.assertNull(sut.addUser(validUser));
		});
		Assertions.assertEquals("User was not persisted.", thrown.getMessage());
		verify(mockUserDAO, times(1)).save(validUser);
	}

	// read
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
		int validId = 1;
		User foundUser = new User(validId, "valid", "valid", "valid");
		when(mockUserDAO.findById(validId)).thenReturn(Optional.of(foundUser));

		Assertions.assertTrue(sut.findUserById(validId) == foundUser);
		verify(mockUserDAO, times(1)).findById(validId);
	}

	@Test
	void test_findUserById_returnsNull_givenInvalidId() {
		int invalidId = 2;
		User foundUser = new User(1, "valid", "valid", "valid");
		when(mockUserDAO.findById(invalidId)).thenReturn(null);

		Assertions.assertFalse(sut.findUserById(invalidId) == foundUser);
		verify(mockUserDAO, times(1)).findById(invalidId);
	}

	@Test
	void test_findUserByUsername_returnsUser_givenValidUsername() {
		String validUsername = "validUsername";
		User foundUser = new User(validUsername, "valid", "valid");
		when(mockUserDAO.findByUsername(validUsername)).thenReturn(foundUser);

		Assertions.assertTrue(sut.findUserByUsername(validUsername) == foundUser);
		verify(mockUserDAO, times(1)).findByUsername(validUsername);
	}

	@Test
	void test_findUserByUsername_returnsNull_givenInvalidUsername() {
		String invalidUsername = "invalid";
		when(mockUserDAO.findByUsername(invalidUsername)).thenReturn(null);

		Assertions.assertNull(sut.findUserByUsername(invalidUsername));
		verify(mockUserDAO, times(1)).findByUsername(invalidUsername);
	}

	@Test
	void test_findUserByEmail_returnsUser_givenValidEmail() {
		String validEmail = "validEmail";
		User foundUser = new User("valid", validEmail, "valid");
		when(mockUserDAO.findByUsername(validEmail)).thenReturn(foundUser);

		Assertions.assertTrue(sut.findUserByUsername(validEmail) == foundUser);
		verify(mockUserDAO, times(1)).findByUsername(validEmail);
	}

	@Test
	void test_findUserByEmail_returnsNull_givenInvalidEmail() {
		String invalidEmail = "invalid";
		when(mockUserDAO.findByEmail(invalidEmail)).thenReturn(null);

		Assertions.assertNull(sut.findUserByEmail(invalidEmail));
		verify(mockUserDAO, times(1)).findByEmail(invalidEmail);
	}

	// authenticate
	@Test
	void test_authenticateUser_returnsUser_givenValidUser() {

	}

	@Test
	void test_authenticateUser_throwsAuthenticationException_givenInvalidUser() {

	}

	// update
	@Test
	void test_updateUser_returnsTrue_givenValidUser() {
		User validUser = new User(1, "valid", "valid", "valid");

		when(mockUserDAO.findById(validUser.getId())).thenReturn(Optional.of(validUser));
		when(mockUserDAO.save(validUser)).thenReturn(validUser);

		boolean actualResult = sut.updateUser(validUser);

		Assertions.assertTrue(actualResult);
		verify(mockUserDAO, times(1)).findById(validUser.getId());
		verify(mockUserDAO, times(1)).save(validUser);
	}

	@Test
	void test_updateUser_throwsInvalidRequestException_givenInvalidUser() {
		User newUser = new User("", "valid", "valid");
		InvalidRequestException thrown = Assertions.assertThrows(InvalidRequestException.class, () -> {
			Assertions.assertNull(sut.updateUser(newUser));
		});
		Assertions.assertEquals("Invalid user data provided.", thrown.getMessage());
	}

	@Test
	void test_updateUser_throwsInvalidRequestException_givenUserNotInDatabase() {
		User newUser = new User(1, "valid", "valid", "valid");
		when(mockUserDAO.findById(newUser.getId())).thenReturn(null);
		when(mockUserDAO.save(newUser)).thenReturn(null);
		InvalidRequestException thrown = Assertions.assertThrows(InvalidRequestException.class, () -> {
			Assertions.assertNull(sut.updateUser(newUser));
		});
		Assertions.assertEquals("Invalid Request: Cannot update user not in database.", thrown.getMessage());
		verify(mockUserDAO, times(1)).findById(newUser.getId());
		verify(mockUserDAO, times(0)).save(newUser);
	}

	// delete
	@Test
	void test_deleteUser_succeeds_givenValidUser() {
		User deletedUser = new User(1, "valid", "valid", "valid");
		when(mockUserDAO.findById(deletedUser.getId())).thenReturn(Optional.of(deletedUser), null);
		// when(mockUserDAO.delete(deletedUser)).doNothing();
		// Assertions.assertNull(sut.updateUser(deletedUser));
		sut.deleteUser(deletedUser);
		verify(mockUserDAO, times(1)).delete(deletedUser);
		verify(mockUserDAO, times(2)).findById(deletedUser.getId());
	}

	// throw ResourcePersistenceException?
	@Test
	void test_deleteUser_throwsInvalidRequestException_givenInvalidUser() {
		User deletedUser = new User(1, "", "valid", "valid");
		when(mockUserDAO.findById(deletedUser.getId())).thenReturn(Optional.of(deletedUser));
		// when(mockUserDAO.delete(deletedUser)).thenReturn(null);
		InvalidRequestException thrown = Assertions.assertThrows(InvalidRequestException.class, () -> {
			sut.deleteUser(deletedUser);
		});
		Assertions.assertEquals("Invalid user data provided.", thrown.getMessage());
		verify(mockUserDAO, times(0)).delete(deletedUser);
		verify(mockUserDAO, times(0)).findById(deletedUser.getId());
	}

	@Test
	void test_deleteUser_throwsInvalidRequestException_givenUserNotInDatabase() {
		User deletedUser = new User(1, "valid", "valid", "valid");
		when(mockUserDAO.findById(deletedUser.getId())).thenReturn(null);
		// when(mockUserDAO.delete(newUser)).thenReturn(null);
		InvalidRequestException thrown = Assertions.assertThrows(InvalidRequestException.class, () -> {
			sut.deleteUser(deletedUser);
		});
		Assertions.assertEquals("Invalid Request: Cannot delete user not in database.", thrown.getMessage());
		verify(mockUserDAO, times(0)).delete(deletedUser);
		verify(mockUserDAO, times(1)).findById(deletedUser.getId());
	}

	@Test
	void test_deleteUser_throwsResourcePersistenceException_ifUserNotDeleted() {
		User deletedUser = new User(1, "valid", "valid", "valid");
		when(mockUserDAO.findById(deletedUser.getId())).thenReturn(Optional.of(deletedUser), Optional.of(deletedUser));
		// when(mockUserDAO.delete(newUser)).thenReturn(null);
		ResourcePersistenceException thrown = Assertions.assertThrows(ResourcePersistenceException.class, () -> {
			sut.deleteUser(deletedUser);
		});
		Assertions.assertEquals("User deletion was not persisted.", thrown.getMessage());
		verify(mockUserDAO, times(1)).delete(deletedUser);
		verify(mockUserDAO, times(2)).findById(deletedUser.getId());
	}


	// isValid -- already covered in add user
	// @Test
	// void test_isValidUser_returnsTrue_givenValidUser() {

	// }

	// @Test
	// void test_isValidUser_returnsFalse_givenInvalidUser() {

	// }
	// method only calls dao function, testing isn't relevant to service layer
	// @Test
	// void test_IsEmailAvailable_returnsTrue_givenNewEmail() {

	// }

	// @Test
	// void test_IsEmailAvailable_returnsFalse_givenExistingEmail() {

	// }

	// @Test
	// void test_IsUsernameAvailable_returnsTrue_givenNewUsername() {

	// }

	// @Test
	// void test_IsUsernameAvailable_returnsFalse_givenExistingUsername() {

	// }
}