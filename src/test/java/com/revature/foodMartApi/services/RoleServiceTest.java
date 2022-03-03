package com.revature.foodMartApi.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.foodMartApi.daos.RoleDAO;
import com.revature.foodMartApi.models.Role;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {
	@Mock
	private RoleDAO roleDAO;
	
	RoleService roleService;
	
	@BeforeEach
	void testPrep() {
		roleService = new RoleService(roleDAO);
	}
	
	@Test
	public void when_create_role() {
		Role role = roleService.createRole("test role");
//		assertEquals(roles, 0);
		assertNotNull(role);
	}

}
