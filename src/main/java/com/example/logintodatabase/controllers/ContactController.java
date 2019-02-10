package com.example.logintodatabase.controllers;

import com.example.logintodatabase.models.forms.AddContactForm;
import com.example.logintodatabase.models.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ContactController {


    @Autowired
    ContactService contactService;

    @GetMapping("/contact/add")
    public String contact(Model model) {
        model.addAttribute("addContactForm", new AddContactForm());
        return "add-contact";
    }

    @PostMapping("/contact/add")
    public String contact(@ModelAttribute @Valid AddContactForm addContactForm,
                          BindingResult result){
        if(result.hasErrors()){
            return "add-contact";
        }

        contactService.addContact(addContactForm);
        return "redirect:/";
    }

    @GetMapping("/contact/delete/{id}")
    public String contactDelete(@PathVariable("id") int id){
        contactService.deleteContact(id);
        return "redirect:/";
    }

}