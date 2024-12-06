package main.java.org.example.taskmanagementsystem.model;

import java.time.LocalDateTime;

public class Task {
    private int id;
    private String name;
    private String description;
    private String status;
    private int id_project;
    public LocalDateTime taskDeadline;

    public Task(int id, String name, String description, String status, int id_project, LocalDateTime taskDeadline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.id_project = id_project;
        this.taskDeadline = taskDeadline;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_project() {
        return id_project;
    }

    public void setId_project(int id_project) {
        this.id_project = id_project;
    }

    public LocalDateTime getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(LocalDateTime taskDeadline) {
        this.taskDeadline = taskDeadline;
    }
}
