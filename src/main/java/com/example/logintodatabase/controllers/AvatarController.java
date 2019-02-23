package com.example.logintodatabase.controllers;

import com.example.logintodatabase.models.services.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AvatarController {

    final AvatarService avatarService;

    @Autowired
    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping("/contact/avatar/change/{id}")
    public String avatar(@RequestParam("avatar") MultipartFile multipartFile,
                         @PathVariable("id") int id,
                         RedirectAttributes model){

        model.addFlashAttribute("fajneInfo", "Jescze lepsza wiadomosc w tym info :)");
        avatarService.uploadAvatar(multipartFile, id);
        return "redirect:/";
    }

    @GetMapping("/contact/avatar/delete/{id}")
    public String avatarDelete(@PathVariable("id") int id){
        avatarService.deleteAvatar(id);
        return "redirect:/";
    }
}
