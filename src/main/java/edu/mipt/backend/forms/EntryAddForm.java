package edu.mipt.backend.forms;

import org.hibernate.validator.constraints.NotBlank;

public class EntryAddForm {

    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    @NotBlank(message = EntryAddForm.NOT_BLANK_MESSAGE)
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
