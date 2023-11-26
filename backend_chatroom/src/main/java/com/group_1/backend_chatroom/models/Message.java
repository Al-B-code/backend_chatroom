package com.group_1.backend_chatroom.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import com.group_1.backend_chatroom.views.View;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.SummaryForUser.class, View.SummaryForMessage.class, View.SummaryForChatroom.class})
    private Long id;
    @Column
    @JsonView(View.SummaryForMessage.class)
    private String userName;
    @Column(columnDefinition = "TEXT")
    @JsonView({View.SummaryForUser.class, View.SummaryForMessage.class, View.SummaryForChatroom.class})

    private String content;
    @Column
    @JsonView({View.SummaryForUser.class, View.SummaryForMessage.class, View.SummaryForChatroom.class})
    private ZonedDateTime timeCreated;
    @ManyToOne
    @JsonIgnoreProperties({"messages", "userChatroomAssociations"})
    @JoinColumn(name = "user_id")
    @JsonView({View.SummaryForMessage.class, View.SummaryForChatroom.class})
    private User user;
    @ManyToOne
    @JsonIgnoreProperties({"messages", "userChatroomAssociations"})
    @JoinColumn(name = "chatroom_id")
    @JsonView({View.SummaryForMessage.class, View.SummaryForChatroom.class})
    private Chatroom chatroom;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"message", "user"})
    @JsonView({View.SummaryForMessage.class, View.SummaryForUser.class, View.SummaryForChatroom.class})
    private List<MessageReaction> reactions;


    public Message(String content, Chatroom chatroom, User user){
        this.content = content;
        this.chatroom = chatroom;
        this.user = user;
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime currentTime = localDateTime.atZone(ZoneId.of("UTC"));
        this.timeCreated = currentTime;
        this.userName = user.getUserName();
        this.reactions = new ArrayList<>();
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void addReaction(MessageReaction messageReaction){
        this.reactions.add(messageReaction);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Chatroom getChatroom() {
        return chatroom;
    }

    public void setChatroom(Chatroom chatroom) {
        this.chatroom = chatroom;
    }

    public List<String> getReactions() {
        ArrayList<String> emojiReactions = new ArrayList<>();
        for (MessageReaction emoji : this.reactions){
            emojiReactions.add(emoji.getReaction().getEmoji());
        }
        return emojiReactions;
    }

    public void setReactions(List<MessageReaction> reactions) {
        this.reactions = reactions;
    }
}
