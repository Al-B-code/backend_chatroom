package com.group_1.backend_chatroom.models;

import jakarta.persistence.*;
import jdk.jfr.Name;

import java.util.List;

@Table
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Name(value = "user_name")
    private String userName;
    @Column
    private String email;
    @Column
    private Role role;
    @OneToMany
    private List<Message> messages;

    public User(String userName, String email, Role role, List<Message> messages) {
        this.userName = userName;
        this.email = email;
        this.role = role;
        this.messages = messages;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
