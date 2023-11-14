package com.group_1.backend_chatroom.models;

import jakarta.persistence.*;
import jdk.jfr.Name;

import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
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
    @OneToMany (mappedBy = "user")
    private List<Message> messages;

    @ManyToMany (mappedBy = "users")
    private List<Chatroom> chatrooms;

    public User(String userName, String email, Role role) {
        this.userName = userName;
        this.email = email;
        this.role = role;
        this.messages = new ArrayList<>();
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


    public void addMessage(Message message){
        this.messages.add(message);
    }

    public void removeMessage(Message message){
        this.messages.remove(message);
    }

}
