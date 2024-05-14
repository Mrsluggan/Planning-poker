package com.planningpokerbackend.planningpokerbackend.services;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final MongoOperations mongoOperations;

    public UserService (MongoOperations mongoOperations) {
        this.mongoOperations=mongoOperations;
    }
}


