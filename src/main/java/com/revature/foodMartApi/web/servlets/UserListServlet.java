package com.revature.foodMartApi.web.servlets;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	@PostMapping
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
	
	
	
	
}
