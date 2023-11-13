package com.group_1.backend_chatroom.components;


import com.group_1.backend_chatroom.models.Chatroom;
import com.group_1.backend_chatroom.models.Role;
import com.group_1.backend_chatroom.models.User;
import com.group_1.backend_chatroom.repositories.ChatroomRepository;
import com.group_1.backend_chatroom.services.ChatroomService;
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
    ChatroomRepository chatroomRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {


        User ryan = new User("ryanAir", "Ryan@BNTA.com", Role.ADMIN);
        userService.addUser(ryan);

        User albert = new User("alb", "Alb@BNTA.com", Role.ADMIN);
        userService.addUser(albert);


        Chatroom chatroom = new Chatroom("BNTA Chatroom");
        chatroomRepository.save(chatroom);


    }




}
