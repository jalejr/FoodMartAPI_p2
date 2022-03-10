package com.revature.foodMartApi.services;

import com.revature.foodMartApi.daos.GroceryListDAO;
import com.revature.foodMartApi.exceptions.InvalidRequestException;
import com.revature.foodMartApi.models.GroceryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * GroceryListService which handles communication with the DAO
 */
@Service
@Transactional
public class GroceryListService {

    private final GroceryListDAO groceryListDAO;

    /**
     * @param groceryListDAO
     * Constructor
     */
    @Autowired
    public GroceryListService(GroceryListDAO groceryListDAO) {
        this.groceryListDAO = groceryListDAO;
    }

    /**
     * @param groceryList
     * @return
     * Adds a GroceryList to the DB and returns the List back
     */
    public GroceryList addGroceryList(GroceryList groceryList) {
        if(!isValidGroceryList(groceryList)) throw new InvalidRequestException("Invalid Grocery List provided...");
        groceryListDAO.save(groceryList);
        return groceryList;
    }

    /**
     * @return
     * Returns all GroceryList in the DB
     */
    public List<GroceryList> findAll() {
        return (List<GroceryList>) groceryListDAO.findAll();
    }

    /**
     * @param id
     * @return
     * Use an Id to return a specific GroceryLIst in DB
     */
    public Optional<GroceryList> findById(Long id) {
        return groceryListDAO.findById(id);
    }

    /**
     * @param id
     * @return
     * Use a UserListId to grab a list of GroceryLists
     */
    public List<GroceryList> findByUserListId(int id) { return groceryListDAO.findByUserListId(id); }

    /**
     * @param groceryList
     * @return
     * Deletes a groceryList by passing in a GroceryList and returns a boolean saying if it did delete.
     */
    public boolean delete(GroceryList groceryList) {
        if(!isValidGroceryList(groceryList)) throw new InvalidRequestException("Invalid Grocery List provided...");
        Long id = groceryList.getGroceryListId();
        if(!isExistingGroceryList(id)) throw new InvalidRequestException("GroceryList does not exist in db...");
        groceryListDAO.delete(groceryList);
        return !groceryListDAO.existsById(id);
    }

    /**
     * @param id
     * @return
     * Deletes a groceryList by passing in the id of a groceryList and returns a boolean saying if it did delete
     */
    public boolean deleteById(Long id) {
        if(!isExistingGroceryList(id)) throw new InvalidRequestException("GroceryList does not exist in db...");
        groceryListDAO.deleteById(id);
        return !groceryListDAO.existsById(id);
    }

    /**
     * @param groceryList
     * @return
     * Returns if the inserted GroceryList is valid
     */
    private boolean isValidGroceryList(GroceryList groceryList) {
        //TODO add the rest of them except id. Id doesn't get set before saving
        if(groceryList == null) return false;
        if(groceryList.getItemCount() <= 0) return false;

        return true;
    }

    /**
     * @param id
     * @return
     * Returns if the GroceryList exists in the db
     */
    private boolean isExistingGroceryList(Long id) {
        return groceryListDAO.existsById(id);
    }
}
