package com.group_1.backend_chatroom.models;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Table(name = "chatrooms")
@Entity
public class Chatroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany (mappedBy = "chatroom")
    private List<Message> messages;

//    @ManyToMany //User needs to be created
//    @JoinTable(
//            name = "chatroom_users",
//            joinColumns = @JoinColumn(name = "chatroom_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"),
//            uniqueConstraints = @UniqueConstraint(columnNames = {"chatroom_id", "user_id"})
//    )
//
//    @JsonIgnoreProperties({"users"})
//    @Column
//    private List<User> users;

    @OneToMany(mappedBy = "chatroom")
    private List<UserChatroomAssociation> userChatroomAssociations;


    public Chatroom(String name) {
        this.name = name;
        this.messages = new ArrayList<>();
//        this.users = new ArrayList<>();
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

//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }

    public void addMessage(Message message){
        this.messages.add(message);
    }

    public void removeMessage(Message message){
        this.messages.remove(message);
    }

//    public void addUser(User user){
//        this.users.add(user);
//    }
//
//    public void removeUser(User user){
//        this.users.remove(user);
//    }


    public void addUserChatroomAssociation(UserChatroomAssociation userChatroomAssociation){
        this.userChatroomAssociations.add(userChatroomAssociation);
    }

    public void removeUserChatroomAssociation(UserChatroomAssociation userChatroomAssociation){
        this.userChatroomAssociations.remove(userChatroomAssociation);
    }

    public List<UserChatroomAssociation> getUserChatroomAssociations() {
        return userChatroomAssociations;
    }

    public void setUserChatroomAssociations(List<UserChatroomAssociation> userChatroomAssociations) {
        this.userChatroomAssociations = userChatroomAssociations;
    }
}



