package com.example.demo.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class HelloServiceTest {

    @Autowired
    HelloService helloService;

    @Test
    void getNames() {
        List<String> names = List.of("Spring", "Idea", "Telegram");

        names.forEach(x -> helloService.addName(x));

        List<String> resultNames = helloService.getNames();
        assertTrue(resultNames.containsAll(names));
    }
}