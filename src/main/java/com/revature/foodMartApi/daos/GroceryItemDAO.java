package com.revature.foodMartApi.daos;

import org.springframework.data.repository.CrudRepository;

import com.revature.foodMartApi.models.GroceryItem;
import org.springframework.stereotype.Repository;


@Repository
public interface GroceryItemDAO extends CrudRepository<GroceryItem, Integer>{

	
//	public String findByName(String itemName);
	//TODO Just leaving this for you to see. No idea why this is causing the contextLoads to fail
	//commented it out for now
	//GroceryItem findItemById(int itemId);
	
}

