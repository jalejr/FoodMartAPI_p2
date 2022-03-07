package com.revature.foodMartApi.services;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.foodMartApi.models.*;
import com.revature.foodMartApi.services.UserListService;
import com.revature.foodMartApi.daos.UserListDAO;

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
		
	}
	
	@Test
	void test_adduserList_badData() {
		
	}
	
	@Test
	void findUserListById_goodData() {
		
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
