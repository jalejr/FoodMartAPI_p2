package com.revature.foodMartApi.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.revature.foodMartApi.models.User;

@Repository
public interface UserDAO extends CrudRepository<User, Integer>{

    User findByUsername(String username);
    User findByEmail(String email);
    // @Query("select * from User u where u.username = :username and u.password = :password")
    // User findUserByUsernameAndPassword(String username, String password);

}
