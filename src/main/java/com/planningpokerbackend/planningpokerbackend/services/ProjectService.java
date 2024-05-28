package com.planningpokerbackend.planningpokerbackend.services;

import java.util.List;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.planningpokerbackend.planningpokerbackend.models.Project;
import com.planningpokerbackend.planningpokerbackend.models.Task;
import com.planningpokerbackend.planningpokerbackend.models.User;

@Service
public class ProjectService {

    private final MongoOperations mongoOperations;

    public ProjectService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Project createProject(Project project) {
        mongoOperations.save(project);
        return project;
    }

    public List<Project> getAllProjects() {
        return mongoOperations.findAll(Project.class);
    }

    public List<Project> getAllProjectsByUserId(String userId) {
        Query query = new Query(Criteria.where("users._id").is(userId));
        return mongoOperations.find(query, Project.class);
    }

    public Project getProjectById(String projectId) {
        Query query = new Query(Criteria.where("id").is(projectId));
        Project project = mongoOperations.findOne(query, Project.class);

        if (projectId!=null) {
            List<Task> updatedTasks = getUpdatedTasksForProject(projectId);
            project.setTasks(updatedTasks);
        } else {
            return null;
        }
        return project;
    }

    private List<Task> getUpdatedTasksForProject(String projectId) {
        Query query = new Query(Criteria.where("projectId").is(projectId));
        return mongoOperations.find(query, Task.class);
    }

    public Project addUserToProject(String projectId, User user) {
        Project project = getProjectById(projectId);
        if (project != null && user != null && !project.getUsers().contains(user)) {
            project.addUser(user);
            mongoOperations.save(project);
        }
        return project;
    }

    public Project removeUserFromProject(String projectId, User user) {
        Project project = getProjectById(projectId);
        if (project != null && user != null) {
            project.removeUser(user);
            mongoOperations.save(project);
        }
        return project;
    }

    public void deleteProject(String projectId) {
        Query query = new Query(Criteria.where("id").is(projectId));
        Project projectToDelete = mongoOperations.findOne(query, Project.class);

        if (projectToDelete != null) {
            mongoOperations.remove(projectToDelete);
            Query tasksQuery = new Query(Criteria.where("projectId").is(projectId));
            mongoOperations.remove(tasksQuery, Task.class);
        }
    }
}
