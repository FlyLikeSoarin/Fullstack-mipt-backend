package edu.mipt.backend.controller;

import edu.mipt.backend.forms.RoomCreateForm;
import edu.mipt.backend.forms.RoomJoinForm;
import edu.mipt.backend.model.Room;
import edu.mipt.backend.repositories.RoomRepository;
import edu.mipt.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@RestController
public class RoomController {

    @Autowired
    RoomRepository repository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/room/create")
    public String roomCreate(@Valid @ModelAttribute RoomCreateForm roomCreateForm, Errors errors, RedirectAttributes ra) {
        repository.save(roomCreateForm.createRoom());
        return "redirect:/";
    }

    @PostMapping("/room/join")
    public String roomJoin(@Valid @ModelAttribute RoomJoinForm roomJoinForm, Principal principal, Errors errors, RedirectAttributes ra) {
        Optional<Room> roomQuery = repository.findById(roomJoinForm.getId());
        if (roomQuery.isPresent()) {
            Room room = roomQuery.get();
            if (room.getAccessCode().equals(roomJoinForm.getAccessCode())) {
//                userRepository.addRoomByUserName(principal.getName());
            }
        }
        return "redirect:/";
    }

    @GetMapping("/room/list")
    public Iterable<Room> roomList() {
        return repository.findAll();
    }
}
