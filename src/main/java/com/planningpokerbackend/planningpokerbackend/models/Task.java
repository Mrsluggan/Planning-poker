package com.planningpokerbackend.planningpokerbackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Tasks")
public class Task {
    @Id
    private String id;
    private String name;
    private String projectId;
    private long totalTime;
    private long startTime; 
    private boolean timerRunning;
    
    public Task(String name, String projectId) {
        this.name = name;
        this.projectId=projectId;
        this.totalTime = 0;
        this.startTime = 0;
        this.timerRunning = false;
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
            startTime = System.currentTimeMillis() / 60000;
            timerRunning = true;
        }
    }

    public void pauseTimer() {
        if (timerRunning) {
            long currentTime = System.currentTimeMillis()/ 60000;
            totalTime += (currentTime - startTime);
            startTime = 0; 
            timerRunning = false; 
        }
    }

    public void updateTimer() {
        if (timerRunning) {
            long currentTime = System.currentTimeMillis() / 60000;
            totalTime = totalTime + (currentTime - startTime);
            startTime = currentTime;
        }
    }
}

