package com.revature.foodMartApi.services;

import com.revature.foodMartApi.daos.GroceryListDAO;
import com.revature.foodMartApi.exceptions.InvalidRequestException;
import com.revature.foodMartApi.models.GroceryItem;
import com.revature.foodMartApi.models.GroceryList;
import com.revature.foodMartApi.models.User;
import com.revature.foodMartApi.models.UserList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.endpoint.web.Link;

import java.util.LinkedList;
import java.util.List;
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
        GroceryList validGroceryList = new GroceryList(1L, new UserList(), new GroceryItem(), 1);

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
    void findAllGroceryLists_givenSomeData() {
        LinkedList<GroceryList> groceryLists = new LinkedList<>();
        groceryLists.add(new GroceryList());
        groceryLists.add(new GroceryList());
        groceryLists.add(new GroceryList());
        groceryLists.add(new GroceryList());
        groceryLists.add(new GroceryList());
        groceryLists.add(new GroceryList());

        when(mockGroceryListDAO.findAll()).thenReturn(groceryLists);
        groceryLists = (LinkedList<GroceryList>) sut.findAll();

        boolean actualResult = groceryLists.size() == 6;

        assertTrue(actualResult);
        verify(mockGroceryListDAO, times(1)).findAll();
    }

    @Test
    void findAllGroceryLists_givenNoData() {
        LinkedList<GroceryList> groceryLists = new LinkedList<>();

        when(mockGroceryListDAO.findAll()).thenReturn(groceryLists);
        groceryLists = (LinkedList<GroceryList>) sut.findAll();

        boolean actualResult = groceryLists.size() == 0;

        assertTrue(actualResult);
        verify(mockGroceryListDAO, times(1)).findAll();
    }

    @Test
    void findGroceryListById_givenGoodData() {
        LinkedList<GroceryList> groceryLists = new LinkedList<>();
        groceryLists.add(new GroceryList(1L, new UserList(), new GroceryItem(), 2));
        groceryLists.add(new GroceryList(2L, new UserList(), new GroceryItem(),  1));
        groceryLists.add(new GroceryList(3L, new UserList(), new GroceryItem(),  4));
        groceryLists.add(new GroceryList(4L, new UserList(), new GroceryItem(),  5));
        groceryLists.add(new GroceryList(5L, new UserList(), new GroceryItem(),  2));
        groceryLists.add(new GroceryList(6L, new UserList(), new GroceryItem(),  7));

        when(mockGroceryListDAO.findById(3L)).thenReturn(Optional.ofNullable(groceryLists.get(2)));
        Optional<GroceryList> foundGroceryList = sut.findById(3L);

        assertNotNull(foundGroceryList);
        verify(mockGroceryListDAO, times(1)).findById(3L);
    }

    @Test
    void findGroceryListById_givenBadData() {
        LinkedList<GroceryList> groceryLists = new LinkedList<>();
        groceryLists.add(new GroceryList(1L, new UserList(), new GroceryItem(), 2));
        groceryLists.add(new GroceryList(2L, new UserList(), new GroceryItem(),  1));
        groceryLists.add(new GroceryList(3L, new UserList(), new GroceryItem(),  4));
        groceryLists.add(new GroceryList(4L, new UserList(), new GroceryItem(),  5));
        groceryLists.add(new GroceryList(5L, new UserList(), new GroceryItem(),  2));
        groceryLists.add(new GroceryList(6L, new UserList(), new GroceryItem(),  7));

        when(mockGroceryListDAO.findById(8L)).thenReturn(null);
        Optional<GroceryList> foundGroceryList = sut.findById(8L);

        assertNull(foundGroceryList);
        verify(mockGroceryListDAO, times(1)).findById(8L);
    }

    @Test
    void findGroceryListByUserListId_givenGoodData() {
        LinkedList<GroceryList> groceryLists = new LinkedList<>();
        groceryLists.add(new GroceryList(1L, new UserList(1, new User()), new GroceryItem(), 2));
        groceryLists.add(new GroceryList(2L, new UserList(1, new User()), new GroceryItem(),  1));
        groceryLists.add(new GroceryList(3L, new UserList(1, new User()), new GroceryItem(),  4));
        groceryLists.add(new GroceryList(4L, new UserList(3, new User()), new GroceryItem(),  5));
        groceryLists.add(new GroceryList(5L, new UserList(1, new User()), new GroceryItem(),  2));
        groceryLists.add(new GroceryList(6L, new UserList(1, new User()), new GroceryItem(),  7));

        when(mockGroceryListDAO.findByUserListId(3)).thenReturn((List<GroceryList>) groceryLists.get(3));
        List<GroceryList> foundGroceryList = sut.findByUserListId(3);

        assertNotNull(foundGroceryList);
        verify(mockGroceryListDAO, times(1)).findByUserListId(3);
    }

    @Test
    void findGroceryListByUserListId_givenBadData() {
        LinkedList<GroceryList> groceryLists = new LinkedList<>();
        groceryLists.add(new GroceryList(1L, new UserList(1, new User()), new GroceryItem(), 2));
        groceryLists.add(new GroceryList(2L, new UserList(1, new User()), new GroceryItem(),  1));
        groceryLists.add(new GroceryList(3L, new UserList(1, new User()), new GroceryItem(),  4));
        groceryLists.add(new GroceryList(4L, new UserList(3, new User()), new GroceryItem(),  5));
        groceryLists.add(new GroceryList(5L, new UserList(1, new User()), new GroceryItem(),  2));
        groceryLists.add(new GroceryList(6L, new UserList(1, new User()), new GroceryItem(),  7));

        when(mockGroceryListDAO.findByUserListId(8)).thenReturn(null);
        List<GroceryList> foundGroceryList = sut.findByUserListId(8);

        assertNull(foundGroceryList);
        verify(mockGroceryListDAO, times(1)).findByUserListId(8);
    }

    @Test
    void deleteGroceryList_givenGoodData() {
        GroceryList groceryList = new GroceryList(1L, new UserList(1, new User()), new GroceryItem(), 2);
        when(mockGroceryListDAO.existsById(groceryList.getGroceryListId())).thenReturn(true, false);

        sut.delete(groceryList);

        verify(mockGroceryListDAO, times(1)).delete(groceryList);
        verify(mockGroceryListDAO, times(2)).existsById(groceryList.getGroceryListId());
    }

    @Test
    void deleteGroceryList_givenBadData() {
        InvalidRequestException thrown = assertThrows(InvalidRequestException.class, () -> {
            GroceryList groceryList = new GroceryList(1L, new UserList(1, new User()), new GroceryItem(), 2);
            when(mockGroceryListDAO.existsById(groceryList.getGroceryListId())).thenReturn(false, false);

            sut.delete(new GroceryList(3L, new UserList(3, new User()), new GroceryItem(), 2));

            verify(mockGroceryListDAO, times(1)).delete(new GroceryList(3L, new UserList(1, new User()), new GroceryItem(), 2));
            verify(mockGroceryListDAO, times(1)).existsById(groceryList.getGroceryListId());
        });

        assertEquals("GroceryList does not exist in db...", thrown.getMessage());

    }

    @Test
    void deleteGroceryListById_givenGoodData() {
        GroceryList groceryList = new GroceryList(1L, new UserList(1, new User()), new GroceryItem(), 2);
        when(mockGroceryListDAO.existsById(1L)).thenReturn(true, false);

        sut.deleteById(1L);

        verify(mockGroceryListDAO, times(1)).deleteById(1L);
        verify(mockGroceryListDAO, times(2)).existsById(groceryList.getGroceryListId());
    }

    @Test
    void deleteGroceryListById_givenBadData() {
        InvalidRequestException thrown = assertThrows(InvalidRequestException.class, () -> {
            GroceryList groceryList = new GroceryList(1L, new UserList(1, new User()), new GroceryItem(), 2);
            when(mockGroceryListDAO.existsById(groceryList.getGroceryListId())).thenReturn(false, false);

            sut.delete(new GroceryList(3L, new UserList(3, new User()), new GroceryItem(), 2));

            verify(mockGroceryListDAO, times(1)).delete(new GroceryList(3L, new UserList(1, new User()), new GroceryItem(), 2));
            verify(mockGroceryListDAO, times(1)).existsById(groceryList.getGroceryListId());
        });

        assertEquals("GroceryList does not exist in db...", thrown.getMessage());
    }
}