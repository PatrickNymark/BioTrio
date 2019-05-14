package com.final_project.controllers;

import com.final_project.entities.MoviePlay;
import com.final_project.repositories.MoviePlayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MoviePlayController {

    @Autowired
    MoviePlayRepository moviePlayRepository;

    @GetMapping("/movie-plays/{movieId}")
    public String getMoviePlays(@PathVariable (name = "movieId") int movieId, Model model) {
        List<MoviePlay> moviePlayList = moviePlayRepository.getMoviePlayByMovieId(movieId);

        model.addAttribute("moviePlays", moviePlayList);

        return "movie-plays";
    }
}
