package com.final_project.controllers;

import com.final_project.entities.Booking;
import com.final_project.entities.MoviePlay;
import com.final_project.entities.Seat;
import com.final_project.entities.Theater;
import com.final_project.repositories.BookingRepository;
import com.final_project.repositories.MoviePlayRepository;
import com.final_project.repositories.SeatRepository;
import com.final_project.repositories.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    MoviePlayRepository moviePlayRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @GetMapping("/booking/choose-seat")
    public String getBookingChooseSeat(int moviePlayId, Model model) {

        return "choose-seat";
    }


}
