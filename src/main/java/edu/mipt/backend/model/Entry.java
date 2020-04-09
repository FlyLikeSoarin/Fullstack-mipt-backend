package edu.mipt.backend.model;

import javax.persistence.*;

@Entity
@Table(name="entry")
public class Entry {

    @Id
    @Column(name="id", unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @ManyToOne
    @JoinColumn(name="room_id", referencedColumnName="id")
    public Room room;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    public User user;

    @Column(name="url")
    public String url;

    @Column(name="title")
    public String title;

    @Column(name="duration")
    public Long duration;

    @Column(name="timestamp")
    public Long uploadTimestamp;

    public Entry(Room room, User user, String url, String title, Long duration) {
        this.room = room;
        this.user = user;
        this.url = url;
        this.title = title;
        this.duration = duration;
        this.uploadTimestamp = System.currentTimeMillis();
    }

    public Entry() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getUploadTimestamp() {
        return uploadTimestamp;
    }

    public void setUploadTimestamp(Long uploadTimestamp) {
        this.uploadTimestamp = uploadTimestamp;
    }
}
