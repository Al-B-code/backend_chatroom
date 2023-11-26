package com.group_1.backend_chatroom.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.group_1.backend_chatroom.views.View;
import jakarta.persistence.*;

@Entity
@Table(name = "user_chatroom_associations", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"chatroom_id", "user_id"})
})
public class UserChatroomAssociation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.SummaryForUser.class, View.SummaryForChatroom.class})
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"userChatroomAssociations"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    @JsonIgnoreProperties({"userChatroomAssociations"})
    @JsonView(View.SummaryForUser.class)
    private Chatroom chatroom;


    public UserChatroomAssociation(User user, Chatroom chatroom) {
        this.user = user;
        this.chatroom = chatroom;
    }

    public UserChatroomAssociation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}



