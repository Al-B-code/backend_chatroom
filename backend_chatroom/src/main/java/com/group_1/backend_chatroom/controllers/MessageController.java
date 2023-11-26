package com.group_1.backend_chatroom.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.group_1.backend_chatroom.dtos.MessageContentDTO;
import com.group_1.backend_chatroom.dtos.MessageReactionDTO;
import com.group_1.backend_chatroom.dtos.ReplyDTO;
import com.group_1.backend_chatroom.models.Message;
import com.group_1.backend_chatroom.services.MessageService;
import com.group_1.backend_chatroom.services.UserService;
import com.group_1.backend_chatroom.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("messages")
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @GetMapping
    @JsonView(View.SummaryForMessage.class)
    public ResponseEntity<List<Message>> getAllMessages(){
        return new ResponseEntity<>(messageService.getMessages(), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    @JsonView(View.SummaryForMessage.class)
    public ResponseEntity<Message> getMessageById(@PathVariable Long id){
        return new ResponseEntity<>(messageService.getMessage(id), HttpStatus.OK);
    }

    @PatchMapping("/{messageId}")
    public ResponseEntity<ReplyDTO> addReactionToMessage(@PathVariable Long messageId, @RequestBody MessageReactionDTO messageReactionDTO){
        return userService.addReaction(messageId, messageReactionDTO);
    }

    @PostMapping
    public ResponseEntity<ReplyDTO> userSendMessage( @RequestBody MessageContentDTO messageContentDTO){
        return messageService.userSendMessage(messageContentDTO);
    }

}
