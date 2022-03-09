package com.revature.foodMartApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.foodMartApi.models.GroceryItem;
import com.revature.foodMartApi.daos.GroceryItemDAO;

@Service
public class GroceryItemService {
	private final GroceryItemDAO groceryItemDAO;

	@Autowired
	public GroceryItemService(GroceryItemDAO groceryItemDAO) {
		this.groceryItemDAO = groceryItemDAO;
	}

	@Transactional
	public GroceryItem addGroceryItem(GroceryItem item) {
		if (!isValidItem(item)) {
		}
		groceryItemDAO.save(item);
		return item;

	}

	public List<GroceryItem> findAllItems() {
		return null;
	}

	public GroceryItem findItemById(int id) {
		return null;
	}
	
//	public String findByName(String itemName) {
//		return  groceryItemDAO.findByName(itemName);
//	}

	public boolean isValidItem(GroceryItem item) {
		// TODO add more validation constraints
		if (item == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean deleteGroceryItem(GroceryItem validItem) {
		int id = validItem.getItemId();
		groceryItemDAO.delete(validItem);
		return !groceryItemDAO.existsById(id);
	}
}
