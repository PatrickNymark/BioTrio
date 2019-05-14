package com.final_project.controllers;

import com.final_project.entities.Seat;
import com.final_project.repositories.BookingRepository;
import com.final_project.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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


}
