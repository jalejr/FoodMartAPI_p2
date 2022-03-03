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
import com.revature.foodMartApi.daos.GroceryItemDAO;
import com.revature.foodMartApi.services.GroceryItemService;

class ItemServiceTest {
	GroceryItemService sut;
	GroceryItemDAO mockGroceryItemDAO;
	
	@BeforeEach
	void setUp() throws Exception {
		mockGroceryItemDAO = mock(GroceryItemDAO.class);
		sut = new GroceryItemService(mockGroceryItemDAO);
	}
	

	@Test
	void test_newItem_returnsTrue_givenValidItem() {
		GroceryItem validItem = new GroceryItem(1,"cheese","8 oz shredded cheddar", 2.99, 5);
		
		when(mockGroceryItemDAO.save(validItem)).thenReturn(validItem);
		
		boolean actualResult = !(sut.newItem(validItem) == null);
		
		Assertions.assertTrue(actualResult);
		verify(mockGroceryItemDAO, times(1)).save(validItem);
	}

	@Test
	void test_newItem_throwsInvalidRequestException_givenInvalidItem() {
		InvalidRequestException thrown = Assertions.assertThrows(InvalidRequestException.class, () -> {
			GroceryItem validItem = null;
			when(mockGroceryItemDAO.save(validItem)).thenReturn(null);
			
			boolean actualResult = !(sut.newItem(validItem) == null);
			
			Assertions.assertFalse(actualResult);
			verify(mockGroceryItemDAO, times(1)).save(validItem);
			});
		Assertions.assertEquals("Invalid item provided.", thrown.getMessage());
	}
}
