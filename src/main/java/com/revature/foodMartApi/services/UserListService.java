package com.revature.foodMartApi.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.foodMartApi.daos.UserListDAO;
import com.revature.foodMartApi.exceptions.InvalidRequestException;

import com.revature.foodMartApi.models.UserList;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserListService {

	private final UserListDAO userListDAO;
	
	@Autowired
	public UserListService(UserListDAO userListDAO) {
		this.userListDAO = userListDAO;
	}
	
	//createuserList
	public UserList addUserList(UserList userList) {
		if(isValidUserList(userList))
			throw new InvalidRequestException("Invalid User List provided...");
		userListDAO.save(userList);
		return userList;
	}
	
	//checks if userlist is valid
	private boolean isValidUserList(UserList userList) {
		if(userList == null) {
			return false;
		} else if(userList.getUser() == null) {
			return false;
		} else if(userList.getId() <= 0) {
				return false;
		}
		
		return true;
	}
	
	//using SPring CRUD to findall
	public List<UserList> findAll(){
		return (List<UserList>) userListDAO.findAll();
	}
	
	//get userlistby userlist id
	public UserList findUserListById(int id) {
		
		try {
			return userListDAO.findById(id).get();
		} catch (NullPointerException | NoSuchElementException e) {
			return null;
		}
	}
	
	//find userlist by user ID
	public Optional<UserList> findUserListByUserId(int userId) {
		return userListDAO.findByUserId(userId);
	}
	
	//delete 
	public boolean deleteUser(UserList deleteUser) {
		
		//validating UserList
		if(isValidUserList(deleteUser) == false) {
			throw new InvalidRequestException("Invalid User List provided..");
		} 
		
		if(userListDAO.findById(deleteUser.getId()) == null){
			throw new InvalidRequestException("Invalid User List provided..");
		}
		
		//deleting userList
		userListDAO.delete(deleteUser);
		
		//
		if(userListDAO.findById(deleteUser.getId()) == null) {
			return true;
		} else {
			return false;
		}
		
	
		
		//checking if id is found then execute deletion from userList DAO
		
	}
	
	//delete by userlist id
	public boolean deletById(int id) {
		if(!isUserListExisting(id))
			throw new InvalidRequestException("UserList does not exists..");
		userListDAO.deleteById(id);
		
		return !userListDAO.existsById(id);
	
	}
	
	//checks to see if user list exists
	private boolean isUserListExisting(int id) {
		return userListDAO.existsById(id);
	}
	

}
