/**
 * 
 */
package com.revature.foodMartApi.daos;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.foodMartApi.models.Role;

/**
 * @author Samar
 *
 */
@Repository
public class RoleDAO {
	private SessionFactory sessionFactory;
	
	public RoleDAO(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}



	public List<Role> getAll(){
		try (Session session = sessionFactory.openSession()){ 
			String sqlSyntax = " FROM role S ";
			List<Role> roles = session.createQuery(sqlSyntax).getResultList();
			return roles;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Role findRoleById(int id) {
		try (Session session = sessionFactory.openSession()){
			String sqlSyntax = " FROM role S where roleId = :id";
			Role role = (Role)session.createQuery(sqlSyntax).getSingleResult();
			return role;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
