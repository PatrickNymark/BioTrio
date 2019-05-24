package com.final_project.controllers;

import com.final_project.entities.*;
import com.final_project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    MovieRepository movieRepository;


    @GetMapping("/manage/all-bookings")
    public String getAllBookings(Model model) {
        List<Booking> bookings = bookingRepository.findAllBookings();

        model.addAttribute("bookings", bookings);

        return "booking/all-bookings";
    }

    @GetMapping("/booking/choose-seat/{id}")
    public String getChooseSeat(@PathVariable(name = "id") int moviePlayId, Model model) {
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

        model.addAttribute("moviePlayId", moviePlayId);
        model.addAttribute("seats", theaterSeats);
        model.addAttribute("rows", theater.getNumberOfRows());
        model.addAttribute("seatsPrRow", theater.getSeatsPerRow());

        return "booking/choose-seat";
    }

    @PostMapping("/booking/choose-seat/{id}")
    public String addBooking(@RequestBody String seats, @PathVariable(name = "id") int moviePlayId) {
        List<Ticket> tickets = new ArrayList<>();

        String[] newSeats = seats.split("&");

        for (int i = 0; i < newSeats.length; i++) {
            Ticket ticket = new Ticket();

            String[] numberAndValue = newSeats[i].split("=");
            String[] seatNumbers = numberAndValue[0].split("-");

            ticket.setSeatRow(Integer.parseInt(seatNumbers[0]));
            ticket.setSeatNr(Integer.parseInt(seatNumbers[1]));
            ticket.setMoviePlayId(moviePlayId);
            tickets.add(ticket);
        }

        Booking booking = new Booking();

        booking.setMoviePlayId(moviePlayId);
        booking.setTickets(tickets);
        booking.setTotalPrice(150);
        booking.setBookingCode(Booking.generateBookingCode());

        bookingRepository.addBooking(booking);

        for(Ticket ticket : tickets) {
            ticket.setBookingCode(booking.getBookingCode());
            ticketRepository.addTicket(ticket);
        }

        return "redirect:/booking/confirmation/"  + booking.getBookingCode();

    }

    @GetMapping("/booking/confirmation/{bookingCode}")
    public String getBookingConfirmation(@PathVariable(name = "bookingCode") String bookingCode, Model model) {
        Booking booking = bookingRepository.findBookingByBookingCode(bookingCode);
        List<Ticket> tickets = ticketRepository.findTicketsByBookingCode(bookingCode);
        MoviePlay moviePlay = moviePlayRepository.getMoviePlayById(booking.getMoviePlayId());
        Movie movie = movieRepository.findMovieById(moviePlay.getMovieId());

        model.addAttribute("booking",booking);
        model.addAttribute("tickets", tickets);
        model.addAttribute("movie", movie);
        model.addAttribute("moviePlay", moviePlay);

        return "booking/booking-confirmation";
    }
}
