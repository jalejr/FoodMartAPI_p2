package com.revature.foodMartApi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.revature.foodMartApi.daos.UserListDAO;
import com.revature.foodMartApi.exceptions.InvalidRequestException;
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
		
		if(!isValidUserList(userList))
			throw new InvalidRequestException("Invalid userList");
		
		userListDAO.save(userList);
		
		return userList;
	}
	
	private boolean isValidUserList(UserList userList) {
		// TODO Auto-generated method stub
		userListDAO.delete(userList);
		return false;
	}

	//to do
	@Transactional
	public List<UserList> findallUserLists (){
		
		List<UserList> userList = new ArrayList();
		return null;
		
	}
	
	@Transactional
	public boolean deleteUserList(UserList userList) {
		userListDAO.delete(userList);
		return false;
	}
	
	@Transactional
	public UserList findUserListByUserId () {
		return null;
	}
	
	@Transactional 
	public UserList findUserListById() {
		return null;
	}
	
	@Transactional
	public void deleteUserList(int id) {
		
	}
	
	
	
}
