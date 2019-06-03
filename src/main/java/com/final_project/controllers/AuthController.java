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

    /**
     * Method retrieves login form
     *
     * @return String
     */
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    /**
     * Method retrieves register form
     *
     * @return String
     */
    @GetMapping("/manage/register")
    public String register() {
        return "auth/register";
    }

    /**
     * Method takes a User model and saves it to the database
     *
     * @param user
     * @return String
     */
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
