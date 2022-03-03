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

    public List<GroceryList> findAllGroceryLists() {
        return (List<GroceryList>) groceryListDAO.findAll();
    }

    public Optional<GroceryList> findGroceryById(Long id) {
        return groceryListDAO.findById(id);
    }

    //TODO implement this search
    public Optional<GroceryList> findGroceryListByUserListId(Long id) {
        return null;
    }

    public boolean deleteGroceryList(GroceryList groceryList) {
        Long id = groceryList.getGroceryListId();
        groceryListDAO.deleteById(id);
        return !groceryListDAO.existsById(id);
    }

    public boolean deleteGroceryListById(Long id) {
        groceryListDAO.deleteById(id);
        return !groceryListDAO.existsById(id);
    }

    private boolean isValidGroceryList(GroceryList groceryList) {
        if(groceryList == null) return false;
        if(groceryList.getGroceryListId() == null) return false;
        if(groceryList.getItemCount() <= 0) return false;

        return true;
    }
}
