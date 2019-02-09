package com.example.logintodatabase.controllers;


import com.example.logintodatabase.models.services.UserService;
import com.example.logintodatabase.models.services.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    UserSession userSession;

    @GetMapping("/")
    public String index(Model model) {
        if(!userSession.isLogin()){
            return "redirect:/login-form";
        }

        model.addAttribute("contacts", userSession.getUserEntity().getContacts());
        return "index";
    }
}
