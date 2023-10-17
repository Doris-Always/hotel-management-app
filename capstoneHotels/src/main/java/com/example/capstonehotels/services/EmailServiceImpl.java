package com.example.capstonehotels.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String recipientEmail, String name, String bookingId) throws MessagingException {
        MimeMessage message =javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("pelumijsh@gmail.com");
        messageHelper.setTo(recipientEmail);
        String subject = "ROOM RESERVATION";
        String content = "Hello" + " " + name + ","
                + "<p>Thank you for making a reservation with us!!!<p/>"
                + "<p>Your room reservation request was successful. "
                + "Here is the bookingId: <p/>" + bookingId
                + "<p>Kindly keep this carefully as proof of payment whenever "
                + "you want to checkin at the receptionist desk or if you want to cancel your reservation";
        messageHelper.setSubject(subject);
        messageHelper.setText(content, true);
        javaMailSender.send(message);
    }

    @Override
    public void sendEmailForCancellingBooking(String recipientEmail, String name, String bookingId) throws MessagingException {

        MimeMessage message =javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("oladejomubarakade@gmail.com");
        messageHelper.setTo(recipientEmail);
        String subject = "CANCEL BOOKING";
        String content = "Dear" + " " + name + ","
                + "<p>Your booking with the id "+bookingId+" has been canceled successfully<p/>"
                + "<p>Kindly reach out to us as soon as possible if you didn't initiate that. "
                + "Thank you! God bless you!";
        messageHelper.setSubject(subject);
        messageHelper.setText(content, true);
        javaMailSender.send(message);
    }
}
