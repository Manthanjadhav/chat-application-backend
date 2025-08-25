package com.chat.chat_app_backend.services;

import com.chat.chat_app_backend.entities.Message;
import com.chat.chat_app_backend.entities.Room;
import com.chat.chat_app_backend.exceptions.RoomNotFoundException;
import com.chat.chat_app_backend.payload.MessageRequest;
import com.chat.chat_app_backend.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final RoomRepository roomRepository;

    public Message sendMessage(String roomId, MessageRequest request) {
        Room room = roomRepository.findByRoomId(request.getRoomId());

        if (room == null) {
            throw new RoomNotFoundException(request.getRoomId());
        }

        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimeStamp(LocalDateTime.now());

        room.getMessages().add(message);
        roomRepository.save(room);

        return message;
    }
}
