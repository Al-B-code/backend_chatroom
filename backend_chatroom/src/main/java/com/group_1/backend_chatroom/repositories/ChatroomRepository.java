package com.group_1.backend_chatroom.repositories;

import com.group_1.backend_chatroom.models.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
}
