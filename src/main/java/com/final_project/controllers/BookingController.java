package com.final_project.controllers;

import com.final_project.entities.*;
import com.final_project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookingController {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    MoviePlayRepository moviePlayRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    TheaterRepository theaterRepository;


    /*
        CREATE BOOKING
     */

    @GetMapping("/booking/choose-seat/{id}")
    public String getBookingChooseSeat(@PathVariable(name = "id") int moviePlayId, Model model) {
        MoviePlay moviePlay = moviePlayRepository.getMoviePlayById(moviePlayId);
        Theater theater = theaterRepository.findTheaterById(moviePlay.getTheaterId());

        List<Ticket> tickets = ticketRepository.getTicketsByMoviePlayId(moviePlayId);

        List<Seat> theaterSeats = new ArrayList<>();

        for(int i = 0; i < theater.getNumberOfRows(); i++) {
            for(int j = 0; j < theater.getSeatsPerRow(); j++) {
                Seat seat = new Seat();

                seat.setRow(i + 1);
                seat.setNr(j + 1);
                seat.setReserved(false);

                for(Ticket ticket : tickets) {
                    if(ticket.getSeatNr() == seat.getNr() && ticket.getSeatRow() == seat.getRow()) {
                        seat.setReserved(true);
                    }
                }

                theaterSeats.add(seat);
            }
        }

        model.addAttribute("seats", theaterSeats);
        model.addAttribute("rows", theater.getNumberOfRows());
        model.addAttribute("seatsPrRow", theater.getSeatsPerRow());

        return "booking/choose-seat";
    }

    @PostMapping("/booking/choose-seat")
    public String addBooking(@RequestBody String seats, @RequestParam("playId") int playId, @RequestParam(value = "action") String action) {
        return "redirect:/booking/confirmation";

    }

    @GetMapping("/booking/confirmation")
    public String bookingConfirmation() {

        return "booking/booking-confirmation";
    }
}
