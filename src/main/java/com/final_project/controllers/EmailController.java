package com.final_project.controllers;

import com.final_project.entities.Booking;
import com.final_project.entities.Ticket;
import com.final_project.repositories.BookingRepository;
import com.final_project.repositories.TicketRepository;
import com.final_project.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.util.List;

@Controller
public class EmailController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    EmailSender emailSender;

    /**
     * Method takes a email and a booking code, which is uses
     * to send confirmation email.
     *
     * @param bookingCode
     * @param email
     * @return String
     */
    @PostMapping("/booking/send-booking-confirmation")
    public String sendBookingConfirmation(@RequestParam("bookingCode") String bookingCode, @RequestParam("email") String email) {
        Booking booking = bookingRepository.findBookingByBookingCode(bookingCode);
        List<Ticket> tickets = ticketRepository.findTicketsByBookingCode(bookingCode);

        try {
            // Sends confirmation email with provided info
            emailSender.sendConfirmationMail(booking, tickets, email);
            return "booking/booking-send";
        } catch (MessagingException e) {
            return "redirect:/all-movies";
        }
    }
}
