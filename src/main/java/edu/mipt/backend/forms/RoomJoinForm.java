package edu.mipt.backend.forms;

import org.hibernate.validator.constraints.*;

public class RoomJoinForm {

    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    @NotBlank(message = RoomJoinForm.NOT_BLANK_MESSAGE)
    private Long Id;

    private String accessCode;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }
}