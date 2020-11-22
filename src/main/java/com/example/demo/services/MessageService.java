package com.example.demo.services;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MessageService {

    @Getter
    @Setter
    private Map<Integer, String> messages = new HashMap<>();

    @Setter
    private int quantity;

    private static final String WARNING_ABSENT_ID = "Нет сообщения с таким id";

    public void addMessage(String message) {
        messages.put(quantity++, message);
    }

    public Map<Integer, String> getMessageById(int id) {
        if (messages.containsKey(id)) {
            return messages.entrySet().stream().filter(x -> x.getKey().equals(id)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
        log.warn(WARNING_ABSENT_ID);
        return null;
    }

    public boolean changeMessageById(int id, String message) {
        if (messages.containsKey(id)) {
            messages.put(id, message);
            return true;
        } else {
            log.warn(WARNING_ABSENT_ID);
            return false;
        }
    }

    public boolean deleteMessageById(int id) {
        if (messages.containsKey(id)) {
            messages.remove(id);
            quantity--;
            return true;
        } else {
            log.warn(WARNING_ABSENT_ID);
            return false;
        }
    }
}
