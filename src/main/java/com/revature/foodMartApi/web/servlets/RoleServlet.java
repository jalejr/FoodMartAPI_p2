/**
 * 
 */
package com.revature.foodMartApi.web.servlets;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@CrossOrigin
public class RoleServlet {
	private final RoleService roleService;

	@Autowired
	public RoleServlet(RoleService roleService) {
		super();
		this.roleService = roleService;
	}
	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Role createRole(@RequestBody Role role) {
		return roleService.createRole(role);
	}
	
	@GetMapping("/byId")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Role findRoleById(@RequestParam int id) {
		return roleService.findRoleById(id);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Role> getAllRoles(){
		return (List<Role>)roleService.getAllRoles();
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Role updateAvatarItem(@RequestBody Role role) {
		return roleService.updateRole(role);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void deleteAvatarItem(@RequestParam int id) {
		roleService.deleteRole(id);
	}
	
	
}
