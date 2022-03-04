package com.revature.foodMartApi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.foodMartApi.models.GroceryItem;
import com.revature.foodMartApi.daos.GroceryItemDAO;
import com.revature.foodMartApi.exceptions.InvalidRequestException;

@Service
public class GroceryItemService {
	private final GroceryItemDAO groceryItemDAO;

	@Autowired
	public GroceryItemService(GroceryItemDAO groceryItemDAO) {
		this.groceryItemDAO = groceryItemDAO;
	}

	@Transactional
	public GroceryItem newItem(GroceryItem item) {
		if (!isValidItem(item))  throw new InvalidRequestException("Invalid item provided.");
			
		groceryItemDAO.save(item);
		return item;

	}

	public List<GroceryItem> findAllItems() {
		return (List<GroceryItem>) groceryItemDAO.findAll();
	}

	public Optional<GroceryItem> findItemById(int id) {
		return groceryItemDAO.findById(id);
	}
	
//	public String findByName(String itemName) {
//		return  groceryItemDAO.findByName(itemName);
//	}

	public boolean deleteGroceryItem(GroceryItem groceryItem) {
		int id = groceryItem.getItemId();
		groceryItemDAO.deleteById(id);
		return !groceryItemDAO.existsById(id);
	}
	
	public boolean deleteGroceryItemById(int id) {
		groceryItemDAO.deleteById(id);
		return !groceryItemDAO.existsById(id);
	}
	public boolean isValidItem(GroceryItem item) {
		// TODO add more validation constraints
		if (item == null)
			return false;
		if(item.getItemId() < 0)
			return false;
		if(item.getItemName() == null)
			return false;
		if(item.getItemPrice() <= 0)
			return false;

		return true;	
	}

}
