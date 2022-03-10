package com.revature.foodMartApi.web.servlets;

import java.util.List;
import java.util.Optional;

import com.revature.foodMartApi.models.User;
import com.revature.foodMartApi.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * UserServlet sets up mapping and deals with requests
 */
@RestController
@RequestMapping("/users")
public class UserServlet {

    private final UserService userService;

    /**
     * @param userService
     * constructor
     */
    @Autowired
    public UserServlet(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param user
     * @return
     * Add a user and returns it. Use JSON
     */
    @PostMapping(value = "addUser")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public User postMethodName(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * @return
     * returns all users in a list
     */
    @GetMapping
    @ResponseBody
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    /**
     * @param id
     * @return
     * find user by id and returns it. Use param
     */
    @GetMapping("/id")
    @ResponseBody
    public User findUserById(@RequestParam Integer id) {
        return userService.findUserById(id);
    }

    /**
     * @param username
     * @return
     * find user by username and returns it. Use param
     */
    @GetMapping("/username")
    @ResponseBody
    public User findUserByUsername(@RequestParam String username) {
        return userService.findUserByUsername(username);
    }

    /**
     * @param user
     * update a user. Use Param
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    /**
     * @param user
     * delete a user. Use param
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
    }

    /**
     * @param id
     * delete user by id. Use param
     */
    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@RequestParam Integer id) {
        User user = userService.findUserById(id);
        userService.deleteUser(user);
    }
}
