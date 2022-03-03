package com.revature.foodMartApi.services;

import com.revature.foodMartApi.daos.GroceryListDAO;
import com.revature.foodMartApi.exceptions.InvalidRequestException;
import com.revature.foodMartApi.models.GroceryList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.endpoint.web.Link;

import java.util.LinkedList;
import java.util.Optional;

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
    void findAllGroceryLists_givenData() {
        LinkedList<GroceryList> groceryLists = new LinkedList<>();
        groceryLists.add(new GroceryList());
        groceryLists.add(new GroceryList());
        groceryLists.add(new GroceryList());
        groceryLists.add(new GroceryList());
        groceryLists.add(new GroceryList());
        groceryLists.add(new GroceryList());

        when(mockGroceryListDAO.findAll()).thenReturn(groceryLists);
        groceryLists = (LinkedList<GroceryList>) sut.findAllGroceryLists();

        boolean actualResult = groceryLists.size() == 6;

        assertTrue(actualResult);
        verify(mockGroceryListDAO, times(1)).findAll();
    }

    @Test
    void findGroceryById_givenGoodData() {
        LinkedList<GroceryList> groceryLists = new LinkedList<>();
        groceryLists.add(new GroceryList(1L, 2));
        groceryLists.add(new GroceryList(2L, 1));
        groceryLists.add(new GroceryList(3L, 4));
        groceryLists.add(new GroceryList(4L, 5));
        groceryLists.add(new GroceryList(5L, 2));
        groceryLists.add(new GroceryList(6L, 7));

        when(mockGroceryListDAO.findById(3L)).thenReturn(Optional.ofNullable(groceryLists.get(2)));
        Optional<GroceryList> foundGroceryList = sut.findGroceryById(3L);

        assertNotNull(foundGroceryList);
        verify(mockGroceryListDAO, times(1)).findById(3L);
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