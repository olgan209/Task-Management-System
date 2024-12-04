package main.java.org.example.taskmanagementsystem.services;

import main.java.org.example.taskmanagementsystem.model.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskService {
    private static final String url = "";
    private static final String username = "";
    private static final String password = " ";

    Connection conn;
    public Connection connect() throws SQLException {
        try{
            conn = DriverManager.getConnection(url, username, password);
            if(conn != null){
                System.out.println("Connected to the database");
            }
            return conn;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public void createTask(Task task){}
    public void assignTaskToUser(int taskId, int userId) {}
    public Task getTaskById(int taskId) {return null;}
    public ArrayList<Task> getAllTasks() {return null;}
    public Task getTaskByUser(int userId) {return null;}
    public Task getTaskByStatus(String status) {return null;}
    public void updateTask(int taskId, Task task) {}
    public void updateTaskStatus(int taskId, String status) {}
    public void setTaskDeadline(int taskId, LocalDateTime deadline){}
    public void deleteTask(int taskId) {}
}
