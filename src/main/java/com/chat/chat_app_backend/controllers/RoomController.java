package com.chat.chat_app_backend.controllers;

import com.chat.chat_app_backend.entities.Message;
import com.chat.chat_app_backend.entities.Room;
import com.chat.chat_app_backend.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody String roomId) {
        Room createdRoom = roomService.createRoom(roomId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> joinRoom(@PathVariable String roomId) {
        Room room = roomService.getRoom(roomId);
        return ResponseEntity.ok(room);
    }

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(
            @PathVariable String roomId,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "20", required = false) int size
    ) {
        List<Message> messages = roomService.getMessages(roomId, page, size);
        return ResponseEntity.ok(messages);
    }
}
