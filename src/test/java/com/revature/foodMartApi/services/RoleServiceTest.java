package com.revature.foodMartApi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.foodMartApi.daos.RoleDAO;
import com.revature.foodMartApi.models.Role;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {
//	@Mock
	private RoleDAO roleDAO;	
	private RoleService roleService;
	
	@BeforeEach
	void testPrep() {
		roleDAO = mock(RoleDAO.class);
		roleService = new RoleService(roleDAO);
	}
	
	@Test
	public void when_create_role() {
		Role role = roleService.createRole("test role");
		assertNotNull(role);
	}
	
	@Test
	public void when_update_role() {
		Role role = roleService.findRoleById(1);
		role.setDescription("update role");
		Role roleUpdated = roleService.updateRole(role);
		assertEquals(roleUpdated.getDescription(), "updated role");
//		assertNotNull(roleUpdated);
		
	}
	
	@Test
	public void when_get_All_roles() {
		List<Role> roles = roleService.getAllRoles();
		assertNotNull(roles);
	}

}
