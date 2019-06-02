package com.final_project.controllers;

import com.final_project.entities.*;
import com.final_project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MoviePlayController {

    @Autowired
    MoviePlayRepository moviePlayRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    TicketRepository ticketRepository;

    @GetMapping ("/all-movie-plays")
    public String  getAllMoviePlays(Model model) {
        List<MoviePlay> moviePlayList = moviePlayRepository.findAllMoviePlays();
        List<Movie> movieList = movieRepository.findAllMovies();

        model.addAttribute("plays", moviePlayList);
        model.addAttribute("movies", movieList);

        return "movie-play/all-movie-plays";
    }

    @GetMapping ("/manage/add-movie-play")
    public String getAddMoviePlay(@ModelAttribute MoviePlay moviePlay, Model model) {
        List<Movie> movieList = movieRepository.findAllMovies();
        List<Theater> theaterList = theaterRepository.findAllTheaters();

        model.addAttribute("moviePlay", moviePlay);
        model.addAttribute("movies", movieList);
        model.addAttribute("theaters", theaterList);

        return "movie-play/add-movie-play";
    }

    @PostMapping ("/manage/add-movie-play")
    public String addMoviePlay(@ModelAttribute @Valid MoviePlay moviePlay, Errors errors) {

        // Check if errors - errors are set with validation constraints.
        if(errors.hasErrors()) {
            return "redirect:/manage/add-movie-play";
        }

        moviePlayRepository.addMoviePlay(moviePlay);
        return "redirect:/all-movie-plays";
    }

    @PostMapping ("/manage/delete-movie-play/{id}")
    public String deleteMoviePlay(@PathVariable(name ="id") int id) {
        List<Booking> bookingsToDelete = bookingRepository.findBookingsByMoviePlayId(id);
        List<Ticket> ticketsToDelete = ticketRepository.findTicketsByMoviePlayId(id);

        // When deleting a movie_play you also have to delete all the bookings
        // and tickets associated to the movie play
        for(Ticket ticket : ticketsToDelete) {
            ticketRepository.deleteTicket(ticket.getId());
        }

        // Same goes for bookings
        for(Booking booking : bookingsToDelete) {
            bookingRepository.deleteBooking(booking.getBookingCode());
        }

        moviePlayRepository.deleteMoviePlay(id);
        return "redirect:/all-movie-plays";
    }

    @GetMapping ("/manage/edit-movie-play/{id}")
    public String getEditMoviePlay(@PathVariable (name = "id") int id, Model model) {
        MoviePlay playToEdit = moviePlayRepository.findMoviePlayById(id);

        List<Movie> movieList = movieRepository.findAllMovies();
        List<Theater> theaterList = theaterRepository.findAllTheaters();

        model.addAttribute("movies", movieList);
        model.addAttribute("theaters", theaterList);
        model.addAttribute("moviePlay",playToEdit);

        return "movie-play/edit-movie-play";
    }

    @PostMapping ("/edit-movie-play")
    public String editMoviePlay(@ModelAttribute MoviePlay moviePlay) {
        moviePlayRepository.editMoviePlay(moviePlay);
        return "redirect:/all-movie-plays";
    }


}