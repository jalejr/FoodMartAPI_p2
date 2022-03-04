package com.revature.foodMartApi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    public User addUser(User newUser) {
        if (!isValidUser(newUser)) {
            throw new InvalidRequestException("Invalid user data provided.");
        }
        boolean isEmailAvailable = isEmailAvailable(newUser.getEmail());
        boolean isUsernameAvailable = isUsernameAvailable(newUser.getUsername());
        if (!isUsernameAvailable || !isEmailAvailable) {
            if (isUsernameAvailable) {
                throw new InvalidRequestException("Email not available.");
            } else if (isEmailAvailable) {
                throw new InvalidRequestException("Username not available.");
            } else {
                throw new InvalidRequestException("Username and Email not available.");
            }
        }
        User persistedUser = userDAO.save(newUser);
        if (persistedUser == null) {
            throw new ResourcePersistenceException("User was not persisted.");
        }
        return newUser;
    }

    public boolean isUsernameAvailable(String username) {
        return (userDAO.findByUsername(username) == null);
    }

    public boolean isEmailAvailable(String email) {
        return (userDAO.findByEmail(email) == null);
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        Iterable<User> userIterable = userDAO.findAll();
        for (User user : userIterable) {
            users.add(user);
        }
        return users;
    }

    public User findUserById(int id) {
        // TODO handles null pointer exception when optional type object is null
        try {
            return userDAO.findById(id).get();
        } catch (NullPointerException | NoSuchElementException e) {
            // e.printStackTrace();
            return null;
        }
    }

    public User findUserByUsername(String username) {
        //TODO valid input check
        return userDAO.findByUsername(username);
    }

    public User findUserByEmail(String email) {
        //TODO valid input check
        return userDAO.findByEmail(email);
    }

    public User authenticateUser(User user) {
        // TODO
        return null;
    }

    public boolean updateUser(User updatedUser) {
        // TODO
        //check if valid
        if (!isValidUser(updatedUser)) {
            throw new InvalidRequestException("Invalid user data provided.");
        }
        //check if exists? return false : execute update
        //on false, throw invalid request exception, offer to register?
        if (userDAO.findById(updatedUser.getId()) == null){
            throw new InvalidRequestException("Invalid Request: Cannot update user not in database.");
        }
        if (userDAO.save(updatedUser) == updatedUser){
            return true;
        }
        return false;
    }

    public boolean deleteUser(User user) {
        // TODO
        //check if valid
        //check if exists? return false : execute delete
        //on false, throw invalid request exception?
        return false;
    }

    private boolean isValidUser(User user) {
        // TODO consider more validation constraints
        if (user == null) {
            return false;
        } else if (user.getUsername() == null || user.getUsername().equals("")) {
            return false;
        } else if (user.getEmail() == null || user.getEmail().equals("")) {
            return false;
        } else if (user.getPassword() == null || user.getPassword().equals("")) {
            return false;
        } else {
            return true;
        }
    }
}
