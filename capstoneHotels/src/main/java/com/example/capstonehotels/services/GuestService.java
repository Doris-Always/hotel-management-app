package com.example.capstonehotels.services;

import com.example.capstonehotels.data.model.Guest;
import com.example.capstonehotels.dtos.request.BookRoomRequest;
import com.example.capstonehotels.dtos.request.BookingRequest;
import com.example.capstonehotels.dtos.request.PaymentRequest;
import com.example.capstonehotels.dtos.response.BookingResponse;
import com.example.capstonehotels.dtos.response.PaymentResponse;
import com.example.capstonehotels.dtos.response.Response;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.List;

public interface GuestService {
    BookingResponse makeRoomReservation(BookRoomRequest bookRoomRequest);

//    PaymentResponse makePayment(String telephoneNumber, PaymentRequest paymentRequest) throws IOException;
    Response makePayment(String telephoneNumber, PaymentRequest paymentRequest) throws IOException, MessagingException;

    Guest findBookingById(String guestId);

    Response cancelBooking(String guestId) throws MessagingException;
}
