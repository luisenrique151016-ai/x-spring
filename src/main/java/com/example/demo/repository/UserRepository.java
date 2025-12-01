package com.example.demo.repository;

import com.example.app.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Integer> {
    List<User> findByNombre(String nombre);
}