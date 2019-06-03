package com.final_project.controllers;

import com.final_project.entities.Movie;
import com.final_project.entities.MoviePlay;
import com.final_project.repositories.MoviePlayRepository;
import com.final_project.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MoviePlayRepository moviePlayRepository;

    /**
     * Method retrieves all movies, and returns the path of the html page to be rendered.
     * Search value is optional and is only used when using search bar to filter results.
     *
     * @param model
     * @param searchValue
     * @return String
     */
    @GetMapping ("/all-movies")
    public String getAllMovie(Model model, @RequestParam(value = "search", required = false) String searchValue){
        List<Movie> movieList = movieRepository.findAllMovies();

        // if search value != null, set the movies to search list.
        if(searchValue != null) {
            List<Movie> searchList = movieRepository.searchMoviesByTitle(searchValue);
            model.addAttribute("movies", searchList);
            return "movie/all-movies";
        }

        model.addAttribute("movies", movieList);
        return "movie/all-movies";
    }

    /**
     * Method retrieves add movie form.
     *
     * @param movie
     * @param model
     * @return String
     */
    @GetMapping ("/manage/add-movie")
    public String getAddMovie(@ModelAttribute Movie movie, Model model) {
        model.addAttribute(movie);
        return "movie/add-movie";
    }

    /**
     * Method takes a valid movie model and saves it to the database
     *
     * @param movie
     * @param errors
     * @return String
     */
    @PostMapping("/manage/add-movie")
    public String addMovie(@ModelAttribute @Valid Movie movie, Errors errors) {

        // Check if errors - errors are set with validation constraints.
        if(errors.hasErrors()) {
            return "movie/add-movie";
        }

        movieRepository.addMovie(movie);
        return "redirect:/all-movies";
    }

    /**
     * Method retrieves edit movie form.
     *
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/manage/edit-movie/{id}")
    public String getEditMovie(@PathVariable(name = "id") int id, Model model) {
        Movie movieToEdit = movieRepository.findMovieById(id);

        model.addAttribute("movie", movieToEdit);
        return "movie/edit-movie";
    }

    /**
     * Method takes a valid movie model and updates database record.
     *
     * @param movie
     * @param errors
     * @param model
     * @return String
     */
    @PostMapping("/manage/edit-movie")
    public String editMovie(@ModelAttribute @Valid Movie movie, Errors errors, Model model) {

        // Check if errors - errors are set with validation constraints.
        if(errors.hasErrors()) {
            model.addAttribute(movie);
            return "movie/edit-movie";
        }

        movieRepository.editMovie(movie);
        return "redirect:/movie/" + movie.getId();
    }

    /**
     * Method takes a path variable and then deletes record from database
     *
     * @param id
     * @return  String
     */
    @PostMapping("/manage/delete-movie/{id}")
    public String deleteMovie(@PathVariable(name = "id") int id) {
        movieRepository.deleteMovie(id);
        return "redirect:/all-movies";
    }

    /**
     * Method retrieves a movie by id, and then returns the path to the html to be rendered
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/movie/{id}")
    public String getMovieById(@PathVariable(name = "id") int id, Model model) {
        Movie movie = movieRepository.findMovieById(id);

        // get current date and add 4 weeks in days
        LocalDate dateLimit = LocalDate.now().plusDays(30);

        List<MoviePlay> moviePlayList = moviePlayRepository.findPlaysByMovieIdWithinDate(id, LocalDate.now(), dateLimit);

        model.addAttribute("movie", movie);
        model.addAttribute("plays", moviePlayList);

        return "movie/movie-page";
    }
}
