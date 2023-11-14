package com.group_1.backend_chatroom.services;

import com.group_1.backend_chatroom.dtos.ChatroomDTO;
import com.group_1.backend_chatroom.dtos.MessageContentDTO;
import com.group_1.backend_chatroom.dtos.UserDTO;
import com.group_1.backend_chatroom.models.Chatroom;
import com.group_1.backend_chatroom.models.Message;
import com.group_1.backend_chatroom.models.User;
import com.group_1.backend_chatroom.models.UserChatroomAssociation;
import com.group_1.backend_chatroom.repositories.ChatroomRepository;
import com.group_1.backend_chatroom.repositories.MessageRepository;
import com.group_1.backend_chatroom.repositories.UserChatroomAssociationRepository;
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

    @Autowired
    UserChatroomAssociationRepository userChatroomAssociationRepository;

    public List<User> users(){
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public Long deleteUser(Long id){
        userRepository.deleteById(id);
        return id;
    }

    @Transactional
    public Message userSendMessage( Long chatroomId, MessageContentDTO messageContentDTO){

        User user = userRepository.getReferenceById(messageContentDTO.getUserId());
        Chatroom chatroom = chatroomRepository.getReferenceById(chatroomId);
        Message message = new Message(messageContentDTO.getContent(), chatroom, user);
        chatroom.addMessage(message);
        // add to the userchatroomassociation table
        UserChatroomAssociation userChatroomAssociation = new UserChatroomAssociation(user, chatroom);

        // checks if the association exits. If it does NOT it will save. If it doesnt it wont save.
        List<UserChatroomAssociation> chatroomAssociations = userChatroomAssociationRepository.findByUserIdAndChatroomId(messageContentDTO.getUserId(), chatroomId);

        if (chatroomAssociations.isEmpty()){
            userChatroomAssociationRepository.save(userChatroomAssociation);
        }
        messageRepository.save(message);
        userRepository.save(user);
        chatroomRepository.save(chatroom);

        return message;


    }

}