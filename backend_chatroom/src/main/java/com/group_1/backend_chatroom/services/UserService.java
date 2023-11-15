package com.group_1.backend_chatroom.services;

import com.group_1.backend_chatroom.dtos.ChatroomDTO;
import com.group_1.backend_chatroom.dtos.MessageContentDTO;
import com.group_1.backend_chatroom.dtos.MessageReactionDTO;
import com.group_1.backend_chatroom.dtos.UserDTO;
import com.group_1.backend_chatroom.models.*;
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

        messageRepository.save(message);
        userRepository.save(user);
        chatroomRepository.save(chatroom);

        // add to the userchatroomassociation table
        UserChatroomAssociation userChatroomAssociation = new UserChatroomAssociation(user, chatroom);

        // checks if the association exits. If it does NOT it will save. If it doesnt it wont save.
        List<UserChatroomAssociation> chatroomAssociations = userChatroomAssociationRepository.findByUserIdAndChatroomId(messageContentDTO.getUserId(), chatroomId);

        if (chatroomAssociations.isEmpty()){
            userChatroomAssociationRepository.save(userChatroomAssociation);
        }

        return message;


    }

    public User createNewUser(UserDTO userDTO){

        User user = new User(
                userDTO.getUserName(),
                userDTO.getEmail(),
                Role.fromInteger(userDTO.getRole())
        );
        System.out.println(userDTO.getRole());
        userRepository.save(user);

        return user;
    }


    public User updateUser( Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).get();
        if (userDTO.getUserName() != null){
            user.setUserName(userDTO.getUserName());
        }
        if (userDTO.getEmail() != null){
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getRole() != 0 ){
            Role role = Role.fromInteger(userDTO.getRole());
            user.setRole(role);
        }

        userRepository.save(user);
        return user;

    }

    public Message addReaction(Long messageId, MessageReactionDTO messageReactionDTO){
        Message message = messageRepository.findById(messageId).get();
        Reaction reaction = Reaction.fromString(messageReactionDTO.getReaction());
        MessageReaction messageReaction = new MessageReaction(message, message.getUser(), reaction);
        return message;
    }


}