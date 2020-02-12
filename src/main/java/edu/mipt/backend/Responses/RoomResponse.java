package edu.mipt.backend.Responses;

import edu.mipt.backend.model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomResponse {
    public RoomResponse(Room room) {
        this.id = room.id;
        this.title = room.title;
        this.currentlyPlaying = room.currentlyPlaying;
    }

    public static Iterable<RoomResponse> convertList(Iterable<Room> roomList) {
        List<RoomResponse> responseList = new ArrayList<RoomResponse>();
        for (Room room : roomList) {
            responseList.add(new RoomResponse(room));
        }
        return responseList;
    }

    public Long id;
    public String title;
    public Long currentlyPlaying;
};
