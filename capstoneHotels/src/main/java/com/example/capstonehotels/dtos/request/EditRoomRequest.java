package com.example.capstonehotels.dtos.request;

import com.example.capstonehotels.data.model.RoomStatus;
import com.example.capstonehotels.data.model.RoomType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EditRoomRequest {
    private String roomNumber;
    private RoomType roomType;
    private BigDecimal roomPrice;
    private RoomStatus roomStatus;
}
