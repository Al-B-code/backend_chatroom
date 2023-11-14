package com.group_1.backend_chatroom.services;

import com.group_1.backend_chatroom.dtos.ChatroomDTO;
import com.group_1.backend_chatroom.dtos.MessageContentDTO;
import com.group_1.backend_chatroom.dtos.UserDTO;
import com.group_1.backend_chatroom.models.Chatroom;
import com.group_1.backend_chatroom.models.Message;
import com.group_1.backend_chatroom.models.User;
import com.group_1.backend_chatroom.repositories.ChatroomRepository;
import com.group_1.backend_chatroom.repositories.MessageRepository;
import com.group_1.backend_chatroom.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatroomRepository chatroomRepository;

    @Autowired
    MessageRepository messageRepository;

    public List<User> users(){
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public void addUser(User user){
        userRepository.save(user);
    }


    @Transactional
    public Message userSendMessage( Long chatroomId, MessageContentDTO messageContentDTO){

        User user = userRepository.getReferenceById(messageContentDTO.getUserId());
        Chatroom chatroom = chatroomRepository.getReferenceById(chatroomId);
        Message message = new Message(messageContentDTO.getContent(), chatroom, user);
        chatroom.addMessage(message);
        chatroom.addUser(user);
        messageRepository.save(message);
        userRepository.save(user);
        chatroomRepository.save(chatroom);

        return message;


    }

}