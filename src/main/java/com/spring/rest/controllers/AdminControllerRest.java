package com.spring.rest.controllers;

import com.spring.rest.models.User;
import com.spring.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminControllerRest {

    private final UserService userService;


    @Autowired
    public AdminControllerRest(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> allUsersRest() {
        return new ResponseEntity<>(userService.listUser(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<User> navBar() {
        return new ResponseEntity<>(userService.findByUsername(userService.getCurrentUsername()), HttpStatus.OK);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<User> update(@PathVariable("id") long id, @RequestBody User user) {
        String oldPass = userService.getUserById(id).getPassword();
        if (oldPass.equals(user.getPassword())) {
            user.setPassword(oldPass);
            userService.updateUser(user);
        } return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("admin/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") long id) {
        userService.removeUser(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
