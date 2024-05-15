package com.planningpokerbackend.planningpokerbackend.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.planningpokerbackend.planningpokerbackend.models.User;
import com.planningpokerbackend.planningpokerbackend.services.ProjectService;
import com.planningpokerbackend.planningpokerbackend.services.TaskService;
import com.planningpokerbackend.planningpokerbackend.services.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    private UserService userService;
    private TaskService taskService;
    private ProjectService projectService;
    
    public UserController( UserService userservice, TaskService taskservice, ProjectService projectService) {
        this.projectService = projectService;
        this.taskService = taskservice;
        this.userService = userservice;
    }

    @GetMapping("/user")
    public List <User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public List <User> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return userService.registerUser(user.getUsername(), user.getPassword());
    }
}
