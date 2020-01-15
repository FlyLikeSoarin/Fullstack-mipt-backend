package edu.mipt.backend.forms;

import edu.mipt.backend.model.Room;
import edu.mipt.backend.model.User;
import org.hibernate.validator.constraints.*;

public class RoomCreateForm {

    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    @NotBlank(message = RoomCreateForm.NOT_BLANK_MESSAGE)
    private String title;

    private String accessCode;

    private String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public Room createRoom() {
        return new Room(getTitle(), getAccessCode());
    }
}