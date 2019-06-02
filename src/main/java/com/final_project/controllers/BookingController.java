package com.final_project.controllers;

import com.final_project.entities.*;
import com.final_project.repositories.*;
import com.final_project.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
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
    public String getChooseSeat(@PathVariable(name = "id") int moviePlayId, Model model, @RequestParam(value = "message", required = false) String message) {
        MoviePlay moviePlay = moviePlayRepository.findMoviePlayById(moviePlayId);
        Theater theater = theaterRepository.findTheaterById(moviePlay.getTheaterId());
        List<Ticket> tickets = ticketRepository.findTicketsByMoviePlayId(moviePlayId);

        List<Seat> theaterSeats = new ArrayList<>();

        // Loop number of rows
        for(int i = 0; i < theater.getNumberOfRows(); i++) {
            // Loop number of seats
            for(int j = 0; j < theater.getSeatsPerRow(); j++) {
                Seat seat = new Seat();

                seat.setRow(i + 1);
                seat.setNr(j + 1);
                seat.setReserved(false);

                // Check if any tickets exists with matching values, and set reserved status accordingly.
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
        model.addAttribute("message", message);

        return "booking/choose-seat";
    }

    @PostMapping("/booking/choose-seat/{id}")
    public String addBooking(@RequestBody(required = false) String seats, @PathVariable(name = "id") int moviePlayId, RedirectAttributes redirectAttributes) {
        // Check of not seats were checked, and then redirects with a message.
        if(seats == null) {
            redirectAttributes.addAttribute("message", "Please choose minimum 1 seat");
            return "redirect:/booking/choose-seat/" + moviePlayId;
        }

        List<Ticket> tickets = new ArrayList<>();

        // To get value for each seat we split the request body string seats since each checkbox value is split by &.
        String[] newSeats = seats.split("&");

        // Check if more than 4 seats were checked, and then redirects with a message.
        if(newSeats.length > 4) {
            redirectAttributes.addAttribute("message", "Please choose maximum 4 seats");
            return "redirect:/booking/choose-seat/" + moviePlayId;
        }


        for (int i = 0; i < newSeats.length; i++) {
            Ticket ticket = new Ticket();

            // Each checkbox contains a value that indicates if its checked
            // so to get only the input name which contains seat nr and row we split by =.
            String[] numberAndValue = newSeats[i].split("=");

            //  To get nr and row values we need to split by -.
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

        // Calls static method from Booking to generate a random code.
        booking.setBookingCode(Booking.generateBookingCode());

        bookingRepository.addBooking(booking);

        // For each ticket set the created booking code and add them to database.
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
        MoviePlay moviePlay = moviePlayRepository.findMoviePlayById(booking.getMoviePlayId());
        Movie movie = movieRepository.findMovieById(moviePlay.getMovieId());

        model.addAttribute("booking",booking);
        model.addAttribute("tickets", tickets);
        model.addAttribute("movie", movie);
        model.addAttribute("moviePlay", moviePlay);

        return "booking/booking-confirmation";
    }

    @PostMapping("/manage/delete-booking/{bookingCode}")
    public String deleteBooking(@PathVariable(name = "bookingCode") String bookingCode) {
        Booking bookingToDelete = bookingRepository.findBookingByBookingCode(bookingCode);

        // Since each ticket references a foreign key to the booking
        // we need to delete all the tickets before deleting booking.
       for(Ticket ticket : bookingToDelete.getTickets()) {
            ticketRepository.deleteTicket(ticket.getId());
        }

        bookingRepository.deleteBooking(bookingCode);

        return "redirect:/manage/all-bookings";
    }

    @GetMapping("/manage/booking/search")
    public String getSearchBooking() {
        return "booking/find-booking";
    }

    @PostMapping("/manage/bookings/search")
    public String searchBookings(Model model, @RequestParam(name = "search", required = false) String searchValue) {
        Booking booking = bookingRepository.findBookingByBookingCode(searchValue);
        MoviePlay moviePlay = moviePlayRepository.findMoviePlayById(booking.getMoviePlayId());
        Movie movie = movieRepository.findMovieById(moviePlay.getMovieId());

        model.addAttribute("moviePlay", moviePlay);
        model.addAttribute("booking", booking);
        model.addAttribute("movie", movie);
        return "booking/find-booking";
    }
}
