package com.revature.foodMartApi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.foodMartApi.daos.UserDAO;
import com.revature.foodMartApi.exceptions.InvalidRequestException;
import com.revature.foodMartApi.exceptions.ResourcePersistenceException;
import com.revature.foodMartApi.models.User;

@Service
@Transactional
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User addUser(User user) {
        if (!isValidUser(user)) {
            throw new InvalidRequestException("Invalid user data provided.");
        }
        User persistedUser = userDAO.save(user);
        if (persistedUser == null) {
            throw new ResourcePersistenceException("User was not persisted.");
        }
        return user;
    }

    public List<User> findAllUsers() {
        // TODO
        List<User> users = new ArrayList<>();
        //iterate through iterable and add to users
        Iterable<User> userIterable = userDAO.findAll();
        for(User user : userIterable){
            users.add(user);
        }
        return users;
    }

    public User findUserById(int id) {
        // TODO
        return null;
    }

    public User findUserByUsername(String username) {
        // TODO
        return null;
    }

    public User findUserByEmail(String email) {
        // TODO
        return null;
    }

    public User authenticateUser(User user) {
        // TODO
        return null;
    }

    public boolean updateUser(User user) {
        // TODO
        return false;
    }

    public boolean deleteUser(User user) {
        // TODO
        return false;
    }

    private boolean isValidUser(User user) {
        // TODO add more validation constraints
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }
}
