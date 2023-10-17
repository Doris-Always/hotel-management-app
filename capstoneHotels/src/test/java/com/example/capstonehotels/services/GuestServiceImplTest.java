package com.example.capstonehotels.services;

import com.example.capstonehotels.data.model.RoomType;
import com.example.capstonehotels.dtos.request.BookRoomRequest;
import com.example.capstonehotels.dtos.response.BookingResponse;
import com.example.capstonehotels.dtos.response.Response;
import com.example.capstonehotels.utils.exceptions.CapstoneException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestServiceImplTest {

    @Autowired
    private GuestService guestService;

    @Test
    void testThatGuestCanBookRoom() {
        BookRoomRequest bookRoomRequest = new BookRoomRequest();
        bookRoomRequest.setRoomType(String.valueOf(RoomType.SINGLE));
        bookRoomRequest.setLastName("Jesus");
        bookRoomRequest.setFirstName("Christ");
        bookRoomRequest.setCheckinDate("09-04-2023");
        bookRoomRequest.setCheckoutDate("20-04-2023");
        bookRoomRequest.setTelephoneNumber("08146093346");
        bookRoomRequest.setEmailAddress("mrjesus3003@gmail.com");
        BookingResponse response = guestService.makeRoomReservation(bookRoomRequest);
        System.out.println(response.getGuestId());
        assertEquals("Your room has been booked successfully, Kindly proceed to " +
                "the payment section", response.getMessage());
    }

    @Test
    void testThatGuestCanBookRoomThrowsException() {
        BookRoomRequest bookRoomRequest = new BookRoomRequest();
        bookRoomRequest.setRoomType(String.valueOf(RoomType.SINGLE));
        bookRoomRequest.setLastName("DrinkWater");
        bookRoomRequest.setFirstName("Samuel");
        bookRoomRequest.setCheckinDate("28-03-2023");
        bookRoomRequest.setCheckoutDate("30-03-2023");
        bookRoomRequest.setTelephoneNumber("+234 (563) 450-1257");
        bookRoomRequest.setEmailAddress("dsfdadfdfds");
        assertThrows(CapstoneException.class, () -> guestService.makeRoomReservation(bookRoomRequest));
    }

    @Test
    void testThatGuestCanCancelBooking() {
        Response cancelBookingResponse = guestService.cancelBooking("641e8f40e68a40544d6a9fcf");
        assertEquals("Your booking has been cancelled", cancelBookingResponse.getMessage());
    }
}