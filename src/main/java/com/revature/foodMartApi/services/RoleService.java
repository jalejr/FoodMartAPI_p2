/**
 * 
 */
package com.revature.foodMartApi.services;

import java.rmi.server.ExportException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.foodMartApi.Model.Role;
import com.revature.foodMartApi.daos.RoleDAO;

/**
 * @author Samar
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
	
	
//	public boolean registerNewScientist(String description) throws ExportException {
//		
//		Role role = new Role(description);
//		Role returnRole = roleDAO.save(role);
//		
//		return returnRole != null;
//	}
	
	public List<Role> getAllRoles(){
		return roleDAO.getAll();
		
	}
	
//	@Transactional
//	public void updateRole (Role role) {
//		try {
//			roleDAO.update(role);
//		} catch (Exception e) {
//			throw e;
//		}
//	}

	
}
