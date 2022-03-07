package com.revature.foodMartApi.web.servlets;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
	
	
	
}
