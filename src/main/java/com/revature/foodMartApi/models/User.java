package com.revature.foodMartApi.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="app_users")
public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	// private Role role;
	
	public User() {
		super();
	}

	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
	
}
