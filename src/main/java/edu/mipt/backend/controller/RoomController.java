package edu.mipt.backend.controller;

import edu.mipt.backend.Responses.EntryResponse;
import edu.mipt.backend.Responses.RoomResponse;
import edu.mipt.backend.Responses.UserResponse;
import edu.mipt.backend.model.Entry;
import edu.mipt.backend.model.Room;
import edu.mipt.backend.model.User;
import edu.mipt.backend.repositories.EntryRepository;
import edu.mipt.backend.repositories.RoomRepository;
import edu.mipt.backend.repositories.UserRepository;
import edu.mipt.backend.services.YoutubeFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;

@RestController
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntryRepository entryRepository;



    @PostMapping("/api/room/create")
    public ResponseEntity roomCreate(Principal principal,
                                     @RequestParam(name="title") String title,
                                     @RequestParam(name="access_code") String accessCode) {
        Room room = new Room(title, accessCode);
        User user = userRepository.findOneByUsername(principal.getName());

        room.users.add(user);
        roomRepository.save(room);

        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @PostMapping("/api/room/{id}/join")
    public ResponseEntity roomJoin(Principal principal,
                                   @PathVariable("id") Long id,
                                   @RequestParam(name="access_code") String accessCode) {
         Room room = roomRepository.findOneById(id);
         User user = userRepository.findOneByUsername(principal.getName());
         if (!room.users.contains(user)) {
             if (room.getAccessCode().equals(accessCode)) {
                 room.users.add(user);
                 roomRepository.save(room);
                 return ResponseEntity.status(HttpStatus.OK).body("success");
             }
             return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access code is incorrect");
         }
         return ResponseEntity.status(HttpStatus.OK).body("Already joined");
    }

    @PostMapping("/api/room/{id}/add_entry")
    public ResponseEntity addEntryToRoom(Principal principal,
                                         @PathVariable("id") Long id,
                                         @RequestParam(name="url") String url) {
        User user = userRepository.findOneByUsername(principal.getName());
        Room room = roomRepository.findOneById(id);
        if (room.users.contains(user)) {
            YoutubeFetchService.FetchedData videoData;

            try {
                videoData = YoutubeFetchService.fetchData(url);
            } catch (YoutubeFetchService.InvalidURL e) {
                System.out.println(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid url");
            }

            Entry entry = new Entry(room, user, url, videoData.title, videoData.duration);
            entryRepository.save(entry);
            if (entry.id != null) {
                room.currentlyPlaying = entry.id;
                roomRepository.save(room);
            }

            // Functionality of backend video scheduler. Not operational yet.
            // VideoSchedulingService schedulingService = new VideoSchedulingService();
            // schedulingService.Schedule(id);

            return ResponseEntity.status(HttpStatus.OK).body("success");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not member of the room");
    }

    @GetMapping("/api/room/{id}/list_entries")
    public Iterable<EntryResponse> listEntry(Principal principal,
                                             @PathVariable("id") Long id) throws AccessDeniedException {
        User user = userRepository.findOneByUsername(principal.getName());
        Room room = roomRepository.findOneById(id);
        if (room.users.contains(user)) {
            return EntryResponse.convertList(room.entries);
        }
        throw new AccessDeniedException("User is not a member of the room");
    }

    @GetMapping("/api/room/{id}/list_users")
    public Iterable<UserResponse> listUser(Principal principal,
                                   @PathVariable("id") Long id) throws AccessDeniedException {
        User user = userRepository.findOneByUsername(principal.getName());
        Room room = roomRepository.findOneById(id);
        if (room.users.contains(user)) {
            return UserResponse.convertList(room.users);
        }
        throw new AccessDeniedException("User is not a member of the room");
    }

    @GetMapping("/api/room/list")
    public Iterable<RoomResponse> roomList(Principal principal) {
        User user = userRepository.findOneByUsername(principal.getName());
        return RoomResponse.convertList(user.rooms);
    }
}
