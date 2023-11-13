package com.group_1.backend_chatroom.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String content;
    @Column
    private ZonedDateTime timeCreated;
    @ManyToOne
    @JsonIgnoreProperties({"messages"})
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JsonIgnoreProperties({"messages"})
    @JoinColumn(name = "chatroom_id")
    private Chatroom chatroom;

    public Messages(String content){
        this.content = content;
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime currentTime = localDateTime.atZone(ZoneId.of("UTC"));
        this.timeCreated = currentTime;
    }

    public Messages(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ZonedDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(ZonedDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }
}
