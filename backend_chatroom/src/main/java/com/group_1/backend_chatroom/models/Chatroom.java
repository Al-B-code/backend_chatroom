package com.group_1.backend_chatroom.models;
import java.util.List;

import jakarta.persistence.*;

@Table(name = "chatroom")
@Entity
public class Chatroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany //Message needs to be created
    private List<Message> messages;

    @ManyToMany //User needs to be created
    private List<User> users;


    public Chatroom(String name, List<Message> messages, List<User> users) {
        this.name = name;
        this.messages = messages;
        this.users = users;
    }

    public Chatroom() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}



