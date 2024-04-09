package za.co.charRoom.chatapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import za.co.charRoom.chatapp.model.ChatMessage;
import za.co.charRoom.chatapp.repository.ChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;


@RestController
public class ChatController {

    @Autowired
    ChatRepo chatRepo;

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) throws JsonProcessingException {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
       Optional<List<ChatMessage>> response = getHistory();
       //TODO: Add history to Chat
        return chatMessage;


    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        chatMessage.setChatRoomId("111");
        Timestamp instant= Timestamp.from(Instant.now());
        chatMessage.setTimestamp(instant);
        chatRepo.save(chatMessage);
        return chatMessage;
    }

    @GetMapping("/getHistory")
    public Optional<List<ChatMessage>> getHistory() {
      return  chatRepo.findByChatRoomId();


    }
}

