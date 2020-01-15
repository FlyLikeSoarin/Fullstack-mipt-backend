package edu.mipt.backend.controller;

import edu.mipt.backend.forms.RoomJoinForm;
import edu.mipt.backend.model.Entry;
import edu.mipt.backend.repositories.EntryRepository;
import edu.mipt.backend.repositories.RoomRepository;
import edu.mipt.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class EntryController {
    @Autowired
    EntryRepository repository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/room/{Id}/add")
    public String addEntryToRoom(
            @PathVariable("Id") Long Id,
            @Valid @ModelAttribute RoomJoinForm roomJoinForm,
            Principal principal,
            Errors errors,
            RedirectAttributes ra) {
        return "redirect:/";
    }

    @GetMapping("/room/{Id}/list")
    public Iterable<Entry> listEntry(@PathVariable("Id") Long Id) {
        return repository.findAll();
    }

}
