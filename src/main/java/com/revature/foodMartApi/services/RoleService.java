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
 *RoleService which handles communication with the DAO
 */
@Service
public class RoleService {

	private final RoleDAO roleDAO;

	/**
	 * @param roleDAO
	 * constructor
	 */
	@Autowired
	public RoleService(RoleDAO roleDAO) {
		super();
		this.roleDAO = roleDAO;
	}

	/**
	 * @param role
	 * @return
	 * Creates role in db and returns it
	 */
	@Transactional
	public Role createRole(Role role) {
		return roleDAO.save(role);
	}

	/**
	 * @param role
	 * @return
	 * updates Role and returns it
	 */
	@Transactional
	public Role updateRole (Role role) {
		return roleDAO.save(role);
	}

	/**
	 * @param id
	 * deletes Role
	 */
	@Transactional
	public void deleteRole(int id) {
		roleDAO.deleteById(id);
	}

	/**
	 * @return
	 * Returns a List of all Roles in DB
	 */
	public List<Role> getAllRoles() {
		return (List<Role>) roleDAO.findAll();
	}

	/**
	 * @param id
	 * @return
	 * returns a role based on Id
	 */
	public Role findRoleById(int id) {
		return roleDAO.findRoleById(id);
	}
	
	


}
