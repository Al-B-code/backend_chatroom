package com.group_1.backend_chatroom.components;


import com.group_1.backend_chatroom.models.*;
import com.group_1.backend_chatroom.repositories.ChatroomRepository;
import com.group_1.backend_chatroom.repositories.MessageRepository;
import com.group_1.backend_chatroom.repositories.UserChatroomAssociationRepository;
import com.group_1.backend_chatroom.services.ChatroomService;
import com.group_1.backend_chatroom.services.MessageService;
import com.group_1.backend_chatroom.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {


    @Autowired
    UserService userService;

    @Autowired
    ChatroomService chatroomService;

    @Autowired
    MessageService messageService;

    @Autowired
    UserChatroomAssociationRepository userChatroomAssociationRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {


        User ryan = new User("ryanAir", "Ryan@BNTA.com", Role.ADMIN);
        userService.addUser(ryan);

        User albert = new User("alb", "Alb@BNTA.com", Role.ADMIN);
        userService.addUser(albert);


        Chatroom chatroom = new Chatroom("BNTA Chatroom");
        chatroomService.addChatroom(chatroom);


        Chatroom chatroom2= new Chatroom("BNTA Trainer Chatroom");
        chatroomService.addChatroom(chatroom2);

        Message message = new Message("Hello world!", chatroom, ryan);
        messageService.addMessage(message);
        chatroom.addMessage(message);

        UserChatroomAssociation userChatroomAssociation = new UserChatroomAssociation(ryan, chatroom);
        userChatroomAssociationRepository.save(userChatroomAssociation);

    }




}
