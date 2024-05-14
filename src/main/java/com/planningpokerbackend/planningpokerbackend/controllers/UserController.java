package com.planningpokerbackend.planningpokerbackend.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.planningpokerbackend.planningpokerbackend.services.ProjectService;
import com.planningpokerbackend.planningpokerbackend.services.TaskService;
import com.planningpokerbackend.planningpokerbackend.services.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    private UserService userservice;
    private TaskService taskservice;
    private ProjectService projectService;
    
    public UserController( UserService userservice, TaskService taskservice, ProjectService projectService) {
        this.projectService = projectService;
        this.taskservice = taskservice;
        this.userservice = userservice;
    }

}
