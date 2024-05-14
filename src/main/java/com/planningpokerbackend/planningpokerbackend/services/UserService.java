package com.planningpokerbackend.planningpokerbackend.services;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.planningpokerbackend.planningpokerbackend.models.User;

@Service
public class UserService {
    
    private final MongoOperations mongoOperations;

    public UserService (MongoOperations mongoOperations) {
        this.mongoOperations=mongoOperations;
    }

    public List <User> getAllUsers() {
        return mongoOperations.findAll(User.class);
    }
}


