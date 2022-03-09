/**
 * 
 */
package com.revature.foodMartApi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Objects;

import javax.persistence.*;
import javax.persistence.Table;

/**
 * @author Awaab
 * @since
 *
 */
//@EntityScan
@Entity
@Table(name = "roles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private int roleId;

	@Column(unique = true, nullable = false)
	private String role_description;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(String description) {
		super();
		this.role_description = description;
	}

	public Role(int id, String description) {
		super();
		this.roleId = id;
		this.role_description = description;
	}

	public int getId() {
		return roleId;
	}

	public void setId(int id) {
		this.roleId = id;
	}

	public String getDescription() {
		return role_description;
	}

	public void setDescription(String description) {
		this.role_description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(role_description, roleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(role_description, other.role_description) && roleId == other.roleId;
	}

	@Override
	public String toString() {
		return "Role [id=" + roleId + ", description=" + role_description + "]";
	}

}
