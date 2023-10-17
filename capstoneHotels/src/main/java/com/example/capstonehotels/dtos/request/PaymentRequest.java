package com.example.capstonehotels.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
    @NotBlank(message = "This field is required")
    private String name;
    @NotBlank(message = "This field is required")
    private String description;
    @NotBlank(message = "This field is required")
    private BigDecimal amount;
}
