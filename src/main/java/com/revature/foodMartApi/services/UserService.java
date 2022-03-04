package com.revature.foodMartApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.foodMartApi.daos.UserDAO;
import com.revature.foodMartApi.exceptions.InvalidRequestException;
import com.revature.foodMartApi.models.User;

@Service
@Transactional
public class UserService {
	private final UserDAO userDAO;
	
	@Autowired
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	
	public User addUser(User user) {
		//TODO 
		if (!isValidUser(user)) {
			throw new InvalidRequestException("Invalid user provided.");
		}
		userDAO.save(user);
		return user;
	}
	
	public List<User> findAllUsers (){
		//TODO
		return null;
	}
	
	public User findUserById (){
		//TODO
		return null;
	}
	
	public User findUserByUsername (){
		//TODO
		return null;
	}
	
	public User findUserByEmail (){
		//TODO
		return null;
	}
	
	public User authenticateUser () {
		//TODO		
		return null;
	}
	
	public boolean updateUser () {
		//TODO		
		return false;
	}
	
	public boolean deleteUser () {
		//TODO		
		return false;
	}
	
	private boolean isValidUser(User user) {
		//TODO add more validation constraints
		if(user==null) {
		return false;
		}
		else {
			return true;
		}
	}
}
