package com.group_1.backend_chatroom.controllers;

import com.group_1.backend_chatroom.models.Message;
import com.group_1.backend_chatroom.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("messages")
public class MessageController {

    @Autowired
    MessageService messageService;
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages(){
        return new ResponseEntity<>(messageService.getMessages(), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id){
        return new ResponseEntity<>(messageService.getMessage(id), HttpStatus.OK);
    }
}
