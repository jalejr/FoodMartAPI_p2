package com.revature.foodMartApi.daos;

import com.revature.foodMartApi.models.GroceryList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryListDAO extends CrudRepository<GroceryList, Long> {

}
