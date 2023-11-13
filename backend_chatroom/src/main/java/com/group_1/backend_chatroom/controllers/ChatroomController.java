package com.group_1.backend_chatroom.controllers;

import com.group_1.backend_chatroom.models.Chatroom;
import com.group_1.backend_chatroom.services.ChatroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("chatrooms")
public class ChatroomController {


    @Autowired
    ChatroomService chatroomService;

    @GetMapping
    public ResponseEntity<List<Chatroom>> getAllChatrooms(){
        return new ResponseEntity<>(chatroomService.getChatrooms(), HttpStatus.OK);
    }

}
