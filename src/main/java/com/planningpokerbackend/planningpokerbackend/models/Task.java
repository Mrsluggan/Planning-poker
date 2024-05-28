package com.planningpokerbackend.planningpokerbackend.models;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Tasks")
public class Task {
    @Id
    private String id;
    private String name;
    private String projectId;
    private long totalTime;
    private long startTime;
    private int timeEstimation;
    private boolean timerRunning;
    private Map<String, Integer> userTimeEstimations;

    public Task(String name, String projectId) {
        this.name = name;
        this.projectId = projectId;
        this.totalTime = 0;
        this.startTime = 0;
        this.timeEstimation = 0;
        this.timerRunning = false;
        this.userTimeEstimations = new HashMap<>();
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public Map<String, Integer> getUserTimeEstimations() {
        return userTimeEstimations;
    }

    public void setUserTimeEstimations(Map<String, Integer> userTimeEstimations) {
        this.userTimeEstimations = userTimeEstimations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public boolean isTimerRunning() {
        return timerRunning;
    }

    public void setTimerRunning(boolean timerRunning) {
        this.timerRunning = timerRunning;
    }

    public void startTimer() {
        if (!timerRunning) {
            startTime = System.currentTimeMillis() / 1000;
            timerRunning = true;
        }
    }

    public void pauseTimer() {
        if (timerRunning) {
            long currentTime = System.currentTimeMillis() / 1000;
            totalTime += (currentTime - startTime);
            startTime = 0;
            timerRunning = false;
        }
    }

    public void updateTimer() {
        if (timerRunning) {
            long currentTime = System.currentTimeMillis() / 1000;
            totalTime = totalTime + (currentTime - startTime);
            startTime = currentTime;
        }
    }

    public int getTimeEstimation() {
        return timeEstimation;
    }

    public void setTimeEstimation(int timeEstimation) {
        this.timeEstimation = timeEstimation;
    }

}
