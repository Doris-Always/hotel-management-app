package com.example.capstonehotels.controller;

import com.example.capstonehotels.dtos.request.BookRoomRequest;
import com.example.capstonehotels.dtos.request.BookingRequest;
import com.example.capstonehotels.dtos.request.PaymentRequest;
import com.example.capstonehotels.dtos.response.ApiResponse;
import com.example.capstonehotels.services.GuestService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/capstoneHotels")
@CrossOrigin(origins = "*")
public class GuestController {

    private final GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping("/bookRoom")
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> makeRoomsReservations(@RequestBody BookRoomRequest bookRoomRequest, HttpServletRequest httpServletRequest) {
        var bookRoom = guestService.makeRoomReservation(bookRoomRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .statusCode(HttpStatus.OK)
                .path(httpServletRequest.getRequestURI())
                .timeStamp(ZonedDateTime.now())
                .data(bookRoom)
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/makePayment/{telephoneNumber}")
    public ResponseEntity<?> makePayment(@PathVariable String telephoneNumber, @RequestBody PaymentRequest paymentRequest,
                                         HttpServletRequest httpServletRequest) throws IOException, MessagingException {
        var payment = guestService.makePayment(telephoneNumber, paymentRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .statusCode(HttpStatus.OK)
                .path(httpServletRequest.getRequestURI())
                .timeStamp(ZonedDateTime.now())
                .data(payment)
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("findBookingById/{guestId}")
    public ResponseEntity<?> findBooking(@PathVariable String guestId, HttpServletRequest httpServletRequest) {
        ApiResponse apiResponse = ApiResponse.builder()
                .statusCode(HttpStatus.OK)
                .path(httpServletRequest.getRequestURI())
                .timeStamp(ZonedDateTime.now())
                .data(guestService.findBookingById(guestId))
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("cancelBooking/{guestId}")
    public ResponseEntity<?> cancelBooking(@PathVariable String guestId, HttpServletRequest httpServletRequest)
            throws MessagingException {
        ApiResponse apiResponse = ApiResponse.builder()
                .statusCode(HttpStatus.OK)
                .path(httpServletRequest.getRequestURI())
                .timeStamp(ZonedDateTime.now())
                .data(guestService.cancelBooking(guestId))
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
