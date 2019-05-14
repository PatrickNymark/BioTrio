package com.final_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddMovieController {

    @GetMapping("/add-movie")
    public String addMovie(){
        return "add-movie";
    }


}
