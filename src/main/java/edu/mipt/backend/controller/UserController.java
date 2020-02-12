package edu.mipt.backend.controller;

import edu.mipt.backend.Responses.UserResponse;
import edu.mipt.backend.model.User;
import edu.mipt.backend.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.net.URLConnection;
import java.security.Principal;
import java.util.Scanner;

@RestController
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/user/me")
    public UserResponse getUserMe(Principal principal) {
        return new UserResponse(repository.findOneByUsername(principal.getName()));
    }

    @GetMapping("/api/user/list")
    public Iterable<UserResponse> getUserAll(Principal principal) {
        return UserResponse.convertList(repository.findAll());
    }
}
