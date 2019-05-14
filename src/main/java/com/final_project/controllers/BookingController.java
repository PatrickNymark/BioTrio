package com.final_project.controllers;

import com.final_project.entities.Booking;
import com.final_project.entities.Seat;
import com.final_project.repositories.BookingRepository;
import com.final_project.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    SeatRepository seatRepository;

    @GetMapping("/booking/choose-seat/{moviePlayId}")
    public String getBookingChooseSeat(@PathVariable(name = "moviePlayId") int moviePlayId, Model model) {
        List<Seat> seatsList = seatRepository.getSeatsByMoviePlay(moviePlayId);

        model.addAttribute("seats", seatsList);
        return "choose-seat";
    }

    @PostMapping("/booking/choose-seat/{moviePlayId}")
    public String addBooking(
            @PathVariable(name = "moviePlayId") int moviePlayId,
            @RequestParam("seatRow") int seatRow,
            @RequestParam("seatNr") int seatNr) {

        Booking booking = new Booking();

        booking.setMoviePlayId(moviePlayId);
        booking.setSeatNr(seatNr);
        booking.setSeatRow(seatRow);

        bookingRepository.addBooking(booking);

        return "redirect:/";
    }
}