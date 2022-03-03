package com.revature.foodMartApi.services;

import com.revature.foodMartApi.daos.GroceryListDAO;
import com.revature.foodMartApi.exceptions.InvalidRequestException;
import com.revature.foodMartApi.models.GroceryList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GroceryListServiceTestSuite {

    GroceryListService sut;
    GroceryListDAO mockGroceryListDAO;

    @BeforeEach
    void setUp() throws Exception {
        mockGroceryListDAO = mock(GroceryListDAO.class);
        sut = new GroceryListService(mockGroceryListDAO);
    }

    @Test
    void test_addGroceryList_returnsTrue_givenValidData() {
        GroceryList validGroceryList = new GroceryList(1L, 1);

        when(mockGroceryListDAO.save(validGroceryList)).thenReturn(validGroceryList);

        boolean actualResult = !(sut.addGroceryList(validGroceryList) == null);

        assertTrue(actualResult);
        verify(mockGroceryListDAO, times(1)).save(validGroceryList);
    }

    @Test
    void test_addGroceryList_returnsFalse_givenBadData() {

        InvalidRequestException thrown = assertThrows(InvalidRequestException.class, () -> {
            GroceryList invalidGroceryList = null;
            when(mockGroceryListDAO.save(invalidGroceryList)).thenReturn(null);

            boolean actualResult = !(sut.addGroceryList(invalidGroceryList) == null);
            assertFalse(actualResult);
            verify(mockGroceryListDAO, times(1)).save(invalidGroceryList);
        });

        assertEquals("Invalid Grocery List provided...", thrown.getMessage());
    }

    @Test
    void findAllGroceryLists() {
    }

    @Test
    void findGroceryById() {
    }

    @Test
    void findGroceryListByUserListId() {
    }

    @Test
    void deleteGroceryList() {
    }

    @Test
    void deleteGroceryListById() {
    }
}