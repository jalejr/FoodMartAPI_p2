/**
 * 
 */
package com.revature.foodMartApi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Awaab
 * @since
 *
 */
@Entity
@Table(name = "roles")
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private int id;

	@Column(unique = true, nullable = false)
	private String description;

}
