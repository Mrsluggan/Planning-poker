package com.planningpokerbackend.planningpokerbackend.services;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.planningpokerbackend.planningpokerbackend.models.Project;
import com.planningpokerbackend.planningpokerbackend.models.Task;

import jakarta.annotation.PostConstruct;


@Service
public class TaskService {

    private final MongoOperations mongoOperations;
    private final ProjectService projectService;

    public TaskService(MongoOperations mongoOperations, ProjectService projectService) {
        this.mongoOperations = mongoOperations;
        this.projectService = projectService;
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
        Project project = projectService.getProjectById(projectId);
        if (project == null) {
            return null;
        }
        task.setProjectId(projectId);
        Task savedTask = mongoOperations.save(task);
        project.getTasks().add(savedTask);
        mongoOperations.save(project);
        return savedTask;
    }

    public void removeTask(String taskId) {
        Task taskToRemove = getTaskById(taskId);
        if (taskToRemove != null) {
            String projectId = taskToRemove.getProjectId();
            mongoOperations.remove(taskToRemove);
            
            Project project = projectService.getProjectById(projectId);
            if (project != null) {
                project.getTasks().removeIf(task -> task.getId().equals(taskId));
                mongoOperations.save(project);
            }
        }
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

    public Task updateTaskTimeEstimation(String taskId, String userId, int timeEstimation) {
        Task task = getTaskById(taskId);
        if (task != null) {
            
            task.getUserTimeEstimations().put(userId, timeEstimation);
            Task savedTask = mongoOperations.save(task);

            
            Project project = projectService.getProjectById(task.getProjectId());
            if (project != null) {
                
                for (Task projectTask : project.getTasks()) {
                    if (projectTask.getId().equals(taskId)) {
                        projectTask.getUserTimeEstimations().put(userId, timeEstimation);
                        break;
                    }
                }
                
                mongoOperations.save(project);
            }
            return savedTask;
        }
        return null;
    }

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    @PostConstruct
    public void startTimerUpdateTask() {
        executorService.scheduleAtFixedRate(this::updateTimers, 0, 1, TimeUnit.MINUTES);
    }

    private void updateTimers() {
        List<Task> tasks = getAllTasks();
        for (Task task : tasks) {
            task.updateTimer();
            mongoOperations.save(task);
        }
    }
}
