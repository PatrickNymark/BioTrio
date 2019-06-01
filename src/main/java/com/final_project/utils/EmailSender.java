package com.final_project.services;

import com.final_project.entities.Booking;
import com.final_project.entities.Ticket;
import com.final_project.repositories.BookingRepository;
import com.final_project.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;


    public void sendConfirmationMail(Booking booking, List<Ticket> tickets, String toEmail)  throws MessagingException {

        final Context ctx = new Context();
        ctx.setVariable("booking", booking);
        ctx.setVariable("tickets", tickets);

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        final MimeMessageHelper message =
                new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
        message.setSubject("Booking Confirmation");
        message.setFrom("biotrio@booking.com");
        message.setTo(toEmail);

        System.out.println("to == " + toEmail);

        // Create the HTML body using Thymeleaf
        final String htmlContent = templateEngine.process("emails/confirmation", ctx);

        message.setText(htmlContent, true); // true = isHtml

        // Send mail
        javaMailSender.send(mimeMessage);
    }

}
