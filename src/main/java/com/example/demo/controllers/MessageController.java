package com.example.demo.controllers;

import com.example.demo.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {

    MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public Map<Integer, String> getMessages() {
        return messageService.getMessages();
    }

    @GetMapping("/{id}")
    public Map<Integer, String> getMessageById(@PathVariable("id") int id) {
        return messageService.getMessageById(id);
    }

    @PostMapping
    public ResponseEntity<String> createMessage(@RequestBody String body) {
        messageService.addMessage(body);
        return ResponseEntity.ok("Created new message " + body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMessage(@PathVariable int id, @RequestBody String body) {
        messageService.changeMessageById(id, body);
        return ResponseEntity.ok("Updated message with id " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable int id) {
        messageService.deleteMessageById(id);
        return ResponseEntity.ok("Deleted message with id " + id);
    }
}
