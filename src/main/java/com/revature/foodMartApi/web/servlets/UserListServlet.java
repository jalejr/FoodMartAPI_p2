package com.revature.foodMartApi.web.servlets;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.foodMartApi.models.UserList;
import com.revature.foodMartApi.services.UserListService;

@RestController
@RequestMapping("/userLists")
public class UserListServlet {
	
	private final UserListService userListService;
	
	
	//constructor
	@Autowired
	public UserListServlet(UserListService userListService) {
		this.userListService = userListService;
	}
	
	@PostMapping(value = "addUserlist")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public UserList addUserList(@RequestBody UserList userList) {
		return userListService.addUserList(userList);
		
	}
	
	@GetMapping("/byId")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public UserList findUserListById(@RequestParam int id) {
		return userListService.findUserListById(id);
		
	}
	
	@GetMapping("/byUserId")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Optional<UserList> findUserListByUserId(@RequestParam int id){
		return userListService.findUserListByUserId(id);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<UserList> findAll(){
		return (List<UserList>)userListService.findAll();
	}
	
	@DeleteMapping("/byId")
	@ResponseStatus(HttpStatus.OK)
	public void deleteUserListById(@RequestParam int id) {
		userListService.deletById(id);
	}
	
	
	
}
