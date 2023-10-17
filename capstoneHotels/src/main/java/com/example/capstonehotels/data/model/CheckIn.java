package com.example.capstonehotels.data.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Document
@RequiredArgsConstructor
public class CheckIn {
    @Id
    private String checkInId;
    //private PaymentStatus paymentStatus;
    private LocalDate checkInDate;
    private LocalTime checkInTime;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String roomNumber;

    public CheckIn(LocalDate checkInDate, LocalTime checkInTime, String email, String firstName,
                   String lastName, String phoneNumber, String roomNumber) {
        this.checkInDate = checkInDate;
        this.checkInTime = checkInTime;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.roomNumber= roomNumber;
    }
}
