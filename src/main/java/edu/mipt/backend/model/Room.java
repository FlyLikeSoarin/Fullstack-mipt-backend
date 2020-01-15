package edu.mipt.backend.model;

import javax.persistence.*;

@Entity
@Table(name="room")
public class Room {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name="title")
    private String title;

    @Column(name="accessCode")
    private String accessCode;

    @Column(name="currentlyPlaying")
    private Long currentlyPlaying;

    public Room(String title, String accessCode) {
        this.title = title;
        this.accessCode = accessCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public Long getCurrentlyPlaying() {
        return currentlyPlaying;
    }

    public void setCurrentlyPlaying(Long currentlyPlaying) {
        this.currentlyPlaying = currentlyPlaying;
    }
}
