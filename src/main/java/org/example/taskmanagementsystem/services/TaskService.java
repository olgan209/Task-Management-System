package org.example.taskmanagementsystem.services;

import org.example.taskmanagementsystem.model.Task;
import org.example.taskmanagementsystem.model.TaskStatus;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private static final String url = "jdbc:postgresql://localhost:5432/Tms";
    private static final String username = "postgres";
    private static final String password = "1234";

    public Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database");
            return conn;
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }

    public void createTask(Task task) {
        String sql = "INSERT INTO Task(name, description, status, projectId, deadline) VALUES(?,?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.setString(3, task.getStatus().name());
            ps.setInt(4, task.getProjectId());
            ps.setTimestamp(5, Timestamp.valueOf(task.getDeadline()));
            ps.executeUpdate();
            System.out.println("Task created");
        } catch (SQLException e) {
            System.out.println("Error creating task: " + e.getMessage());
        }
    }

    public Task getTaskById(int taskId) {
        String sql = "SELECT * FROM Task WHERE taskId = ?";
        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, taskId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Task(
                        rs.getInt("taskId"),
                        rs.getString("name"),
                        rs.getString("description"),
                        TaskStatus.fromString(rs.getString("status")),
                        rs.getInt("projectId"),
                        rs.getTimestamp("deadline").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching task by ID: " + e.getMessage());
        }
        return null;
    }

    public List<Task> getTasksByProjectId(int projectId) {
        String sql = "SELECT * FROM Task WHERE projectId = ? ORDER BY deadline ASC";
        List<Task> tasks = new ArrayList<>();

        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tasks.add(new Task(
                        rs.getInt("taskId"),
                        rs.getString("name"),
                        rs.getString("description"),
                        TaskStatus.fromString(rs.getString("status")),
                        rs.getInt("projectId"),
                        rs.getTimestamp("deadline").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching tasks by projectId: " + e.getMessage());
        }
        return tasks;
    }


    public ArrayList<Task> getAllTasks() {
        String sql = "SELECT * FROM Task";
        ArrayList<Task> tasks = new ArrayList<>();
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tasks.add(new Task(
                        rs.getInt("taskId"),
                        rs.getString("name"),
                        rs.getString("description"),
                        TaskStatus.fromString(rs.getString("status")),
                        rs.getInt("projectId"),
                        rs.getTimestamp("deadline").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching tasks: " + e.getMessage());
        }
        return tasks;
    }

    public List<Task> getFiveClosestDeadlines() {
        String sql = "SELECT * FROM Task ORDER BY deadline ASC LIMIT 5";
        List<Task> tasks = new ArrayList<>();

        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String statusFromDb = rs.getString("status");
                TaskStatus status = TaskStatus.fromString(statusFromDb.replace(" ", "_"));

                tasks.add(new Task(
                        rs.getInt("taskid"),
                        rs.getString("name"),
                        rs.getString("description"),
                        status,
                        rs.getInt("projectId"),
                        rs.getTimestamp("deadline").toLocalDateTime()
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching tasks with closest deadlines: " + e.getMessage());
        }

        return tasks;
    }


    public Task getTaskByUser(int userId) {
        String sql = "SELECT * FROM Task WHERE userId = ?";
        try(Connection conn = this.connect();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Task(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        TaskStatus.fromString(rs.getString("status")),
                        rs.getInt("projectId"),
                        rs.getTimestamp("deadline").toLocalDateTime()
                );
            }
        } catch (SQLException e){
            System.out.println("Error fetching task by ID: " + e.getMessage());
        }
        return null;
    }

    public Task getTaskByStatus(String status) {
        String sql = "SELECT * FROM Task WHERE status = ?";
        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Task(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        TaskStatus.fromString(rs.getString("status")),
                        rs.getInt("projectId"),
                        rs.getTimestamp("deadline").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching task by status: " + e.getMessage());
        }
        return null;
    }

    public void updateTask(int taskId, Task task) {
        String sql = "UPDATE Task SET name = ?, description = ?, status = ?, project_id = ?, deadline = ? WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.setString(3, task.getStatus().name());
            ps.setInt(4, task.getProjectId());
            ps.setTimestamp(5, Timestamp.valueOf(task.getDeadline()));
            ps.setInt(6, taskId);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Task updated successfully");
            } else {
                System.out.println("No task found with ID " + taskId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating task: " + e.getMessage());
        }
    }

    public void updateTaskStatus(int taskId, String status) {
        String sql = "UPDATE Task SET status = ? WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, taskId);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Task status updated successfully");
            } else {
                System.out.println("No task found with ID " + taskId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating task status: " + e.getMessage());
        }
    }

    public void setTaskDeadline(int taskId, LocalDateTime deadline){
        String sql = "UPDATE Task SET deadline = ? WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(deadline));
            ps.setInt(2, taskId);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Task deadline updated successfully");
            } else {
                System.out.println("No task found with ID " + taskId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating task deadline: " + e.getMessage());
        }
    }
    public void deleteTask(int taskId) {
        String sql = "DELETE FROM Task WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, taskId);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Task deleted successfully");
            } else {
                System.out.println("No task found with ID " + taskId);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting task: " + e.getMessage());
        }
    }
}
