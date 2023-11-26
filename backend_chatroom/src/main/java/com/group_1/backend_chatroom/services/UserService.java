package com.group_1.backend_chatroom.services;

import com.group_1.backend_chatroom.dtos.MessageContentDTO;
import com.group_1.backend_chatroom.dtos.MessageReactionDTO;
import com.group_1.backend_chatroom.dtos.ReplyDTO;
import com.group_1.backend_chatroom.dtos.UserDTO;
import com.group_1.backend_chatroom.models.*;
import com.group_1.backend_chatroom.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    @Autowired
    MessageReactionRepository messageReactionRepository;

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


    public Chatroom addUserToChatroom(Long userId, Long chatroomId){
        User user = userRepository.findById(userId).get();
        Chatroom chatroom = chatroomRepository.findById(chatroomId).get();
        List<UserChatroomAssociation> UserChatroomAssociations = userChatroomAssociationRepository.findByUserIdAndChatroomId(user.getId(), chatroom.getId()); // findbyuserIdandChatroomId shouldnt be a list.



        if (UserChatroomAssociations.isEmpty()){
            // add to the userchatroomassociation table
            UserChatroomAssociation userChatroomAssociation = new UserChatroomAssociation(user, chatroom);
            userChatroomAssociationRepository.save(userChatroomAssociation);
            chatroom.addUserChatroomAssociation(userChatroomAssociation);
            chatroomRepository.save(chatroom);

            return chatroom;

        }
        return chatroom;
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


    public ResponseEntity<ReplyDTO> updateUser( Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).get();

        String userName = "";
        String email = "";
        String userRole = "";
        String softDeleted = "";

        if (userDTO.getUserName() != null && !(Objects.equals(userDTO.getUserName(), user.getUserName()))){
            user.setUserName(userDTO.getUserName());
            userName = "User name has been set to " + user.getUserName() + ". ";
        }
        if (userDTO.getEmail() != null && !(Objects.equals(userDTO.getEmail(), user.getEmail()))){
            user.setEmail(userDTO.getEmail());
            email = "user email has been set to " + user.getEmail() + ". ";
        }
        if (userDTO.getRole() != 0 && userDTO.getRole() != user.getRole().ordinal()){
            try {
                Role role = Role.fromInteger(userDTO.getRole());
                user.setRole(role);
                userRole = "user role has been set to " + user.getRole() + ". ";
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(new ReplyDTO("invalid role, user details not saved. Please add a valid role."), HttpStatus.BAD_REQUEST);
            }
        }
        if (userDTO.getSoftDeleted() != null && userDTO.getSoftDeleted() != user.getSoftDeleted()){
                user.setSoftDeleted(userDTO.getSoftDeleted());
                softDeleted = "user's soft deleted status has been set to " + user.getSoftDeleted() + ". ";
        }


        userRepository.save(user);
        ReplyDTO reply = new ReplyDTO(userName + email + userRole + softDeleted);
        if (reply.getReply().isEmpty()){
            reply.setReply("No user details have changed.");
            return new ResponseEntity<>(reply, HttpStatus.OK );
        }
        reply.setReply(userName + email + userRole + softDeleted);
        return new ResponseEntity<>(reply, HttpStatus.OK);

    }

    public ResponseEntity<ReplyDTO> addReaction(Long messageId, MessageReactionDTO messageReactionDTO){


        Message message = messageRepository.findById(messageId).get();

        try {
            Reaction reaction = Reaction.fromString(messageReactionDTO.getReaction());
            // The emoji is valid, proceed
            MessageReaction messageReaction = new MessageReaction(message, message.getUser(), reaction);
            messageReactionRepository.save(messageReaction);
            messageRepository.save(message);
            ReplyDTO reply = new ReplyDTO("Message Sent Successfully");
            return new ResponseEntity<>(reply, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // The emoji is invalid, error handling
            ReplyDTO reply = new ReplyDTO("Message failed to send, emoji does not exist");
            return new ResponseEntity<>(reply, HttpStatus.BAD_REQUEST);
        }


    }


}