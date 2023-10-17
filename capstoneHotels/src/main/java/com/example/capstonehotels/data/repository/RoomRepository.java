package com.example.capstonehotels.data.repository;

import com.example.capstonehotels.data.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoomRepository extends MongoRepository<Room, String> {
    Optional<Room> findRoomByRoomNumber(String roomNumber);
}
