package com.planningpokerbackend.planningpokerbackend.services;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    
    private final MongoOperations mongoOperations;

    public TaskService (MongoOperations mongoOperations) {
        this.mongoOperations=mongoOperations;
    }
}


