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

/**
 * UserService uses DAO to access DB and make calls
 */
@Service
@Transactional
public class UserService {
    private final UserDAO userDAO;

    /**
     * @param userDAO
     * constructor
     */
    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * @param newUser
     * @return
     * Returns a user after it has added to db
     */
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

    /**
     * @param username
     * @return
     * Makes sure a username is available to be used. Returns true or false
     */
    public boolean isUsernameAvailable(String username) {
        return (userDAO.findByUsername(username) == null);
    }

    /**
     * @param email
     * @return
     * Makes sure a email is available to be used. Returns true or false
     */
    public boolean isEmailAvailable(String email) {
        return (userDAO.findByEmail(email) == null);
    }

    /**
     * @return
     * finds all users in DB then returns a list of it
     */
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        Iterable<User> userIterable = userDAO.findAll();
        for (User user : userIterable) {
            users.add(user);
        }
        return users;
    }

    /**
     * @param id
     * @return
     * returns user by Id
     */
    public User findUserById(int id) {
        // TODO handles null pointer exception when optional type object is null
        try {
            return userDAO.findById(id).get();
        } catch (NullPointerException | NoSuchElementException e) {
            // e.printStackTrace();
            return null;
        }
    }

    /**
     * @param username
     * @return
     * Returns a user by using Username
     */
    public User findUserByUsername(String username) {
        if(username == null || username.equals("")){
            return null;
        }
        return userDAO.findByUsername(username);
    }

    /**
     * @param email
     * @return
     * returns a user by using Email
     */
    public User findUserByEmail(String email) {
        if(email == null || email.equals("")){
            return null;
        }
        return userDAO.findByEmail(email);
    }

    /**
     * @param user
     * @return
     * unfinished method
     */
    public User authenticateUser(User user) {
        // TODO
        return null;
    }

    /**
     * @param updatedUser
     * @return
     * Update a user and return boolean value which says if it happened
     */
    public boolean updateUser(User updatedUser) {
        // check if valid
        if (!isValidUser(updatedUser)) {
            throw new InvalidRequestException("Invalid user data provided.");
        }
        // check if exists? execute update : throw exception
        // on false, throw invalid request exception, offer to register?
        if (userDAO.findById(updatedUser.getId()) == null) {
            throw new InvalidRequestException("Invalid Request: Cannot update user not in database.");
        }
        if (userDAO.save(updatedUser) == updatedUser) {
            return true;
        }
        return false;
    }

    /**
     * @param deletedUser
     * @return
     * delete a user and return boolean
     */
    public boolean deleteUser(User deletedUser) {
        // TODO
        // check if valid
        if (!isValidUser(deletedUser)) {
            throw new InvalidRequestException("Invalid user data provided.");
        }
        // check if exists? execute delete : throw exception
        if (userDAO.findById(deletedUser.getId()) == null) {
            throw new InvalidRequestException("Invalid Request: Cannot delete user not in database.");
        }
        userDAO.delete(deletedUser);
        if (userDAO.findById(deletedUser.getId()) == null) {
            return true;
        } else {
            //if SQL error? but that would throw exception and not return to this step? 
            //may be unreachable code
            throw new ResourcePersistenceException("User deletion was not persisted.");
        }
    }

    /**
     * @param user
     * @return
     * checks user and returns if valid
     */
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