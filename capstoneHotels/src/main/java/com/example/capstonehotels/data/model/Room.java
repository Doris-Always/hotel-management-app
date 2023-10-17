package com.example.capstonehotels.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@NoArgsConstructor
@Document
@Data
public class Room {
        @Id
        private String roomId;
        private String roomNumber;
        private RoomType roomType;
        private BigDecimal roomPrice;
        private RoomStatus roomStatus;

        public Room(String roomNumber, RoomType roomType, BigDecimal roomPrice, RoomStatus roomStatus) {
            this.roomNumber = roomNumber;
            this.roomType = roomType;
            this.roomPrice = roomPrice;
            this.roomStatus = roomStatus;
        }

}
