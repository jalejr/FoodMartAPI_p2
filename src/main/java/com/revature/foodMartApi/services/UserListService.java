package com.revature.foodMartApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.revature.foodMartApi.daos.UserListDAO;
import com.revature.foodMartApi.models.UserList;

public class UserListService {

	
	private final UserListDAO userListDAO;
	
	@Autowired
	public UserListService(UserListDAO userListDAO) {
		
		this.userListDAO = userListDAO;
	}
	
	//logic to add a new user list
	@Transactional
	public UserList addUserList(UserList userList) {
		
		userListDAO.save(userList);
		
		return userList;
	}
	
	//to do
	@Transactional
	public List<UserList> findallUserLists (){
		return null;
		
	}
	
}
