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

    @Query("from User U where U.username = :username and U.password = :password")
    User findUserByUsernameAndPassword(String username, String password);

}
