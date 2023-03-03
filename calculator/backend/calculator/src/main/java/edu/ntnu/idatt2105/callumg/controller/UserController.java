package edu.ntnu.idatt2105.callumg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.ntnu.idatt2105.callumg.model.User;
import edu.ntnu.idatt2105.callumg.service.UserService;


public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/user/{username}")
    public User getUser(@PathVariable("username") String username) {
        return userService.getUser(username);
    }

    @PutMapping("/user/{username}")
    public User updateUser(@RequestBody User user, @PathVariable("username") String username) {
        return userService.updateUser(user, username);
    }

    @DeleteMapping("/user/{username}")
    public void deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
    }
}
