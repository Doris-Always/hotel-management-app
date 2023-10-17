package com.example.capstonehotels.dtos.response;

import com.example.capstonehotels.utils.RecipientData;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class PaymentResponse {
    private HttpStatus statusCode;
    private String message;
    private RecipientData data;
}
