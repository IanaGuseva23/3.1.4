package com.spring.rest.controllers;

import com.spring.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUserInfo(Model model) {
        model.addAttribute("user", userService.findByUsername(userService.getCurrentUsername()));
        return "admin";
    }

}
