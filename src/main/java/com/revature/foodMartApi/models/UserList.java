package com.revature.foodMartApi.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


//creating userlist table
@Entity
@Table(name= "userlist")
public class UserList {

	//creating list id into userlist table
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //list ID serial 
	@Column(name = "list_id")
	private int id;
	
	//creating user id foreign key not null
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	
	//constructors
	public UserList() {
		super();
	}


	public UserList(int id, User user) {
		super();
		this.id = id;
		this.user = user;
	}

	//getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	//to string auto generated 
	@Override
	public String toString() {
		return "UserList [id=" + id + "]";
	}

	
	//hashcode and equals
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserList other = (UserList) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
	
}
