package com.planningpokerbackend.planningpokerbackend.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.planningpokerbackend.planningpokerbackend.models.Task;
import com.planningpokerbackend.planningpokerbackend.services.ProjectService;
import com.planningpokerbackend.planningpokerbackend.services.TaskService;
import com.planningpokerbackend.planningpokerbackend.services.UserService;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
@RestController
public class TaskController {

    private TaskService taskservice;

    public TaskController(TaskService taskservice) {
        this.taskservice = taskservice;
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
    public Task createNewTask(@PathVariable("projectId") String projectId, @RequestBody Task task) {
        return taskservice.createNewTask(projectId, task);
    }

    @PutMapping("/manageTaskTimer/{taskId}")
    public Task startTask(@PathVariable("taskId") String id) {

        Task task = taskservice.getTaskById(id);
        if (!task.isTimerRunning()) {
            return taskservice.startTask(id);
        } else {
            return taskservice.stopTask(id);

        }
    }

    @DeleteMapping("/removeTask/{taskId}")
    public void removeTask(@PathVariable("taskId") String id){
       taskservice.removeTask(id);
    }

    @PostMapping("/timeEstimation/{taskId}/{userId}/{timeEstimation}")
    public ResponseEntity<Task> updateTaskTimeEstimation(@PathVariable("taskId") String taskId, @PathVariable("userId") String userId, @PathVariable("timeEstimation") int timeEstimation) {

        return taskservice.updateTaskTimeEstimation(taskId, userId, timeEstimation);
    }






}
