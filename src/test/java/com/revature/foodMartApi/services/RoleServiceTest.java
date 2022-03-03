package com.revature.foodMartApi.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.foodMartApi.Model.Role;

class RoleServiceTest {
	@Autowired
	private RoleService roleService;
	
	
	@Test
	public void whenApplicationStarts_thenHibernateCreatesInitialRecordForRole() {
		List<Role> roles = roleService.getAllRoles();
		assertEquals(roles.size(), 0);
	}

}
