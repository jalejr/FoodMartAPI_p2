package com.revature.foodMartApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.foodMartApi.daos.UserListDAO;
import com.revature.foodMartApi.models.User;
import com.revature.foodMartApi.models.UserList;

public class UserListService {

	private final UserListDAO userListDAO;
	
	@Autowired
	public UserListService(UserListDAO userListDAO) {
		this.userListDAO = userListDAO;
	}
	//implement
	public UserList addUserList(UserList userList) {
		
		return userList;
	}
	
	//using SPring CRUD to findall
	public List<UserList> findAll(){
		return (List<UserList>) userListDAO.findAll();
	}
	
	//implement
	public UserList findUserListById(int id) {
		return null;
	}
	//implement
	public UserList findUserListByUserId(int userId) {
		return null;
	}
	//implement
	public boolean deleteUserListById(User deleteUser) {
		return false;
	}
	

}
