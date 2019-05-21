package com.final_project.controllers;

import com.final_project.entities.MoviePlay;
import com.final_project.repositories.MoviePlayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MoviePlayController {

    @Autowired
    MoviePlayRepository moviePlayRepository;

    @GetMapping ("/all-movie-plays")
    public String  getAllMoviePlays(Model model) {
        List<MoviePlay> moviePlayList = moviePlayRepository.getMoviePlays();

        model.addAttribute("plays", moviePlayList);

        return "all-movie-plays";
    }

    @GetMapping ("/add-movie-play")
    public String getAddMoviePlay() {
        return "add-movie-play";
    }

    @PostMapping ("/add-movie-play")
    public String addMoviePlay(
            @RequestParam ("theaterId") int theaterId,
            @RequestParam ("movieId") int movieId,
            @RequestParam ("playStart") String playStart) {

        MoviePlay moviePlay = new MoviePlay();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        moviePlay.setPlayStart(LocalDateTime.parse(playStart, formatter));
        moviePlay.setTheaterId(theaterId);
        moviePlay.setMovieId(movieId);

        moviePlayRepository.addMoviePlay(moviePlay);
        return "redirect:/all-movie-plays";
    }

    @PostMapping ("/delete-movie-play")
    public String deleteMoviePlay(@RequestParam(name ="id") int id) {
        moviePlayRepository.deleteMoviePlay(id);
        return "redirect:/all-movie-plays";
    }

    @GetMapping ("/edit-movie-play/{id}")
    public String getEditMoviePlay(@PathVariable (name = "id") int id, Model model) {
        MoviePlay playToEdit = moviePlayRepository.getMoviePlayById(id);

        model.addAttribute("play",playToEdit);

        return "edit-movie-play";
    }

    @PostMapping ("/edit-movie-play")
    public String editMoviePlay(
            @RequestParam("movieId") int movieId,
            @RequestParam("theaterId") int theaterId,
            @RequestParam("playStart") String playStart,
            @RequestParam("playId") int playId) {

        MoviePlay moviePlay = new MoviePlay();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        moviePlay.setPlayStart(LocalDateTime.parse(playStart, formatter));
        moviePlay.setTheaterId(theaterId);
        moviePlay.setMovieId(movieId);
        moviePlay.setId(playId);

        moviePlayRepository.editMoviePlay(moviePlay);
        return "redirect:/all-movie-plays";
    }
}