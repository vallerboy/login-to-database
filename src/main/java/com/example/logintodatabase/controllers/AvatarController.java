package com.example.logintodatabase.controllers;

import com.example.logintodatabase.models.services.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AvatarController {

    final AvatarService avatarService;

    @Autowired
    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping("/contact/avatar/{id}")
    public String avatar(@RequestParam("avatar") MultipartFile multipartFile,
                         @PathVariable("id") int id){

    }


}
