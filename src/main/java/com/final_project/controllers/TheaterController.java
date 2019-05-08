package com.final_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TheaterController {
    @GetMapping ("/all-theater")
    public String getAllTheaters(){
        return "all-theater";
    }
}
