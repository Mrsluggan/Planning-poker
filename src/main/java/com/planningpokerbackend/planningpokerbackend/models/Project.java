package com.planningpokerbackend.planningpokerbackend.models;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Projects")
public class Project {
    @Id
    private String id;
    private String projectName;
    private List<Task> tasks;
    private List<User> users;

    public Project() {
        this.tasks = new ArrayList<>();
        this.users = new ArrayList<>();
    }
    public Project(String projectName) {
        this.projectName = projectName;
        this.tasks = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    public void addUser(User user) {
        this.users.add(user);
    }
}

