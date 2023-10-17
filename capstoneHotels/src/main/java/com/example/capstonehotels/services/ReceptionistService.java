package com.example.capstonehotels.services;

import com.example.capstonehotels.data.model.CheckIn;
import com.example.capstonehotels.dtos.request.BookingRequest;
import com.example.capstonehotels.dtos.request.CheckInRequest;

import java.util.List;

public interface ReceptionistService {
    List<CheckIn> findCheckInByDate(String date);
    List<CheckIn> findAllCheckIns();
    String checkGuestIn(String bookingId, CheckInRequest checkInRequest);
}
