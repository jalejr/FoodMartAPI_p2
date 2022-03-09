package com.revature.foodMartApi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
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
		Role role = new Role("Test");
		when(roleDAO.save(role)).thenReturn(role);
		Role newRole = roleService.createRole(role);
		Assertions.assertNotNull(newRole);
	}

	@Test
	public void when_update_role() {
		Role role = new Role("Test");
		role.setDescription("update role");
		when(roleDAO.save(role)).thenReturn(role);
		Role roleUpdated = roleService.updateRole(role);
		assertEquals(roleUpdated.getDescription(), "update role");
	}

	@Test
	public void when_get_All_roles() {
		List<Role> roles = roleService.getAllRoles();
		assertNotNull(roles);
	}

}
