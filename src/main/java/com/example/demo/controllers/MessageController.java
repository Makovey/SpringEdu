package com.example.demo.controllers;

import com.example.demo.db.entity.Message;
import com.example.demo.db.repo.MessageRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("message")
public class MessageController {

    MessageRepo messageRepo;

    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    List<Message> getMessages() {
        return StreamSupport
                .stream(messageRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    Message getMessageById(@PathVariable("id") int id) {
        return findMessageOrThrowNPE(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<String> createMessage(@RequestBody Message message) {
        messageRepo.save(message);
        return ResponseEntity.ok("Created new message " + message.getId());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Message updateMessage(@PathVariable int id, @RequestBody Message message) {
        return messageRepo.findById(id)
                .map(old -> {
                    old.setText(message.getText());
                    old.setCreatedAt(Date.valueOf(LocalDate.now()));
                    return messageRepo.save(old);
                })
                .orElseThrow(() -> new NullPointerException("Not found message with id " + id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteMessage(@PathVariable int id) {
        messageRepo.delete(findMessageOrThrowNPE(id));
        return ResponseEntity.ok("Deleted message with id " + id);
    }

    private Message findMessageOrThrowNPE(int id) {
        return messageRepo
                .findById(id)
                .orElseThrow(() -> new NullPointerException("Not found message with id " + id));
    }
}
