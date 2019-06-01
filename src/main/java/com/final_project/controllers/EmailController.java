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

    @PostMapping("/booking/send-booking-confirmation")
    public String sendBookingConfirmation(@RequestParam("bookingCode") String bookingCode, @RequestParam("email") String email) {
        Booking booking = bookingRepository.findBookingByBookingCode(bookingCode);
        List<Ticket> tickets = ticketRepository.findTicketsByBookingCode(bookingCode);

        try {
            emailSender.sendConfirmationMail(booking, tickets, email);
            return "redirect:/booking/confirmation/"  + bookingCode;
        } catch (MessagingException e) {
            return "redirect:/all-movies";
        }
    }
}
