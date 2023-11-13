package com.group_1.backend_chatroom.controllers;

import com.group_1.backend_chatroom.models.User;
import com.group_1.backend_chatroom.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class userController {

    @Autowired
    userService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.users();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
