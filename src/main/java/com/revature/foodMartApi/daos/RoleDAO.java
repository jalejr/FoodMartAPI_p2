/**
 * 
 */
package com.revature.foodMartApi.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.foodMartApi.models.Role;

/**
 * @author Awaab
 *RoleDAO implemented using CrudRepository. Custom impl but never used.
 */
@Repository
public interface RoleDAO extends CrudRepository<Role, Integer>{
	
	@Query("FROM Role r where r.roleId = :roleId")
	Role findRoleById(int roleId);
}
//public class RoleDAO {
//	private SessionFactory sessionFactory;
//	
//	public RoleDAO(SessionFactory sessionFactory) {
//		super();
//		this.sessionFactory = sessionFactory;
//	}
//	public List<Role> findAll(){
//		try (Session session = sessionFactory.openSession()){ 
//			String sqlSyntax = " FROM Role S ";
//			List<Role> roles = session.createQuery(sqlSyntax).getResultList();
//			return roles;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}	
//	public Role findRoleById(int id) 
//	{
//		
//		try (Session session = sessionFactory.openSession()) {
//			return session.get(Role.class, id);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	public Role save(Role role) {
//		try (Session session = sessionFactory.openSession()) {
//			session.merge(role);
//			return role;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	public void deleteById(int id) {
//		try (Session session = sessionFactory.openSession()) {
//			session.remove(findRoleById(id));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//}
