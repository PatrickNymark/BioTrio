package com.final_project.controllers;

import com.final_project.entities.Movie;
import com.final_project.entities.MoviePlay;
import com.final_project.repositories.MoviePlayRepository;
import com.final_project.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MoviePlayRepository moviePlayRepository;

    @GetMapping ("/all-movies")
    public String getAllMovie(Model model){
        List<Movie> movieList = movieRepository.getAllMovies();

        model.addAttribute("movies", movieList);

        return "all-movies";
    }

    @GetMapping ("/manage/add-movie")
    public String getAddMovie() {
        return "add-movie";
    }

    @PostMapping("/manage/add-movie")
    public String addMovie(@ModelAttribute Movie movie) {
        movieRepository.addMovie(movie);
        return "redirect:/all-movies";
    }

    @GetMapping("/manage/edit-movie/{id}")
    public String getEditMovie(@PathVariable(name = "id") int id, Model model) {
        Movie movieToEdit = movieRepository.getMovieById(id);

        model.addAttribute("movie",movieToEdit);
        return "edit-movie";
    }

    @PostMapping("/manage/edit-movie")
    public String editMovie(@ModelAttribute Movie movie) {
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
        Movie movie = movieRepository.getMovieById(id);
        List<MoviePlay> moviePlayList = moviePlayRepository.getMoviePlaysByMovieId(id);

        model.addAttribute("movie", movie);
        model.addAttribute("plays", moviePlayList);

        return "movie-page";
    }




    /*add function to all  buttons
   @GetMapping ("/add-movie")
   @GetMapping ("/delete-movie")
   @PostMapping("show-movie")
   @GetMapping ("/save-movie")*/
}
