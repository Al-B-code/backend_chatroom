package com.group_1.backend_chatroom.services;

import com.group_1.backend_chatroom.dtos.MessageReactionDTO;
import com.group_1.backend_chatroom.models.Message;
import com.group_1.backend_chatroom.models.MessageReaction;
import com.group_1.backend_chatroom.models.Reaction;
import com.group_1.backend_chatroom.repositories.MessageReactionRepository;
import com.group_1.backend_chatroom.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

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

    public List<Message> getMessagesByReactionId(Long ReactionId){
        List<MessageReaction> messageReactions = messageReactionRepository.findAllMessagesById(ReactionId);
        List<Message> messages = new ArrayList<>();

        for (MessageReaction messageReaction: messageReactions) {
            Message message = messageReaction.getMessage();
            messages.add(message);
        }
        return messages;
    }

    public List<Message> getAllMessagesByReaction(MessageReactionDTO messageReactionDTO){
        Reaction reaction = Reaction.fromString(messageReactionDTO.getReaction());
        List<MessageReaction> messageReactions = messageReactionRepository.findAllMessagesByReaction(reaction);
        List<Message> messages = new ArrayList<>();

        for (MessageReaction messageReaction: messageReactions) {
            Message message = messageReaction.getMessage();
            messages.add(message);
        }
        return messages;

    }



}
