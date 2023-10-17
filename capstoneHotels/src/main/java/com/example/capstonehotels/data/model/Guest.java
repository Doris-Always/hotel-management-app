package com.example.capstonehotels.data.model;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Data
public class Guest {
    @Id
    private String guestId;
    private String firstName;
    private String lastName;
    private String telephoneNumber;
    private RoomType roomType;
    private String emailAddress;
    private String checkinDate;
    private String checkoutDate;
    private BigDecimal roomPrice;
    private PaymentStatus paymentStatus;
}
