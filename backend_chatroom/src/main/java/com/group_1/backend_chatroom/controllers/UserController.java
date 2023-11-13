package com.group_1.backend_chatroom.controllers;

import com.group_1.backend_chatroom.models.User;
import com.group_1.backend_chatroom.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.users();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userService.getUser(id).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}