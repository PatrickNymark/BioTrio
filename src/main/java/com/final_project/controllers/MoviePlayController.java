package com.final_project.controllers;

import com.final_project.entities.*;
import com.final_project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
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

    /**
     * Method retrieves all movie plays
     *
     * @param model
     * @return String
     */
    @GetMapping ("/all-movie-plays")
    public String  getAllMoviePlays(Model model) {
        List<MoviePlay> moviePlayList = moviePlayRepository.findAllMoviePlays();
        List<Movie> movieList = movieRepository.findAllMovies();

        model.addAttribute("plays", moviePlayList);
        model.addAttribute("movies", movieList);

        return "movie-play/all-movie-plays";
    }

    /**
     * Method retrieves single movie play
     *
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/movie-play/{id}")
    public String getMoviePlay(@PathVariable(name = "id") int id, Model model) {
        MoviePlay moviePlay = moviePlayRepository.findMoviePlayById(id);
        Theater theater = theaterRepository.findTheaterById(moviePlay.getTheaterId());
        Movie movie = movieRepository.findMovieById(moviePlay.getMovieId());

        List<Ticket> tickets = ticketRepository.findTicketsByMoviePlayId(moviePlay.getId());

        model.addAttribute("movie", movie);
        model.addAttribute("theater", theater);
        model.addAttribute("moviePlay", moviePlay);
        model.addAttribute("tickets", tickets);

        return "movie-play/movie-play-page";
    }

    /**
     * Method takes a start and end date, and then retrieves all movie-plays within data range
     *
     * @param playStart
     * @param playEnd
     * @param model
     * @return String
     */
    @PostMapping("/movie-play/search")
    public String getMoviePlaysWithinDates(@RequestParam(name = "playStart") String playStart, @RequestParam(name = "playEnd") String playEnd, Model model) {
        List<MoviePlay> moviePlays = moviePlayRepository.findMoviePlaysWithinDate(playStart, playEnd);
        List<Movie> movieList = movieRepository.findAllMovies();

        model.addAttribute("moviePlays", moviePlays);
        model.addAttribute("movies", movieList);
        return "movie-play/sorted-movie-plays";
    }

    /**
     * Method retrieves add movie form
     *
     * @param moviePlay
     * @param model
     * @param message
     * @return String
     */
    @GetMapping ("/manage/add-movie-play")
    public String getAddMoviePlay(@ModelAttribute MoviePlay moviePlay, Model model, @RequestParam(value = "message", required = false) String message) {
        List<Movie> movieList = movieRepository.findAllMovies();
        List<Theater> theaterList = theaterRepository.findAllTheaters();

        model.addAttribute("moviePlay", moviePlay);
        model.addAttribute("movies", movieList);
        model.addAttribute("theaters", theaterList);
        model.addAttribute("message", message);

        return "movie-play/add-movie-play";
    };

    /**
     * Method takes a movie play and saves it to the database
     *
     * @param moviePlay
     * @param redirectAttributes
     * @return
     */
    @PostMapping ("/manage/add-movie-play")
    public String addMoviePlay(@ModelAttribute MoviePlay moviePlay, RedirectAttributes redirectAttributes) {
        if(moviePlay.getPlayStart() == null) {
            redirectAttributes.addAttribute("message", "Play date cannot be empty");
            return "redirect:/manage/add-movie-play";
        }

        // Calculate play end
        LocalDateTime endDate = calculateEndTime(moviePlay.getMovieId(), moviePlay.getPlayStart());

        // Set play end
        moviePlay.setPlayEnd(endDate);

        if(!checkAvailability(moviePlay.getTheaterId(), moviePlay)) {
            redirectAttributes.addAttribute("message", "Theater is occupied at given time");
            return "redirect:/manage/add-movie-play";
        }

        moviePlayRepository.addMoviePlay(moviePlay);
        return "redirect:/all-movie-plays";
    }

    /**
     * Method takes a path variable and then deletes record from database
     *
     * @param id
     * @return
     */
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

    /**
     * Method retrieves edit movie form
     *
     * @param id
     * @param message
     * @param model
     * @return
     */
    @GetMapping ("/manage/edit-movie-play/{id}")
    public String getEditMoviePlay(@PathVariable (name = "id") int id, @RequestParam(name = "message", required = false) String message, Model model) {
        MoviePlay playToEdit = moviePlayRepository.findMoviePlayById(id);

        List<Movie> movieList = movieRepository.findAllMovies();
        List<Theater> theaterList = theaterRepository.findAllTheaters();

        model.addAttribute("movies", movieList);
        model.addAttribute("theaters", theaterList);
        model.addAttribute("moviePlay",playToEdit);
        model.addAttribute("message", message);

        return "movie-play/edit-movie-play";
    }

    /**
     * Methods takes a movie play and then updates database record
     *
     * @param moviePlay
     * @param redirectAttributes
     * @return
     */
    @PostMapping ("/manage/edit-movie-play")
    public String editMoviePlay(@ModelAttribute MoviePlay moviePlay, RedirectAttributes redirectAttributes) {

        // Set play end
        moviePlay.setPlayEnd(calculateEndTime(moviePlay.getMovieId(), moviePlay.getPlayStart()));

        // Get movie plays by the theater id
        if(!checkAvailability(moviePlay.getTheaterId(), moviePlay)) {
            redirectAttributes.addAttribute("message", "Theater is occupied at given time");
            return "redirect:/manage/edit-movie-play/" + moviePlay.getId();
        }

        moviePlayRepository.editMoviePlay(moviePlay);
        return "redirect:/all-movie-plays";
    }


    /**
     * Private method to calculate play end time
     *
     * @param movieId
     * @param dateStart
     * @return LocalDateTime
     */
    private LocalDateTime calculateEndTime(int movieId, LocalDateTime dateStart) {
        Movie movie = movieRepository.findMovieById(movieId);

        // Get movie length + 15 min margin for preparation
        int minutes = movie.getLengthInMinutes() + 30;

        // Calculate play end
        LocalDateTime endDate = dateStart.plusMinutes(minutes);

        return endDate;
    }

    /**
     * Private method to check if movie play to create overlap
     * any plays in the particular theater
     *
     * @param theaterId
     * @param moviePlay
     * @return boolean
     */
    private boolean checkAvailability(int theaterId, MoviePlay moviePlay) {
        List<MoviePlay> moviePlayList = moviePlayRepository.findMoviePlaysByTheaterId(theaterId);

        // Iterate each movie play
        for(MoviePlay play : moviePlayList) {
            // Check if dates overlap
            if((play.getPlayStart().isBefore(moviePlay.getPlayEnd())) && (moviePlay.getPlayStart().isBefore(play.getPlayEnd()))) {
                return false;
            } else if (play.getPlayStart().isEqual(moviePlay.getPlayStart())) {
                return false;
            }
        }

        return true;
    }
}