/**
 * 
 */
package com.revature.foodMartApi.servlets;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.foodMartApi.models.Role;
import com.revature.foodMartApi.services.RoleService;

/**
 * @author Awaab
 *
 */
@RestController
@RequestMapping("/roles")
public class RoleServlet {
	private final RoleService roleService;

	@Autowired
	public RoleServlet(RoleService roleService) {
		super();
		this.roleService = roleService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Role> getAllRoles(){
		return roleService.getAllRoles();
	}
	
}
