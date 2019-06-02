package com.final_project.controllers;

import com.final_project.entities.Movie;
import com.final_project.entities.MoviePlay;
import com.final_project.repositories.MoviePlayRepository;
import com.final_project.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MoviePlayRepository moviePlayRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Movie> topMovies = movieRepository.findAllMovies();
        List<MoviePlay> nextMoviePlays = moviePlayRepository.findNextMoviePlays();
        List<Movie> movieList = movieRepository.findAllMovies();

        model.addAttribute("movies", topMovies);
        model.addAttribute("moviePlays", nextMoviePlays);
        model.addAttribute("movieList", movieList);

        return "index";
    }
}
