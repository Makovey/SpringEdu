package com.example.demo.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageServiceTest {

    @Autowired
    MessageService messageService;

    private List<String> messages;

    private int randomIndex;

    @BeforeEach
    void setUp() {
        messages = List.of("Hello!", "Second Message", "Third Message");
        messages.forEach(x -> messageService.addMessage(x));
        randomIndex = new Random().nextInt(messages.size());
    }

    @AfterEach
    void tearDown() {
        messageService.setMessages(new HashMap<>());
        messageService.setQuantity(0);
    }

    @Test
    void addMessage() {
        assertTrue(messageService.getMessages().values().containsAll(messages));
        assertNotNull(messageService.getMessages());
    }

    @Test
    void getMessageById() {
        assertTrue(messageService.getMessageById(randomIndex).containsValue(messages.get(randomIndex)));
        assertNull(messageService.getMessageById(messages.size() + 1));
        assertNull(messageService.getMessageById(-1));
    }

    @Test
    void changeMessageById() {
        String message = "It's totally new message";
        assertTrue(messageService.changeMessageById(randomIndex, message));
        assertFalse(messageService.changeMessageById(messages.size() + 1, message));
        assertNotEquals(messageService.getMessages().get(randomIndex), messages.get(randomIndex));
    }

    @Test
    void deleteMessageById() {
        assertTrue(messageService.deleteMessageById(randomIndex));
        assertFalse(messageService.deleteMessageById(messages.size() + 1));
        assertNotEquals(messages, messageService.getMessages().values());
    }


}