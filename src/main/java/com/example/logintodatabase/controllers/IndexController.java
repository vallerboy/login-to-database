package com.example.logintodatabase.controllers;


import com.example.logintodatabase.models.entities.ContactEntity;
import com.example.logintodatabase.models.services.ContactService;
import com.example.logintodatabase.models.services.UserService;
import com.example.logintodatabase.models.services.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    UserSession userSession;

    @Autowired
    UserService userService;

    @Autowired
    ContactService contactService;

    @GetMapping("/")
    public String index(Model model) {
        if(!userSession.isLogin()){
            return "redirect:/login-form";
        }

        model.addAttribute("contacts", contactService.getAllContactsForLoginUser(0));
        System.out.println("WIELKSOC KOLEKCJI: " + userSession.getUserEntity().getContacts().size());
        return "index";
    }

    @GetMapping("/{page}")
    public String index(Model model,
                        @PathVariable("page") int page){
        if(!userSession.isLogin()){
            return "redirect:/login-form";
        }

        Page<ContactEntity> pageObject = contactService.getAllContactsForLoginUser(page);
        if(pageObject.isEmpty()){
            return "redirect:/0";
        }


        model.addAttribute("contacts", pageObject);
        return "index";
    }
}
