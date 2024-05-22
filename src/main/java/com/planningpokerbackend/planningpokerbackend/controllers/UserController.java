package com.planningpokerbackend.planningpokerbackend.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.planningpokerbackend.planningpokerbackend.models.Project;
import com.planningpokerbackend.planningpokerbackend.models.User;
import com.planningpokerbackend.planningpokerbackend.services.ProjectService;
import com.planningpokerbackend.planningpokerbackend.services.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    private final UserService userService;
    private final ProjectService projectService;

    public UserController(UserService userservice, ProjectService projectService) {
        this.userService = userservice;
        this.projectService = projectService;
    }

    @GetMapping("/user")
    public List <User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/project/{id}")
    public List <User> getAllUsersForProject(@PathVariable String id) {
        return userService.getAllUsersForProject(id); 
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return userService.registerUser(user.getUsername(), user.getPassword());
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.login(user.getUsername(), user.getPassword());
    }

    @PostMapping("/joinProject/{projectId}/user/{userId}")
    public Project joinProject(@PathVariable String projectId, @PathVariable String userId) {
        User user = userService.getUserById(userId);
        return projectService.addUserToProject(projectId, user);
    }

    @PostMapping("/leaveProject/{projectId}/user/{userId}")
    public Project leaveProject(@PathVariable String projectId, @PathVariable String userId) {
        User user = userService.getUserById(userId);
        return projectService.removeUserFromProject(projectId, user);
    }


    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    } 

}
