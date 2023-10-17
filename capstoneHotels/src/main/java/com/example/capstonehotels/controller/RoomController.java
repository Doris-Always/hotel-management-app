package com.example.capstonehotels.controller;

import com.example.capstonehotels.data.model.Room;
import com.example.capstonehotels.dtos.request.AddRoomRequest;
import com.example.capstonehotels.dtos.request.EditRoomRequest;
import com.example.capstonehotels.dtos.response.Response;
import com.example.capstonehotels.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/room")
@CrossOrigin(origins = "*")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/view_available_rooms")
    public ResponseEntity<?> getAvailableRooms(){
    return ResponseEntity.ok(roomService.getAvailableRooms());
    }
    @PostMapping("/add_room")
    public ResponseEntity<?> addRoom(@RequestBody AddRoomRequest addRoomRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.addRoom(addRoomRequest));
    }
    @GetMapping("/get_room_by_id/{roomId}")
    public ResponseEntity<?> getRoomById(@PathVariable String roomId){
        return ResponseEntity.ok(roomService.getRoomById(roomId));
    }
    @GetMapping("/get_all_rooms")
    public ResponseEntity<?> getAllRooms(){
        return ResponseEntity.ok(roomService.getAllRooms());
    }
    @GetMapping("/get_booked_rooms")
    public ResponseEntity<?> getBookedRooms(){
        return ResponseEntity.ok(roomService.getBookedRooms());
    }
    @PatchMapping("/update_room_details/{id}")
    public ResponseEntity<?> editRoomDetails(@PathVariable String id, @RequestBody EditRoomRequest editRoomRequest){
        return ResponseEntity.ok(roomService.editRoomDetails(id, editRoomRequest));
    }
    @GetMapping("/find-room-by-room-number/{roomNumber}")
    public ResponseEntity<?> getRoomByRoomNumber(@PathVariable String roomNumber){
        return ResponseEntity.ok(roomService.getRoomByRoomNumber(roomNumber));
    }
}
