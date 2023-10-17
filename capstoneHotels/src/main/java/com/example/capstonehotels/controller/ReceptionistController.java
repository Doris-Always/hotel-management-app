package com.example.capstonehotels.controller;

import com.example.capstonehotels.data.model.CheckIn;
import com.example.capstonehotels.data.model.Room;
import com.example.capstonehotels.dtos.request.BookingRequest;
import com.example.capstonehotels.dtos.request.CheckInRequest;
import com.example.capstonehotels.services.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/receptionist")
@CrossOrigin(origins = "*")
public class ReceptionistController {
    @Autowired
    private ReceptionistService receptionistService;

    @PostMapping("/check-guest-in/{bookingId}")
    public ResponseEntity<?> checkGuestIn(@PathVariable String bookingId, @RequestBody CheckInRequest checkInRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(receptionistService.checkGuestIn(bookingId, checkInRequest));
    }
    @GetMapping("/find-check-ins-by-date/{date}")
    public ResponseEntity<?> findCheckInByDate(@PathVariable String date){
        return ResponseEntity.ok(receptionistService.findCheckInByDate(date));
    }
    @GetMapping("/find-all-check-ins")
    public ResponseEntity<?> findAllCheckIns(){
        return ResponseEntity.ok(receptionistService.findAllCheckIns());
    }

}
