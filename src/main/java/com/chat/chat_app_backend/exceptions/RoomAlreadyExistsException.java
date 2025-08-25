package com.chat.chat_app_backend.exceptions;

public class RoomAlreadyExistsException extends RuntimeException {
    public RoomAlreadyExistsException(String roomId) {
        super("Room already exists with id: " + roomId);
    }
}