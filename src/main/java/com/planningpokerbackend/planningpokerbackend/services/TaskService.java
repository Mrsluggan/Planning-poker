package com.planningpokerbackend.planningpokerbackend.services;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.planningpokerbackend.planningpokerbackend.models.Task;
import com.planningpokerbackend.planningpokerbackend.models.User;

@Service
public class TaskService {

    private final MongoOperations mongoOperations;

    public TaskService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public List<Task> getAllTasks() {
        return mongoOperations.findAll(Task.class);
    }

    public Task getTaskById(String taskId) {
        return mongoOperations.findById(taskId, Task.class);
    }

    public List<Task> getTasksByProjectId(String projectId) {
        Query query = Query.query(Criteria.where("projectId").is(projectId));
        return mongoOperations.find(query, Task.class);
    }

    public Task createNewTask(String projectId, Task task) {
        task.setProjectId(projectId);
        return mongoOperations.save(task);
    }

    public void removeTask(String taskId) {
        mongoOperations.remove(getTaskById(taskId));
    }

    public Task startTask(String taskId) {
        Task task = getTaskById(taskId);
        task.startTimer();
        return mongoOperations.save(task);
    }

    public Task stopTask(String taskId) {
        Task task = getTaskById(taskId);
        task.pauseTimer();
        return mongoOperations.save(task);
    }
}
