package com.group_1.backend_chatroom.services;

import com.group_1.backend_chatroom.models.User;
import com.group_1.backend_chatroom.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class userService {

   @Autowired
    UserRepository userRepository;

   public List<User> users(){
       return userRepository.findAll();
   }

}
