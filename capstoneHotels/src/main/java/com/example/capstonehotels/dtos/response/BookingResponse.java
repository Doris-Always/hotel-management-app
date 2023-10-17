package com.example.capstonehotels.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingResponse {
    private String guestId;
    private int statusCode;
    private String message;
}
