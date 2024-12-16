package org.example.taskmanagementsystem.model;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class Task {
    private IntegerProperty taskId;
    private StringProperty name;
    private StringProperty description;
    private ObjectProperty<TaskStatus> status;
    private IntegerProperty projectId;
    private ObjectProperty<LocalDateTime> deadline;

    public Task(int taskId, String name, String description, TaskStatus status, int projectId, LocalDateTime deadline) {
        this.taskId = new SimpleIntegerProperty(taskId);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.status = new SimpleObjectProperty<>(status);
        this.projectId = new SimpleIntegerProperty(projectId);
        this.deadline = new SimpleObjectProperty<>(deadline);
    }

    public int getTaskId() {
        return taskId.get();
    }

    public void setTaskId(int taskId) {
        this.taskId.set(taskId);
    }

    public IntegerProperty getTaskIdProperty() { // Изменено имя метода
        return taskId;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty getDescriptionProperty() {
        return description;
    }

    public TaskStatus getStatus() {
        return status.get();
    }

    public void setStatus(TaskStatus status) {
        this.status.set(status);
    }

    public ObjectProperty<TaskStatus> getStatusProperty() {
        return status;
    }

    public int getProjectId() {
        return projectId.get();
    }

    public void setProjectId(int projectId) {
        this.projectId.set(projectId);
    }

    public IntegerProperty projectIdProperty() {
        return projectId;
    }

    public LocalDateTime getDeadline() {
        return deadline.get();
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline.set(deadline);
    }

    public ObjectProperty<LocalDateTime> deadlineProperty() {
        return deadline;
    }
}
