package com.revature.foodMartApi.daos;

import com.revature.foodMartApi.models.GroceryList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroceryListDAO extends CrudRepository<GroceryList, Long> {
    @Query("FROM GroceryList i WHERE i.listId =: listId")
    Optional<GroceryList> findByUserListId(int listId);
}
