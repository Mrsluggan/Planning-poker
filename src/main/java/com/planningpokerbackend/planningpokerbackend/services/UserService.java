
package com.planningpokerbackend.planningpokerbackend.services;

import java.util.List;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.planningpokerbackend.planningpokerbackend.models.Project;
import com.planningpokerbackend.planningpokerbackend.models.User;

@Service
public class UserService {
    
    private final MongoOperations mongoOperations;
    private final ProjectService projectService;

    public UserService (MongoOperations mongoOperations, ProjectService projectService) {
        this.mongoOperations=mongoOperations;
        this.projectService=projectService;
    }

    public List <User> getAllUsers() {
        return mongoOperations.findAll(User.class);
    }

    public User getUserById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return mongoOperations.findOne(query, User.class);
    }

    public User getUsername(String username) {
        Query query = Query.query(Criteria.where("username").is(username));
        return mongoOperations.findOne(query,User.class);
    }

    public void createUser(User user) {
        mongoOperations.save(user);
    }

    public List<User> getAllUsersForProject(String projectId) {
      Project project = projectService.getProjectById(projectId);
      return project.getUsers();
    }

    public String registerUser(String username, String password) {
        if(getUsername(username) == null) {
            User user = new User(username, password);
            createUser(user);
            return "Registrering lyckades";
        } else {
            return "Användarnamnet är upptaget";
        }
    }

    public User login(String username, String password) {
        User user = getUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }


    public void deleteUser(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        mongoOperations.remove(query,User.class);
    }
}
