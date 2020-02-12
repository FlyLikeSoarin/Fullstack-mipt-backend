package edu.mipt.backend.controller;

import edu.mipt.backend.repositories.EntryRepository;
import edu.mipt.backend.repositories.RoomRepository;
import edu.mipt.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EntryController {
    @Autowired
    EntryRepository entryRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

}
