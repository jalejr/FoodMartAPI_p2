/**
 * 
 */
package com.revature.foodMartApi.services;

import java.rmi.server.ExportException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.foodMartApi.models.Role;
import com.revature.foodMartApi.daos.RoleDAO;

/**
 * @author Awaab
 *
 */
@Service
@Transactional
public class RoleService {
	
	private final RoleDAO roleDAO;
	
	@Autowired
	public RoleService(RoleDAO roleDAO) {
		super();
		this.roleDAO = roleDAO;
	}
	
	public List<Role> getAllRoles(){
		return roleDAO.getAll();
		
	}
	
		public Role findRoleById(int id) {
			return roleDAO.findRoleById(id);
		}

	
}
