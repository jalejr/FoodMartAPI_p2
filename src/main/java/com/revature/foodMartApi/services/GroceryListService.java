package com.revature.foodMartApi.services;

import com.revature.foodMartApi.daos.GroceryListDAO;
import com.revature.foodMartApi.exceptions.InvalidRequestException;
import com.revature.foodMartApi.models.GroceryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GroceryListService {

    private final GroceryListDAO groceryListDAO;

    @Autowired
    public GroceryListService(GroceryListDAO groceryListDAO) {
        this.groceryListDAO = groceryListDAO;
    }

    public GroceryList addGroceryList(GroceryList groceryList) {
        if(!isValidGroceryList(groceryList)) throw new InvalidRequestException("Invalid Grocery List provided...");
        groceryListDAO.save(groceryList);
        return groceryList;
    }

    public List<GroceryList> findAll() {
        return (List<GroceryList>) groceryListDAO.findAll();
    }

    public Optional<GroceryList> findById(Long id) {
        return groceryListDAO.findById(id);
    }

    public Optional<GroceryList> findByUserListId(int id) { return groceryListDAO.findByUserListId(id); }

    public boolean delete(GroceryList groceryList) {
        if(!isValidGroceryList(groceryList)) throw new InvalidRequestException("Invalid Grocery List provided...");
        Long id = groceryList.getGroceryListId();
        if(!isExistingGroceryList(id)) throw new InvalidRequestException("GroceryList does not exist in db...");
        groceryListDAO.delete(groceryList);
        return !groceryListDAO.existsById(id);
    }

    public boolean deleteById(Long id) {
        if(!isExistingGroceryList(id)) throw new InvalidRequestException("GroceryList does not exist in db...");
        groceryListDAO.deleteById(id);
        return !groceryListDAO.existsById(id);
    }

    private boolean isValidGroceryList(GroceryList groceryList) {
        if(groceryList == null) return false;
        if(groceryList.getGroceryListId() == null) return false;
        if(groceryList.getItemCount() <= 0) return false;

        return true;
    }

    private boolean isExistingGroceryList(Long id) {
        return groceryListDAO.existsById(id);
    }
}
