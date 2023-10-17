package com.example.capstonehotels.dtos.request;

import com.example.capstonehotels.data.model.RoomStatus;
import com.example.capstonehotels.data.model.RoomType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddRoomRequest {
    @NotBlank(message = "This field must be filled")
    private String roomNumber;
    @NotBlank(message = "This field must be filled")
    private RoomType roomType;
    @NotBlank(message = "This field must be filled")
    private BigDecimal roomPrice;
    @NotBlank(message = "This field must be filled")
    private RoomStatus roomStatus;
}
