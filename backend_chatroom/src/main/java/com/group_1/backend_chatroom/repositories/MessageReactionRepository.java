package com.group_1.backend_chatroom.repositories;

import com.group_1.backend_chatroom.models.Message;
import com.group_1.backend_chatroom.models.MessageReaction;
import com.group_1.backend_chatroom.models.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageReactionRepository extends JpaRepository<MessageReaction, Long>{

    List<MessageReaction> findAllMessagesById(Long messageReactionId);

    List<MessageReaction> findAllMessagesByReaction(Reaction reaction);


}
