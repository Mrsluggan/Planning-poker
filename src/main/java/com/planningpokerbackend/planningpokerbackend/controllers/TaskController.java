package com.planningpokerbackend.planningpokerbackend.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.planningpokerbackend.planningpokerbackend.services.ProjectService;
import com.planningpokerbackend.planningpokerbackend.services.TaskService;
import com.planningpokerbackend.planningpokerbackend.services.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
public class TaskController {
    
    private TaskService taskservice;
    private ProjectService projectService;
    private UserService userservice;
    
    public TaskController(TaskService taskservice, ProjectService projectService, UserService userservice) {
        this.taskservice = taskservice;
        this.projectService = projectService;
        this.userservice = userservice;
    }
    
}
