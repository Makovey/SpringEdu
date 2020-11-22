package com.example.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
class MyControllerTest {

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
    @SneakyThrows
    void sayHello() {
        String url = "/hello";
        mockMvc.perform(post(url)).andExpect(status().isOk()).andDo(document(url));
    }

    @Test
    @SneakyThrows
    void sayHelloz() {
        String url = "/list";
        mockMvc.perform(get(url)).andExpect(status().isOk()).andDo(document(url));
    }
}