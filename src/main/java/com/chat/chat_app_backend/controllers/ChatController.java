package com.chat.chat_app_backend.controllers;

import com.chat.chat_app_backend.entities.Message;
import com.chat.chat_app_backend.payload.MessageRequest;
import com.chat.chat_app_backend.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @MessageMapping("/sendMessage/{roomId}")//publish to this url /app/sendMessage/roomId
    @SendTo("/topic/room/{roomId}")//subscribe to this url
    public Message sendMessage(
            @DestinationVariable String roomId,
            @RequestBody MessageRequest request
    ) {
        return chatService.sendMessage(roomId, request);
    }
}
