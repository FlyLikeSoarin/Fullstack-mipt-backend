package edu.mipt.backend.controller;

import edu.mipt.backend.model.User;
import edu.mipt.backend.services.UserService;
import jdk.nashorn.internal.objects.annotations.Constructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.tools.jstat.Token;

@RestController
public class AuthenticationController {
    @Autowired
    private UserService userService;

    private class TokenResponse {
        public String status;
        public String token;

        public TokenResponse(String status, String token) {
            this.status = status;
            this.token = token;
        }
    };

    private TokenResponse failureResponse = new TokenResponse("failure", "");

    @PostMapping("/auth/register")
    public TokenResponse register(@RequestParam(name="username") String username,
                           @RequestParam(name="email") String email,
                           @RequestParam(name="password") String password) {
        try {
            UserDetails user = userService.register(username, email, password);
            try {
                return new TokenResponse("success", userService.login(username, password));
            } catch (UsernameNotFoundException | UserService.IncorrectPassword e) {}
        } catch (UserService.UserAlreadyExists e) {}
        return failureResponse;
    }

    @PostMapping("/auth/login")
    public TokenResponse login(@RequestParam(name="username") String username,
                        @RequestParam(name="password") String password) {
        try {
            return new TokenResponse("success", userService.login(username, password));
        } catch (UsernameNotFoundException e) {
            return new TokenResponse("failure", "user not found");
        } catch (UserService.IncorrectPassword e) {
            return new TokenResponse("failure", "incorrect password");
        }

//        return failureResponse;
    }
}
