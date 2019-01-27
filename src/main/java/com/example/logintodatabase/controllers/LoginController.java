package com.example.logintodatabase.controllers;

import com.example.logintodatabase.models.User;
import com.example.logintodatabase.models.repositories.UserRepository;
import com.example.logintodatabase.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/login-form")
    public String loginForm(Model model){
        model.addAttribute("user",new User());
        return "login-form";
    }

    @PostMapping("/login-form")
    @ResponseBody
    public String getUser(@ModelAttribute User user){
        if(userService.login(user)){
            return "login correct";
        }
        return "bad login or password";
    }
}
