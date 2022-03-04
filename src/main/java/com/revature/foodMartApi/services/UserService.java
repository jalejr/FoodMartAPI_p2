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
        boolean isEmailAvailable = isEmailAvailable(user.getEmail());
        boolean isUsernameAvailable = isUsernameAvailable(user.getUsername());
        if (!isUsernameAvailable || !isEmailAvailable) {
            if (isUsernameAvailable) {
                throw new InvalidRequestException("Email not available.");
            }
            else if (isEmailAvailable) {
                throw new InvalidRequestException("Username not available.");
            }
            else {
                throw new InvalidRequestException("Username and Email not available.");
            }
        }
        User persistedUser = userDAO.save(user);
        if (persistedUser == null) {
            throw new ResourcePersistenceException("User was not persisted.");
        }
        return user;
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
        // TODO

        return userDAO.findById(id).get();
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
