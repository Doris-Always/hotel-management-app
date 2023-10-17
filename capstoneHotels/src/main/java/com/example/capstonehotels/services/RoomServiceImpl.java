package com.example.capstonehotels.services;

import com.example.capstonehotels.data.model.Room;
import com.example.capstonehotels.data.model.RoomStatus;
import com.example.capstonehotels.data.repository.RoomRepository;
import com.example.capstonehotels.dtos.request.AddRoomRequest;
import com.example.capstonehotels.dtos.request.EditRoomRequest;
import com.example.capstonehotels.dtos.response.Response;
import com.example.capstonehotels.exception.RoomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Response addRoom(AddRoomRequest addRoomRequest) {
        List<Room> allRooms = getAllRooms();
        for (Room room : allRooms) {
            if (room.getRoomNumber().equals(addRoomRequest.getRoomNumber())) throw new RoomException
                    ("A room with the number " + addRoomRequest.getRoomNumber() + " already exists, choose another room number");

        }
        Room room = new Room(addRoomRequest.getRoomNumber(),
                addRoomRequest.getRoomType(), addRoomRequest.getRoomPrice(), addRoomRequest.getRoomStatus());
        roomRepository.save(room);
        return new Response("Room added successfully");
    }

    @Override
    public Room getRoomById(String roomId) {
        return roomRepository.findById(roomId).orElseThrow(() -> new RoomException("room with the id " + roomId + " not found"));
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> getAvailableRooms() {

        List<Room> availableRooms = new ArrayList<>();
        List<Room> allRooms = getAllRooms();
        for (Room room : allRooms) {
            if (room.getRoomStatus().equals(RoomStatus.UNBOOKED)) {
                availableRooms.add(room);
            }

        }
        return availableRooms;
    }

    @Override
    public List<Room> getBookedRooms() {
        List<Room> unAvailableRooms = new ArrayList<>();
        List<Room> allRooms = getAllRooms();
        for (Room room : allRooms) {
            if (room.getRoomStatus().equals(RoomStatus.BOOKED)) {
                unAvailableRooms.add(room);
            }
        }
        return unAvailableRooms;
    }

    @Override
    public Response editRoomDetails(String roomId, EditRoomRequest editRoomRequest) {
        Room foundRoom = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("room not found"));
        List<Room> allRooms = getAllRooms();
        for (Room room : allRooms) {
            if (room.getRoomNumber().equals(editRoomRequest.getRoomNumber())) throw new RoomException
                    ("A room with the number " + editRoomRequest.getRoomNumber() + " already exists, choose another room number");
        }
            foundRoom.setRoomNumber(editRoomRequest.getRoomNumber() != null && !editRoomRequest.getRoomNumber().equals("")
                    ? editRoomRequest.getRoomNumber() : foundRoom.getRoomNumber());
            foundRoom.setRoomPrice(editRoomRequest.getRoomPrice() != null
                    ? editRoomRequest.getRoomPrice() : foundRoom.getRoomPrice());
            foundRoom.setRoomStatus(editRoomRequest.getRoomStatus() != null
                    ? editRoomRequest.getRoomStatus() : foundRoom.getRoomStatus());
            foundRoom.setRoomType(editRoomRequest.getRoomType() != null
                    ? editRoomRequest.getRoomType() : foundRoom.getRoomType());
            roomRepository.save(foundRoom);
            return new Response("Room details have been updated successfully");
        }

    @Override
    public Room getRoomByRoomNumber(String roomNumber) {
        return roomRepository.findRoomByRoomNumber(roomNumber).orElseThrow(()-> new
                RoomException("room with number "+roomNumber+" does not exist"));
    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

}


