package com.revature.foodMartApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.InvalidEndpointRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.foodMartApi.models.GroceryItem;
import com.revature.foodMartApi.daos.ItemDAO;

@Service
public class ItemService {
	private final ItemDAO itemDAO;

	@Autowired
	public ItemService(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	@Transactional
	public GroceryItem newItem(GroceryItem item) {
		if (!isValidItem(item)) {
// TODO!			throw new InvalidRequestException("Invalid item provided.");
		}
		itemDAO.save(item);
		return item;

	}

	public List<GroceryItem> findAllItems() {
		return null;
	}

	public GroceryItem findItemById() {
		return null;
	}
	
	public GroceryItem findByInventoryCount() {
		return null;
	}

	public boolean isValidItem(GroceryItem item) {
		// TODO add more validation constraints
		if (item == null) {
			return false;
		} else {
			return true;
		}
	}

}
