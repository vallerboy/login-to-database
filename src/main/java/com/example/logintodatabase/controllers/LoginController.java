package com.example.logintodatabase.controllers;

import com.example.logintodatabase.models.User;
import com.example.logintodatabase.models.repositories.UserRepository;
import com.example.logintodatabase.models.services.UserService;
import com.example.logintodatabase.models.services.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LoginController {

    final UserService userService;

    final
    UserSession userSession;

    @Autowired
    public LoginController(UserService userService, UserSession userSession) {
        this.userService = userService;
        this.userSession = userSession;
    }



    @GetMapping("/login-form")
    public String loginForm(Model model){
        model.addAttribute("user",new User());
        return "login-form";
    }

    @PostMapping("/login-form")
    public String getUser(@ModelAttribute User user,
                          Model model){

        UserService.LoginResponse loginResponse = userService.login(user);
        if(loginResponse == UserService.LoginResponse.SUCCESS){
            return "redirect:/";
        }

        model.addAttribute("loginResponse", loginResponse);
        return "login-form";
    }

    @GetMapping("/logout")
    public  String logout(){
        userSession.logout();
        return "redirect:/login-form";
    }
}
