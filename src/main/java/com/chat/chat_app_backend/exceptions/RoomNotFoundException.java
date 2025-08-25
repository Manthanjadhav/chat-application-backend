package com.chat.chat_app_backend.exceptions;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(String roomId) {
        super("Room not found with id: " + roomId);
    }
}
