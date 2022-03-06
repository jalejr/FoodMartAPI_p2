/**
 * 
 */
package com.revature.foodMartApi.services;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.foodMartApi.models.Role;
import com.revature.foodMartApi.daos.RoleDAO;

/**
 * @author Awaab
 *
 */
@Service
public class RoleService {

	private final RoleDAO roleDAO;

	@Autowired
	public RoleService(RoleDAO roleDAO) {
		super();
		this.roleDAO = roleDAO;
	}
	
	@Transactional
	public Role createRole(String description) {
		Role role = new Role();
		role.setDescription(description);
		return roleDAO.save(role);
	}
	
	@Transactional
	public Role updateRole (Role role) {
		return roleDAO.save(role);
	}

	@Transactional
	public void deleteRole(int id) {
		roleDAO.deleteById(id);
	}
	
	public List<Role> getAllRoles() {
		return (List<Role>) roleDAO.findAll();
	}

	public Role findRoleById(int id) {
		return roleDAO.findRoleById(id);
	}
	
	


}
