package com.planningpokerbackend.planningpokerbackend.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.planningpokerbackend.planningpokerbackend.services.ProjectService;
import com.planningpokerbackend.planningpokerbackend.services.TaskService;
import com.planningpokerbackend.planningpokerbackend.services.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
public class ProjectController {

    private ProjectService projectService;
    private UserService userService;
    private TaskService taskservice;

    public ProjectController(ProjectService projectService, UserService userService, TaskService taskservice) {
        this.projectService = projectService;
        this.userService = userService;
        this.taskservice = taskservice;
    }
    
}
