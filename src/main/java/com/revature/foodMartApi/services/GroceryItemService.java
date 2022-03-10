package com.revature.foodMartApi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.foodMartApi.models.GroceryItem;
import com.revature.foodMartApi.daos.GroceryItemDAO;
import com.revature.foodMartApi.exceptions.InvalidRequestException;

/**
 * GroceryItemService
 */
@Service
public class GroceryItemService {
	private final GroceryItemDAO groceryItemDAO;

	/**
	 * @param groceryItemDAO
	 * constructor which requires DAO
	 */
	@Autowired
	public GroceryItemService(GroceryItemDAO groceryItemDAO) {
		this.groceryItemDAO = groceryItemDAO;
	}

	/**
	 * @param item
	 * @return
	 * Add a GroceryItem to the db. Has some checks to make sure data is valid. Also returns the grocery item put in
	 */
	@Transactional
	public GroceryItem addGroceryItem(GroceryItem item) {
		if (!isValidItem(item))  throw new InvalidRequestException("Invalid item provided.");
		
		groceryItemDAO.save(item);
		return item;

	}

	/**
	 * @return
	 * Gives back a List of all GroceryItems in the DB
	 */
	public List<GroceryItem> findAllItems() {
		return (List<GroceryItem>) groceryItemDAO.findAll();
	}

	/**
	 * @param id
	 * @return
	 * Gives an GroceryItem back that's identified by the id
	 */
	public Optional<GroceryItem> findItemById(int id) {
		return groceryItemDAO.findById(id);
	}

	/**
	 * @param item
	 * @return
	 * A bool is returned depending if the GroceryItem is valid
	 */
	public boolean isValidItem(GroceryItem item) {
		// TODO add more validation constraints
		if (item == null) {
			return false;
		}
		if (item.getItemId() < 0)
			return false;
		if (item.getItemName() == null)
			return false;
		if (item.getItemPrice() <= 0)
			return false;

		return true;
	}

	/**
	 * @param validItem
	 * @return
	 * Deletes a GroceryItem and returns if the delete was successful
	 */
	public boolean deleteGroceryItem(GroceryItem validItem) {
		int id = validItem.getItemId();
		groceryItemDAO.delete(validItem);
		return !groceryItemDAO.existsById(id);
	}
}
