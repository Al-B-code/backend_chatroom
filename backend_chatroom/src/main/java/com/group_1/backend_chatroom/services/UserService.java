package com.group_1.backend_chatroom.services;

import com.group_1.backend_chatroom.models.User;
import com.group_1.backend_chatroom.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> users(){
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public void addUser(User user){
        userRepository.save(user);
    }

}