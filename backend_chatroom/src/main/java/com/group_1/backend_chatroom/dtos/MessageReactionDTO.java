package com.group_1.backend_chatroom.dtos;

public class MessageReactionDTO {

    private String reaction;

    public MessageReactionDTO(String reaction) {
        this.reaction = reaction;
    }

    public MessageReactionDTO() {
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

}
