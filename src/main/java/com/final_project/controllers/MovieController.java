package com.final_project.controllers;

import com.final_project.entities.Movie;
import com.final_project.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping ("/all-movies")
    public String getAllMovie(Model model){
        List<Movie> movieList = movieRepository.getAllMovies();

        model.addAttribute("movies", movieList);
        System.out.println("database connected");
        return "all-movies";
    }

    @GetMapping ("/add-movie")
    public String addMovie() {
        return "add-movie";
    }

    @GetMapping("/movie/{id}")
    public String getMovieById(@PathVariable(name = "id") int id, Model model) {
        Movie movie = movieRepository.getMovieById(id);

        model.addAttribute("movie", movie);

        return "movie-page";
    }



    /*add function to all  buttons
   @GetMapping ("/add-movie")
   @GetMapping ("/delete-movie")
   @PostMapping("show-movie")
   @GetMapping ("/save-movie")*/
}
