package com.example.quanlyphongtro;

import java.util.ArrayList;
import java.util.List;

public class RoomDataCenter {
    private static RoomDataCenter instance;
    private static List<Room> roomList;

    private RoomDataCenter() {
        roomList = new ArrayList<>();
        // Mock data
        roomList.add(new Room("P101", "Phòng 101", 2500000, "Còn trống", "", ""));
        roomList.add(new Room("P102", "Phòng 102", 3000000, "Đã thuê", "Nguyễn Văn A", "0987654321"));
        roomList.add(new Room("P201", "Phòng 201", 2800000, "Còn trống", "", ""));
    }

    public static synchronized RoomDataCenter getInstance() {
        if (instance == null) {
            instance = new RoomDataCenter();
        }
        return instance;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void addRoom(Room room) {
        roomList.add(room);
    }

    public void updateRoom(int index, Room room) {
        if (index >= 0 && index < roomList.size()) {
            roomList.set(index, room);
        }
    }

    public void deleteRoom(int index) {
        if (index >= 0 && index < roomList.size()) {
            roomList.remove(index);
        }
    }
}
