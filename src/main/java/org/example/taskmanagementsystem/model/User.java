package org.example.taskmanagementsystem.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String email;
    private List<Task> userTasks;

    public User(int id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
        this.userTasks = new ArrayList<Task>();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Task> getTasks() {
        return userTasks;
    }

    public void setTasks(List<Task> userTasks) {
        this.userTasks = userTasks;
    }
}
