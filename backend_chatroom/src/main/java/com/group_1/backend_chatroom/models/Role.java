package com.group_1.backend_chatroom.models;

public enum Role {

    USER(0),
    MODERATOR(1),
    ADMIN(2);

    private final int value;

    Role(int value) {
        this.value = value;
    }

    public static Role fromInteger(int value) {
        switch (value) {
            case 0:
                return USER;
            case 1:
                return MODERATOR;
            case 2:
                return ADMIN;
            // handle other cases if needed
            default:
                throw new IllegalArgumentException("Invalid Role value: " + value);
        }
    }
}