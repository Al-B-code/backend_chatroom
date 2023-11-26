package com.group_1.backend_chatroom.dtos;

public class ReplyDTO {

    private String reply;



    public ReplyDTO(String reply) {
        this.reply = reply;
    }

    public ReplyDTO() {
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

}

