package com.revature.foodMartApi.services;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.foodMartApi.models.*;
import com.revature.foodMartApi.services.UserListService;
import com.revature.foodMartApi.daos.UserListDAO;
import com.revature.foodMartApi.exceptions.InvalidRequestException;

public class UserListServiceTestSuite {
	
	UserListService sut;
	UserListDAO mockUserListDAO;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		mockUserListDAO = mock(UserListDAO.class);
		sut = new UserListService(mockUserListDAO);
	}
	
	@Test
	void test_adduserList_goodData() {
		
		UserList validUserList = new UserList(1, new User());
		when(mockUserListDAO.save(validUserList)).thenReturn(validUserList);//valid userlist
		
		boolean result = !(sut.addUserList(validUserList) == null);
		
		assertTrue(result);
		//assertNotNull(userList);
		verify(mockUserListDAO, times(1)).save(validUserList);
	}
	
	@Test
	void test_adduserList_badData() {
		
		InvalidRequestException thrown = assertThrows(InvalidRequestException.class, () -> {
			UserList invalidUserList = null;
			
			when(mockUserListDAO.save(invalidUserList)).thenReturn(null);
			
			boolean result = !(sut.addUserList(invalidUserList) == null);
			
			assertFalse(result);
			
			verify(mockUserListDAO, times(1)).save(invalidUserList);
		});

		assertEquals("Invalid UserList provided.", thrown.getMessage());
		
	}
	
	@Test
	void findUserListById_goodData() {
		LinkedList<UserList> userLists = new LinkedList<>();
		userLists.add(new UserList(1, new User()));
		userLists.add(new UserList(2, new User()));
		userLists.add(new UserList(3, new User()));
		
		//assetNotNull(user)
		
	}
	
	@Test
	void findUserListById_badData() {
		
	}
	
	@Test
	void findUserListByUserId_goodData() {
		
	}
	
	@Test
	void findUserListByUserId_badData() {
		
	}
	
	@Test
	void findAllUserLists_goodData() {
		
	}
	
	@Test
	void findAllUserLists_badData() {
		
	}
	
	@Test
	void deleteUserList_goodData() {
		
	}
	
	@Test
	void deleteUserList_badData() {
		
	}
	
	@Test
	void deleteUserListById_goodData() {
		
	}
	
	@Test
	void deleteUserListById_badData() {
		
	}
}
