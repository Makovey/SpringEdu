package com.example.demo.db.repo;

import com.example.demo.db.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    List<User> findAllByIdAfter(int id);
}
