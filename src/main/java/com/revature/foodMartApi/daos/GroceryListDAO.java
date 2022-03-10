package com.revature.foodMartApi.daos;

import com.revature.foodMartApi.models.GroceryList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * GroceryListDAO which is implemented through CrudRepository. Also some custom impl for a findByUserListId
 */
@Repository
public interface GroceryListDAO extends CrudRepository<GroceryList, Long> {
    @Query("SELECT i FROM GroceryList i WHERE i.listId.id = ?1")
    List<GroceryList> findByUserListId(int inputId);
}
