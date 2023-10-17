package com.example.capstonehotels.dtos.request;

import com.example.capstonehotels.data.model.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CheckInRequest {
    //private PaymentStatus paymentStatus;
    @NotBlank(message = "this field must be filled")
    private String checkInTime;
    @NotBlank(message = "this field must be filled")
    private String checkInDate;
    @NotBlank(message = "this field must be filled")
    private String email;
    @NotBlank(message = "this field must be filled")
    private String FirstName;
    @NotBlank(message = "this field must be filled")
    private String LastName;
    @NotBlank(message = "this field must be filled")
    private String phoneNumber;
    @NotBlank(message = "this field must be filled")
    private String roomNumber;
}
