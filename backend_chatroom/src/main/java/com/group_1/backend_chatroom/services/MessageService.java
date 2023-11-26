package com.group_1.backend_chatroom.services;

import com.group_1.backend_chatroom.dtos.MessageContentDTO;
import com.group_1.backend_chatroom.dtos.ReplyDTO;
import com.group_1.backend_chatroom.models.Chatroom;
import com.group_1.backend_chatroom.models.Message;
import com.group_1.backend_chatroom.models.User;
import com.group_1.backend_chatroom.models.UserChatroomAssociation;
import com.group_1.backend_chatroom.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatroomRepository chatroomRepository;

    @Autowired
    UserChatroomAssociationRepository userChatroomAssociationRepository;

    @Autowired
    MessageReactionRepository messageReactionRepository;

    public List<Message> getMessages(){
        return messageRepository.findAll();
    }

    public Message getMessage(Long id) {
        return messageRepository.findById(id).get();
    }

    public void addMessage(Message message){
        messageRepository.save(message);
    }



    @Transactional
    public ResponseEntity<ReplyDTO> userSendMessage(MessageContentDTO messageContentDTO){
        User user = userRepository.findById(messageContentDTO.getUserId()).get();
        Chatroom chatroom = chatroomRepository.findById(messageContentDTO.getChatroomId()).get();
        Message message = new Message(messageContentDTO.getContent(), chatroom, user);
        List<UserChatroomAssociation> userChatroomAssocations = userChatroomAssociationRepository.findByUserIdAndChatroomId(user.getId(), chatroom.getId()); // findbyuserIdandChatroomId shouldnt be a list.


        if (userChatroomAssocations.isEmpty()) {
            return new ResponseEntity<>(new ReplyDTO("User not in chatroom, message not sent"), HttpStatus.BAD_REQUEST);
        }

        chatroom.addMessage(message);
        messageRepository.save(message);
        userRepository.save(user);
        chatroomRepository.save(chatroom);
        return new ResponseEntity<>(new ReplyDTO("Message sent successfully"), HttpStatus.OK);
    }


}
