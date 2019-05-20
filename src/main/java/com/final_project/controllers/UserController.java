package com.final_project.controllers;

import com.final_project.entities.User;
import com.final_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/customer/profile")
    public String getProfile(Model model, Principal principal) {
        String currentUserEmail = principal.getName();
        /*User user = userRepository.getUserByEmail(currentUserEmail);

        model.addAttribute(user); */

        System.out.println(currentUserEmail);
        return "user/profile";
    }
}
