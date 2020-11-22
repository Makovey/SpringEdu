package com.example.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MessageControllerTest {

    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    public void setUp(WebApplicationContext wac, RestDocumentationContextProvider rdcp) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(documentationConfiguration(rdcp))
                .build();
    }

    @Test
    @Order(1)
    @SneakyThrows
    void createMessage() {
        String url = "/message";
        String message = "Hey, it's my new message";
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(message))
                .andExpect(content().string("Created new message " + message))
                .andExpect(status().isOk()).andDo(document(url + " {method-name}"));
    }

    @Test
    @SneakyThrows
    void getMessages() {
        String url = "/message";
        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document(url + " {method-name}"));
    }

    @Test
    @SneakyThrows
    void getMessageById() {
        String url = "/message/{id}";
        mockMvc.perform(get(url, "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document(url + " {method-name}"));
    }

    @Test
    @SneakyThrows
    void updateMessage() {
        String url = "/message/{id}";
        String message = "I'm update this message";
        String pathVariable = "1";
        mockMvc.perform(put(url, pathVariable)
                .contentType(MediaType.APPLICATION_JSON)
                .content(message))
                .andExpect(content().string("Updated message with id " + pathVariable))
                .andExpect(status().isOk()).andDo(document(url + " {method-name}"));
    }

    @Test
    @SneakyThrows
    void deleteMessage() {
        String url = "/message/{id}";
        String pathVariable = "1";
        mockMvc.perform(delete(url, pathVariable)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("Deleted message with id " + pathVariable))
                .andExpect(status().isOk()).andDo(document(url + " {method-name}"));
    }
}