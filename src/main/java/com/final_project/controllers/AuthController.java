package com.final_project.controllers;

import com.final_project.entities.User;
import com.final_project.repositories.UserRepository;
import com.final_project.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    SecurityConfig securityConfig;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/manage/register")
    public String register() {
        return "auth/register";
    }

    @PostMapping("/manage/register")
    public String addUser(@ModelAttribute User user) {
        // Hash password with bcrypt.
        String encodedPass = securityConfig.passwordEncoder().encode(user.getPassword());
        user.setPassword(encodedPass);
        user.setActive(true);

        userRepository.addUser(user);
        return "redirect:/login";
    }

}
