package com.spring.rest.service;

import com.spring.rest.models.User;

import java.util.List;

public interface UserService {
    List<User> listUser ();
    User getUserById (long id);
    void addUser (User user);
    void removeUser (long id);
    void updateUser (User user);
    User findByUsername(String username);
    String getCurrentUsername();

}
