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
	public void testPrep() {
		
		mockUserListDAO = mock(UserListDAO.class);
		sut = new UserListService(mockUserListDAO);
	}
	
	@Test
	void test_adduserList_givenGoodData() {
		
	}
	
	@Test
	void test_adduserList_givenBadData() {
		
	}
}
