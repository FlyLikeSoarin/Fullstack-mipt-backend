package edu.mipt.backend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="room")
public class Room {

    @Id
    @Column(name="id", unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name="title")
    public String title;

    @Column(name="accessCode")
    public String accessCode;

    @Column(name="currentlyPlaying")
    public Long currentlyPlaying;

    @OneToMany(mappedBy="room")
    public List<Entry> entries = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name="users_in_room",
            joinColumns = @JoinColumn(name="room_id"),
            inverseJoinColumns = @JoinColumn(name="user_id"))
    public List<User> users = new ArrayList<>();

    public Room() {
        this.title = "default-room";
        this.accessCode = "";
        this.currentlyPlaying = new Long(-1);
    }

    public Room(String title, String accessCode) {
        this.title = title;
        this.accessCode = accessCode;
        this.currentlyPlaying = new Long(-1);
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
