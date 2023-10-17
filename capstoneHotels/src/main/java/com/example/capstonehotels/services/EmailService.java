package com.example.capstonehotels.services;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmail(String recipientEmail, String name, String bookingId) throws MessagingException;
    void sendEmailForCancellingBooking(String recipientEmail, String name, String bookingId) throws MessagingException;
}
