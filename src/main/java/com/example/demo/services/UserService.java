package com.example.demo.services;

import com.example.demo.db.repo.UserRepo;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }




}
