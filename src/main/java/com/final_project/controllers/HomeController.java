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
        List<Movie> top3Movies = movieRepository.findTop3Movies();
        List<MoviePlay> next3MoviePlays = moviePlayRepository.findNext3MoviePlays();
        List<Movie> movieList = movieRepository.findAllMovies();

        model.addAttribute("movies", top3Movies);
        model.addAttribute("moviePlays", next3MoviePlays);
        model.addAttribute("movieList", movieList);

        return "index";
    }
}
