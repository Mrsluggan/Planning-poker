package com.planningpokerbackend.planningpokerbackend.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.planningpokerbackend.planningpokerbackend.models.Task;
import com.planningpokerbackend.planningpokerbackend.services.ProjectService;
import com.planningpokerbackend.planningpokerbackend.services.TaskService;
import com.planningpokerbackend.planningpokerbackend.services.UserService;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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

    @GetMapping("/getTasks")
    public List<Task> getAllTasks() {
        return taskservice.getAllTasks();
    }

    @GetMapping("/getTasks/{projectId}")
    public List<Task> getAllTaskFromProjectId(@PathVariable("projectId") String id) {
        return taskservice.getTasksByProjectId(id);
    }

    @GetMapping("/getTask/{taskId}")
    public Task getTaskById(@PathVariable("taskId") String id) {
        return taskservice.getTaskById(id);
    }

    @PostMapping("/newTask/{projectId}")
    public Task createNewTask(@PathVariable("projectId") String id, Task task) {
        return taskservice.createNewTask(id, task);
    }

    @PutMapping("/handleTask/{taskId}")
    public Task startTask(@PathVariable("taskId") String id) {

        Task task = taskservice.getTaskById(id);
        if (!task.isTimerRunning()) {
            return taskservice.startTask(id);
        } else {
            return taskservice.stopTask(id);

        }

    }

}
