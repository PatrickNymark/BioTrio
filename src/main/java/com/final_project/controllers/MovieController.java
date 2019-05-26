package com.final_project.controllers;

import com.final_project.entities.Movie;
import com.final_project.entities.MoviePlay;
import com.final_project.repositories.MoviePlayRepository;
import com.final_project.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MoviePlayRepository moviePlayRepository;

    @GetMapping ("/all-movies")
    public String getAllMovie(Model model){
        List<Movie> movieList = movieRepository.findAllMovies();

        model.addAttribute("movies", movieList);
        return "movie/all-movies";
    }

    @GetMapping ("/manage/add-movie")
    public String getAddMovie(@ModelAttribute Movie movie, Model model) {
        model.addAttribute(movie);
        return "movie/add-movie";
    }

    @PostMapping("/manage/add-movie")
    public String addMovie(@ModelAttribute @Valid Movie movie, Errors errors) {
        if(errors.hasErrors()) {
            return "movie/add-movie";
        }

        movieRepository.addMovie(movie);
        return "redirect:/all-movies";
    }

    @GetMapping("/manage/edit-movie/{id}")
    public String getEditMovie(@PathVariable(name = "id") int id, Model model) {
        Movie movieToEdit = movieRepository.findMovieById(id);

        model.addAttribute("movie", movieToEdit);
        return "movie/edit-movie";
    }

    @PostMapping("/manage/edit-movie")
    public String editMovie(@ModelAttribute @Valid Movie movie, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute(movie);
            return "movie/edit-movie";
        }

        movieRepository.editMovie(movie);
        return "redirect:/movie/" + movie.getId();
    }

    @PostMapping("/manage/delete-movie/{id}")
    public String deleteMovie(@PathVariable(name = "id") int id) {
        movieRepository.deleteMovie(id);
        return "redirect:/all-movies";
    }

    @GetMapping("/movie/{id}")
    public String getMovieById(@PathVariable(name = "id") int id, Model model) {
        Movie movie = movieRepository.findMovieById(id);
        List<MoviePlay> moviePlayList = moviePlayRepository.getMoviePlaysByMovieId(id);

        model.addAttribute("movie", movie);
        model.addAttribute("plays", moviePlayList);

        return "movie/movie-page";
    }
}
