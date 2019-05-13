package com.final_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieController {

    @GetMapping ("/all-movie")
    public String getAllMovie(){return "all-movie";}



    /*add function to all  buttons
   @GetMapping ("/add-movie")
   @GetMapping ("/delete-movie")
   @PostMapping("show-movie")
   @GetMapping ("/save-movie")*/
}
