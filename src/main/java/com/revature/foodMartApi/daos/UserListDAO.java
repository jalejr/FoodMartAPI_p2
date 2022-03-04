package com.revature.foodMartApi.daos;


import com.revature.foodMartApi.models.User;
import com.revature.foodMartApi.models.UserList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserListDAO extends CrudRepository<UserList, Integer> {

	
	
}
