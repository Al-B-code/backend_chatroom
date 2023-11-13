package com.group_1.backend_chatroom.models;

public enum Role {

    STUDENT (1),
    MODERATOR(2),
    ADMIN(3);

    private final int value;

    Role(int value) {
        this.value = value;
    }
}
