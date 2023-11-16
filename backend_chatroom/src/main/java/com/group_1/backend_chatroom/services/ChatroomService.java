package com.group_1.backend_chatroom.services;

import com.group_1.backend_chatroom.dtos.ChatroomDTO;
import com.group_1.backend_chatroom.models.Chatroom;
import com.group_1.backend_chatroom.models.Message;
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

    public Chatroom getChatroom(ChatroomDTO chatroomDTO){
        return chatroomRepository.getReferenceById(chatroomDTO.getId());
    }

    public void addChatroom(Chatroom chatroom){
        chatroomRepository.save(chatroom);
    }

    public Chatroom createNewChatroom(ChatroomDTO chatroomDTO){
        Chatroom chatroom = new Chatroom(chatroomDTO.getName());
        chatroomRepository.save(chatroom);
        return chatroom;
    }

    public List<Message> getChatroomMessages(Long id){
        return chatroomRepository.findById(id).get().getMessages();
    }


}
