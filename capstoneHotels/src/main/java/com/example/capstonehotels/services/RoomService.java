package com.example.capstonehotels.services;

import com.example.capstonehotels.data.model.Room;
import com.example.capstonehotels.dtos.request.AddRoomRequest;
import com.example.capstonehotels.dtos.request.EditRoomRequest;
import com.example.capstonehotels.dtos.response.Response;

import java.util.List;

public interface RoomService {
    Response addRoom(AddRoomRequest addRoomRequest);
    Room getRoomById(String roomId);
    List<Room> getAllRooms();
    List<Room> getAvailableRooms();
    List<Room> getBookedRooms();
    Response editRoomDetails( String roomId, EditRoomRequest editRoomRequest);

    Room getRoomByRoomNumber(String roomNumber);

    Room saveRoom(Room room);

}
