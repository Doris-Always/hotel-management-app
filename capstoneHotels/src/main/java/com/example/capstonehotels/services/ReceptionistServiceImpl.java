package com.example.capstonehotels.services;

import com.example.capstonehotels.data.model.CheckIn;
import com.example.capstonehotels.data.model.PaymentStatus;
import com.example.capstonehotels.data.model.RoomStatus;
import com.example.capstonehotels.data.repository.CheckInRepository;
import com.example.capstonehotels.dtos.request.BookingRequest;
import com.example.capstonehotels.dtos.request.CheckInRequest;
import com.example.capstonehotels.exception.CheckInException;
import com.example.capstonehotels.exception.RoomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReceptionistServiceImpl implements ReceptionistService {
    @Autowired
    private GuestService guestService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private CheckInRepository checkInRepository;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final DateTimeFormatter dateFormatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public List<CheckIn> findCheckInByDate(String date) {
        LocalDate searchedDate = LocalDate.parse(date, dateFormatter2);

        List<CheckIn> foundCheckIns = new ArrayList<>();
        for(CheckIn checkIn : findAllCheckIns()){
            if(checkIn.getCheckInDate().equals(searchedDate)){
                foundCheckIns.add(checkIn);
            }
        }

        if(searchedDate.isAfter(LocalDate.now())) throw new CheckInException("You can't search check-in for future date");
        if (foundCheckIns.size() == 0) throw new CheckInException("There was no customer that checked in on "+date);
        return foundCheckIns;
    }

    @Override
    public List<CheckIn> findAllCheckIns() {
        return checkInRepository.findAll();
    }

    @Override
    public String checkGuestIn(String bookingId, CheckInRequest checkInRequest) {
         var foundBooking = guestService.findBookingById(bookingId);
         var foundRoom = roomService.getRoomByRoomNumber(checkInRequest.getRoomNumber());
        LocalDate checkInDate = LocalDate.parse(checkInRequest.getCheckInDate(), dateFormatter);
        LocalTime checkInTime = LocalTime.parse(checkInRequest.getCheckInTime(), timeFormatter);
        CheckIn checkIn = new CheckIn(checkInDate, checkInTime, checkInRequest.getEmail(), checkInRequest.getFirstName(),
                checkInRequest.getLastName(), checkInRequest.getPhoneNumber(), checkInRequest.getRoomNumber());
        if(!checkInDate.equals(LocalDate.now())) throw new CheckInException("The check-in date is not correct, set today's date");
        if(!checkInTime.equals(LocalTime.now())) throw new CheckInException("incorrect time, set current time");
        if(foundRoom.getRoomStatus().equals(RoomStatus.BOOKED)) throw new RoomException("room with " +
                ""+checkInRequest.getRoomNumber()+" is not available for check-in");
        if(!foundBooking.getPaymentStatus().equals(PaymentStatus.PAYMENT_SUCCESSFUL)){
            throw new CheckInException("payment has not been made for this booking yet, pls make payment");
        } else {
            foundRoom.setRoomStatus(RoomStatus.BOOKED);
            roomService.saveRoom(foundRoom);
        }

        checkInRepository.save(checkIn);
        return "Guest is successfully checked in";
    }
}
