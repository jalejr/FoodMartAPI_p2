/**
 * 
 */
package com.revature.foodMartApi.daos;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.foodMartApi.Model.Role;
import com.revature.foodMartApi.utils.HibernateUtil;

/**
 * @author Samar
 *
 */
@Repository
public class RoleDAO {
	
	public List<Role> getAll(){
		try {
			Session session = HibernateUtil.getSession(); 
			String sqlSyntax = " FROM role S ";
			List<Role> roles = session.createQuery(sqlSyntax).list();
			return roles;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtil.closeSession();
		}
	}
	
}
