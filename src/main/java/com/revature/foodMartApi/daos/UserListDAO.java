package com.revature.foodMartApi.daos;



import com.revature.foodMartApi.models.UserList;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * UserListDAO impl through CrudRepository. Has custom query for findByUserId which is never used
 */
@Repository
public interface UserListDAO extends CrudRepository<UserList, Integer> {

	@Query("FROM UserList i WHERE i.id =: userId")
	Optional<UserList> findByUserId(int userId);
	
}
