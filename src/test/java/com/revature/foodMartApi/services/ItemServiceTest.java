package com.revature.foodMartApi.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

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
		GroceryItem validItem = new GroceryItem(1, "cheese", "8 oz shredded cheddar", 2.99, 5);

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

	@Test
	void test_findAllItems_returnsTrue_givenList() {
		LinkedList<GroceryItem> groceryItemList = new LinkedList<>();
		groceryItemList.add(new GroceryItem(3, "cheese1", "8 oz shredded cheddars", 3.99, 1));
		groceryItemList.add(new GroceryItem(2, "lettuce", "8 oz shredded lettuce", 2.49, 3));

		when(mockGroceryItemDAO.findAll()).thenReturn(groceryItemList);
		groceryItemList = (LinkedList<GroceryItem>) sut.findAllItems();

		boolean actualResult = groceryItemList.size() == 2;

		assertTrue(actualResult);
		verify(mockGroceryItemDAO, times(1)).findAll();
	}

//	@Test
//	void test_findGroceryItemByName_givenValidName() {
//
//		GroceryItem validItem = new GroceryItem(1, "cheese", "8 oz shredded cheddar", 2.99, 5);
//
//		when(mockGroceryItemDAO.save(validItem)).thenReturn(validItem);
//		
//		String itemName = validItem.getItemName();
//		boolean actualResult = !(sut.findByName(itemName) == "cheese");
//
//		Assertions.assertTrue(actualResult);
//		verify(mockGroceryItemDAO, times(1)).findByName(itemName);
//	}
//	
//	@Test
//	void test_findGroceryItemByName_givenBadName() {
//		GroceryItem validItem = new GroceryItem(1, "cheese", "8 oz shredded cheddar", 2.99, 5);
//
//		when(mockGroceryItemDAO.save(validItem)).thenReturn(validItem);
//		
//		String itemName = validItem.getItemName();
//		boolean actualResult = (sut.findByName(itemName) == "taco shell");
//
//		Assertions.assertFalse(actualResult);
//		verify(mockGroceryItemDAO, times(1)).findByName(itemName);
//	}

	@Test
	void test_deleteGroceryItem_returnsTrue_givenValidItem() {
		GroceryItem validItem = new GroceryItem(1, "cheese", "8 oz shredded cheddar", 2.99, 5);

		when(mockGroceryItemDAO.save(validItem)).thenReturn(validItem);
		boolean actualResult = !(sut.deleteGroceryItem(validItem) == false);

		Assertions.assertTrue(actualResult);
		verify(mockGroceryItemDAO, times(1)).deleteById(validItem.getItemId());
	}

	@Test
	void test_deleteGroceryItem_returnsFalse_givenInvalidItem() {
		InvalidRequestException thrown = Assertions.assertThrows(InvalidRequestException.class, () -> {
			GroceryItem invalidItem = null;
			when(mockGroceryItemDAO.save(invalidItem)).thenReturn(null);

			boolean actualResult = !(sut.isValidItem(invalidItem) == false);

			Assertions.assertFalse(actualResult);
			verify(mockGroceryItemDAO, times(1)).save(invalidItem);
		});
		Assertions.assertEquals("Invalid item provided.", thrown.getMessage());
	}
}
