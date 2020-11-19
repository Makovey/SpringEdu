package com.example.demo.web;

import com.example.demo.services.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyController {

    HelloService helloService;

    public MyController(HelloService helloService) {
        this.helloService = helloService;
    }


    @GetMapping
    public String hello() {
        return "hello";
    }

    @PostMapping
    @RequestMapping("/hello")
    public String sayHello(String name) {
        helloService.addName(name);
        return name;
    }

    @GetMapping
    @RequestMapping("/list")
    public List<String> getNames() {
        return helloService.getNames();
    }


}
