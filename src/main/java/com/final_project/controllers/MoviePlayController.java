package com.final_project.controllers;

import com.final_project.entities.MoviePlay;
import com.final_project.repositories.MoviePlayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MoviePlayController {

    @Autowired
    MoviePlayRepository moviePlayRepository;

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
        return "redirect:/";
    }
}