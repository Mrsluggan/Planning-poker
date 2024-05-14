package com.planningpokerbackend.planningpokerbackend.services;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    
    private final MongoOperations mongoOperations;

    public ProjectService (MongoOperations mongoOperations) {
        this.mongoOperations=mongoOperations;
    }
}
