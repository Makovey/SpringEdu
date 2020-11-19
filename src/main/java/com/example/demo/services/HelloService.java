package com.example.demo.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HelloService {
    List<String> names = new ArrayList<>();

    public void addName(String name) {
        names.add(name);
    }

    public List<String> getNames() {
        return names;
    }
}
