package com.final_project.controllers;

import com.final_project.entities.Movie;
import com.final_project.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping ("/all-movies")
    public String getAllMovie(Model model){
        List<Movie> movieList = movieRepository.getAllMovies();

        model.addAttribute("movies", movieList);

        return "all-movies";
    }



    /*add function to all  buttons
   @GetMapping ("/add-movie")
   @GetMapping ("/delete-movie")
   @PostMapping("show-movie")
   @GetMapping ("/save-movie")*/
}
