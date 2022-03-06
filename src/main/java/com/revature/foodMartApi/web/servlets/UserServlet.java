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

@RestController
@RequestMapping("/users")
public class UserServlet {

    private final UserService userService;

    @Autowired
    public UserServlet(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "addUser")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public User postMethodName(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/id")
    public User findUserById(@RequestParam Integer id) {
        return userService.findUserById(id);
    }

    @GetMapping("/username")
    public User findUserByUsername(@RequestParam String username) {
        return userService.findUserByUsername(username);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
    }

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@RequestParam Integer id) {
        User user = userService.findUserById(id);
        userService.deleteUser(user);
    }
}
