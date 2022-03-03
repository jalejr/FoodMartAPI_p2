package com.revature.foodMartApi.daos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.revature.foodMartApi.models.UserList;

public interface UserListDAO extends CrudRepository<UserList, String>{
	Optional<UserList> findUserListById(String userListId);
	
	Optional<UserList> findUserListByUserId(String userId);
	
	

}
