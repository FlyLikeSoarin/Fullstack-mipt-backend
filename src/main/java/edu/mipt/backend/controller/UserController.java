package edu.mipt.backend.controller;

import edu.mipt.backend.model.User;
import edu.mipt.backend.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/user/me")
    public User getUserMe(Principal principal) {
        return repository.findOneByUsername(principal.getName());
    }

    @GetMapping("/user/list")
    public Iterable<User> getUserAll(Principal principal) {
        return repository.findAll();
    }
}
