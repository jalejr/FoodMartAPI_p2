package com.revature.foodMartApi.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.revature.foodMartApi.models.User;

@Repository
public interface UserDAO extends CrudRepository<User, Integer>{

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Query("from User where User.username = :username and User.password = :password")
    User findUserByUsernameAndPassword(String username, String password);

}
