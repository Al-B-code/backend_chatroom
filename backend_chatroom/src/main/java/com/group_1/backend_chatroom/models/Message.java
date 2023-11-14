package com.group_1.backend_chatroom.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
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

    public Message(String content, Chatroom chatroom, User user){
        this.content = content;
        this.chatroom = chatroom;
        this.user = user;
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime currentTime = localDateTime.atZone(ZoneId.of("UTC"));
        this.timeCreated = currentTime;
    }

    public Message(){
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
