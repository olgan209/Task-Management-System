package org.example.taskmanagementsystem.model;

import java.time.LocalDateTime;

public class Task {
    private int id;
    private String name;
    private String description;
    private TaskStatus status;
    private int projectId;
    private LocalDateTime deadline;

    public Task(int id, String name, String description, TaskStatus status, int projectId, LocalDateTime deadline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.projectId = projectId;
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}

