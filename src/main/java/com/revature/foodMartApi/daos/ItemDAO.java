package com.revature.foodMartApi.daos;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.foodMartApi.models.GroceryItem;

public interface ItemDAO extends CrudRepository<GroceryItem, Integer>{

	GroceryItem findItemById(int itemId);
	
}

