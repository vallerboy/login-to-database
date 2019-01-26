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


@Controller
public class LoginController {

   // @Autowired
    //UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login-form")
    public String loginForm(Model model){
        model.addAttribute("user",new User());
        return "login-form";
    }
    @PostMapping("login-form")

    public String getUser(@ModelAttribute User user){

        String result = "login-failed";




        if(userRepository.existsByNameAndPassword(user.getName(),user.getPassword())){
            result = "login-succes";
        }
        if(!userRepository.existsByName(user.getName())){
            result = "invalid-user";
        }

        return result;

    }
}
