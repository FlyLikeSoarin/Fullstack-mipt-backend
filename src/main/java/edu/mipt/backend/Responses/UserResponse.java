package edu.mipt.backend.Responses;

import edu.mipt.backend.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserResponse {
    public UserResponse(User user) {
        this.id = user.id;
        this.username = user.username;
    }

    public static Iterable<UserResponse> convertList(Iterable<User> userList) {
        List<UserResponse> responseList = new ArrayList<UserResponse>();
        for (User user : userList) {
            responseList.add(new UserResponse(user));
        }
        return responseList;
    }

    public Long id;
    public String username;
}