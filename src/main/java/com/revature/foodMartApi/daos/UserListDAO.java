package com.revature.foodMartApi.daos;



import com.revature.foodMartApi.models.UserList;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserListDAO extends CrudRepository<UserList, Integer> {

	@Query("FROM userlist i WHERE i.userId =: userId")
    Optional<UserList> findByUserListId(int listId);
}
