package com.example.capstonehotels.services;

import com.example.capstonehotels.data.model.Room;
import com.example.capstonehotels.data.model.RoomStatus;
import com.example.capstonehotels.data.model.RoomType;
import com.example.capstonehotels.dtos.request.AddRoomRequest;
import com.example.capstonehotels.dtos.request.EditRoomRequest;
import com.example.capstonehotels.dtos.response.Response;
import com.example.capstonehotels.exception.RoomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RoomServiceImplTest {
    @Autowired
    private RoomService roomService;

    private AddRoomRequest addRoomRequest;
    private AddRoomRequest addRoomRequest1;
    private AddRoomRequest addRoomRequest2;
    private AddRoomRequest addRoomRequest3;
    private AddRoomRequest addRoomRequest4;
    @BeforeEach
    void setUp() {
        addRoomRequest = new AddRoomRequest();
        addRoomRequest.setRoomNumber("678");
        addRoomRequest.setRoomStatus(RoomStatus.BOOKED);
        addRoomRequest.setRoomPrice(BigDecimal.valueOf(2000));
        addRoomRequest.setRoomType(RoomType.EXECUTIVE_SUITE);

        addRoomRequest1 = new AddRoomRequest();
        addRoomRequest1.setRoomNumber("107");
        addRoomRequest1.setRoomStatus(RoomStatus.UNBOOKED);
        addRoomRequest1.setRoomPrice(BigDecimal.valueOf(1500));
        addRoomRequest1.setRoomType(RoomType.APARTMENT);

        addRoomRequest2 = new AddRoomRequest();
        addRoomRequest2.setRoomNumber("108");
        addRoomRequest2.setRoomStatus(RoomStatus.UNBOOKED);
        addRoomRequest2.setRoomPrice(BigDecimal.valueOf(1500));
        addRoomRequest2.setRoomType(RoomType.APARTMENT);

        addRoomRequest3 = new AddRoomRequest();
        addRoomRequest3.setRoomNumber("119");
        addRoomRequest3.setRoomStatus(RoomStatus.UNBOOKED);
        addRoomRequest3.setRoomPrice(BigDecimal.valueOf(1500));
        addRoomRequest3.setRoomType(RoomType.APARTMENT);

        addRoomRequest4 = new AddRoomRequest();
        addRoomRequest4.setRoomNumber("003");
        addRoomRequest4.setRoomStatus(RoomStatus.UNBOOKED);
        addRoomRequest4.setRoomPrice(BigDecimal.valueOf(1500));
        addRoomRequest4.setRoomType(RoomType.APARTMENT);
    }
    @Test void testThatRoomCanBeAdded(){
        Response res = roomService.addRoom(addRoomRequest);
        assertEquals("Room added successfully", res.getMessage());
    }
    @Test void testThatRoomCanBeSearchedWithId(){
        Room searchedRoom = roomService.getRoomById("641af666a0037a3baa499c94");
        assertEquals("107", searchedRoom.getRoomNumber());
    }
    @Test void testThatRoomDetailsCanBeEdited(){
        EditRoomRequest editRoomRequest = new EditRoomRequest();
        editRoomRequest.setRoomNumber("211");
       Response res = roomService.editRoomDetails("641ad141b2230e3fa9128691", editRoomRequest);
       assertEquals("Room details have been updated successfully", res.getMessage());
    }
    @Test void testThatSearchingRoomWithWrongIdThrowsException(){
        assertThrows(RoomException.class, ()-> roomService.getRoomById("123"));
    }
    @Test void testThatAddingRoomWithExistingRoomNumberThrowsException(){
        //Response res = roomService.addRoom(addRoomRequest1);
        assertThrows(RoomException.class, ()-> roomService.addRoom(addRoomRequest1));
        //assertEquals("Room added successfully", res.getMessage());
    }
    @Test void testThatOnlyAvailableRoomsCanBeSearchedFor(){
        List<Room> availableRooms = roomService.getAvailableRooms();
        int counter = 0;
        for (Room room: availableRooms){
            if (room.getRoomStatus().equals(RoomStatus.UNBOOKED)) counter++;
        }
        assertEquals(6, counter);
    }
    @Test void testThatOnlyBookedRoomCanBeSearchedFor(){
        List<Room> bookedRooms = roomService.getBookedRooms();
        int counter = 0;
        for (Room room: bookedRooms){
            if (room.getRoomStatus().equals(RoomStatus.BOOKED)) counter++;
        }
        assertEquals(9, counter);

    }
}