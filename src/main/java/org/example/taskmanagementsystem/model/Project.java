package org.example.taskmanagementsystem.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Project {
    private Integer id; // Используется Integer для возможности работы с null
    private String name;
    private String description;
    private IntegerProperty taskId = new SimpleIntegerProperty(); // Инициализация

    // Конструктор без id
    public Project(String name, String description) {
        this(null, name, description);
    }

    // Конструктор с id
    public Project(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Геттеры и сеттеры
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public int getTaskId() {
        return taskId.get();
    }

    public void setTaskId(int taskId) {
        this.taskId.set(taskId);
    }

    public IntegerProperty taskIdProperty() { // Метод taskIdProperty для привязки данных
        return taskId;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", taskId=" + taskId.get() +
                '}';
    }
}
