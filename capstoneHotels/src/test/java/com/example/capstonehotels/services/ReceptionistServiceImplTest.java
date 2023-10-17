package com.example.capstonehotels.services;

import com.example.capstonehotels.data.model.PaymentStatus;
import com.example.capstonehotels.dtos.request.BookingRequest;
import com.example.capstonehotels.dtos.request.CheckInRequest;
import com.example.capstonehotels.exception.CheckInException;
import com.example.capstonehotels.exception.RoomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReceptionistServiceImplTest {
    @Autowired
    private ReceptionistService receptionistService;

    private CheckInRequest checkInRequest;
    private CheckInRequest checkInRequest1;
    private CheckInRequest checkInRequest2;
    private CheckInRequest checkInRequest3;
    private BookingRequest bookingRequest;
    private BookingRequest bookingRequest1;

    @BeforeEach
    void setUp() {
        checkInRequest = new CheckInRequest();
        checkInRequest.setCheckInTime("08:01");
        checkInRequest.setCheckInDate("02/04/2023");
        checkInRequest.setEmail("olade@gmail.com");
        checkInRequest.setFirstName("Ola");
        checkInRequest.setLastName("Mub");
        checkInRequest.setPhoneNumber("Ade");
        checkInRequest.setRoomNumber("206");

        checkInRequest1 = new CheckInRequest();
        checkInRequest1.setCheckInDate("02/04/2023");
        checkInRequest1.setCheckInTime("22:21");
        checkInRequest1.setEmail("olade@gmail.com");
        checkInRequest1.setFirstName("Ola");
        checkInRequest1.setLastName("Mub");
        checkInRequest1.setPhoneNumber("Ade");
        checkInRequest1.setRoomNumber("116");

        checkInRequest2 = new CheckInRequest();
        checkInRequest2.setCheckInDate("02/04/2023");
        checkInRequest2.setCheckInTime("22:21");
        checkInRequest2.setEmail("olade@gmail.com");
        checkInRequest2.setFirstName("Ola");
        checkInRequest2.setLastName("Mub");
        checkInRequest2.setPhoneNumber("Ade");
        checkInRequest2.setRoomNumber("100000");


        checkInRequest3= new CheckInRequest();
        checkInRequest3.setCheckInTime("08:01");
        checkInRequest3.setCheckInDate("03/04/2023");
        checkInRequest3.setEmail("olade@gmail.com");
        checkInRequest3.setFirstName("Ola");
        checkInRequest3.setLastName("Mub");
        checkInRequest3.setPhoneNumber("Ade");
        checkInRequest3.setRoomNumber("111");

        bookingRequest = new BookingRequest();
        bookingRequest.setGuestId("642997f98799253df8ee0094");

        bookingRequest1 = new BookingRequest();
        bookingRequest1.setGuestId("6429972a8de6ab1c55e2781a");

    }

    @Test void testThatCustomerCanBeCheckedIn(){
        String bookingId = "642997f98799253df8ee0094";
        String checkIn = receptionistService.checkGuestIn(bookingId, checkInRequest);
        assertEquals("Guest is successfully checked in", checkIn);
    }

    @Test void testThatBookedRoomCantBeCheckedInto(){
        String bookingId = "642997f98799253df8ee0094";
        //String checkIn = receptionistService.checkGuestIn(bookingRequest, checkInRequest);
        //assertEquals("Guest is successfully checked in", checkIn);
        assertThrows(RoomException.class,()-> receptionistService.checkGuestIn(bookingId, checkInRequest));
    }
    @Test void testThatIfCustomerWantToCheckInWithBookingOfUnconfirmedPayment_throwsException(){
        String bookingId1 = "6429972a8de6ab1c55e2781a";
//        String checkIn = receptionistService.checkGuestIn(bookingRequest1, checkInRequest1);
//        assertEquals("Guest is successfully checked in", checkIn);
        assertThrows(CheckInException.class, ()-> receptionistService.checkGuestIn(bookingId1, checkInRequest1));

    }
    @Test void testThatNonExistingRoomCantBeCheckedInto(){
        String bookingId = "642997f98799253df8ee0094";
//        String checkIn = receptionistService.checkGuestIn(bookingRequest, checkInRequest2);
//        assertEquals("Guest is successfully checked in", checkIn);
        assertThrows(RoomException.class, ()-> receptionistService.checkGuestIn(bookingId, checkInRequest2));

    }
    @Test void testThatOnlyPresentDateCanBeSetForCheckIn(){
        String bookingId = "642997f98799253df8ee0094";
//        String checkIn = receptionistService.checkGuestIn(bookingRequest, checkInRequest3);
//        assertEquals("Guest is successfully checked in", checkIn);
        assertThrows(CheckInException.class, ()-> receptionistService.checkGuestIn(bookingId, checkInRequest3));

    }
    @Test void testThatReceptionistCanSearchForCheckinByDate(){
        var foundCheckIn = receptionistService.findCheckInByDate("02-04-2023");
     assertEquals(3, (long) foundCheckIn.size());
    }
    @Test void testThatSearchingCheckInsForFutureDateThrowsException(){
//        var foundCheckIn = receptionistService.findCheckInByDate("12-12-2025");
//        assertEquals(3, (long) foundCheckIn.size());
        assertThrows(CheckInException.class, ()-> receptionistService.findCheckInByDate("12-12-2025"));
    }
    @Test void testThatOnlyCurrentTimeCanBeSetForCheckIn(){

    }
}