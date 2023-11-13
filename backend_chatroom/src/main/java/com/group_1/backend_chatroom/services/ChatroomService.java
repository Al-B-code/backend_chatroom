package com.group_1.backend_chatroom.services;

import com.group_1.backend_chatroom.dtos.ChatroomDTO;
import com.group_1.backend_chatroom.models.Chatroom;
import com.group_1.backend_chatroom.repositories.ChatroomRepository;
import com.group_1.backend_chatroom.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatroomService {

    @Autowired
    ChatroomRepository chatroomRepository;

    @Autowired
    MessageRepository messageRepository;

    public List<Chatroom> getChatrooms() {
        return chatroomRepository.findAll();
    }

    public Chatroom getChatroomById(ChatroomDTO chatroomDTO){
        return chatroomRepository.getReferenceById(chatroomDTO.getId());
    }

    public void addChatroom(Chatroom chatroom){
        chatroomRepository.save(chatroom);
    }


}
