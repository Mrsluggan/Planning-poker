package com.planningpokerbackend.planningpokerbackend.services;

import java.util.List;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    public List <User> getUserById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return mongoOperations.find(query, User.class);
    }

    public User getUsername(String username) {
        Query query = Query.query(Criteria.where("username").is(username));
        return mongoOperations.findOne(query,User.class);
    }

    public void createUser(User user) {
        mongoOperations.save(user);
    }

    public String registerUser(String username, String password) {
        if(getUsername(username) == null) {
            User user = new User(username, password);
            createUser(user);
            return "success";
        } else {
            return "Användarnamnet är upptaget";
        }
    }
}

