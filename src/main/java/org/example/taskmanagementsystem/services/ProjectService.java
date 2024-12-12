package org.example.taskmanagementsystem.services;

import org.example.taskmanagementsystem.model.Project;
import org.example.taskmanagementsystem.model.User;

import java.sql.*;
import java.util.ArrayList;

public class ProjectService {
    private static final String url = "jdbc:postgresql://localhost:5432/Tms";
    private static final String username = "postgres";
    private static final String password = "1234";

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

    public void createProject(Project project){
        String sql = "INSERT INTO projects(name, description) VALUES(?,?)";
        try{
            Connection conn = this.connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, project.getName());
            ps.setString(2, project.getDescription());
            ps.executeUpdate();
            System.out.println("Project created");
            conn.close();
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    public void addUserToProject(int projectId, int userId){
        String sql = "INSERT INTO project_users(projectId, userId) VALUES(?,?)";
        try(Connection conn = this.connect();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, projectId);
            ps.setInt(2, userId);
            ps.executeUpdate();
            System.out.println("User added to project");
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }

    public Project getProjectById(int id){
        String sql = "SELECT * FROM projects WHERE id = ?";
        try{
            Connection conn = this.connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Project(rs.getInt("id"),
                                   rs.getString("name"),
                                   rs.getString("description")
                );
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return null;
    }
    public ArrayList<Project> getAllProjects(){
        String sql = "SELECT * FROM projects";
        ArrayList<Project> projects = new ArrayList<>();
        try(Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
                while(rs.next()){
                    projects.add(new Project(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description")));
                }
        }catch (SQLException e){
            System.out.println(e.toString());
        }
        return null;
    }
    public ArrayList<Project> getProjectsByUser(int userId) {
        ArrayList<Project> projects = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.description FROM projects p " +
                "JOIN project_users pu ON p.id = pu.project_id " +
                "WHERE pu.user_id = ?";
        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                projects.add(new Project(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching projects by user: " + e.getMessage());
        }
        return projects;
    }


    public ArrayList<User> getProjectUsers(int projectId){
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT u.id, u.username, u.email FROM users AS u " +
                      "JOIN project_users AS pu ON u.id = pu.user_id " +
                        "WHERE pu.project_id = ?";
        try(Connection conn = this.connect();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    public void updateProject(int id, Project updatedProject){
        String sql = "UPDATE projects SET name = ?, description = ? WHERE id = ?";
        try(Connection conn = this.connect();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, updatedProject.getName());
            ps.setString(2, updatedProject.getDescription());
            int rowsUpdated = ps.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("Project updated");
            } else {
                System.out.println("No project found with ID " + id);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void addTaskToProject(int projectId, int taskId){
        String sql = "UPDATE tasks SET projectId = ? WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, projectId);
            ps.setInt(2, taskId);
            ps.executeUpdate();
            System.out.println("Task added to project");
        } catch (SQLException e) {
            System.out.println("Error adding task to project: " + e.getMessage());
        }
    }

    public void removeTaskFromProject(int taskId){
        String sql = "UPDATE tasks SET project_id = NULL WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, taskId);
            ps.executeUpdate();
            System.out.println("Task removed from project");
        } catch (SQLException e) {
            System.out.println("Error removing task from project: " + e.getMessage());
        }
    }

    public void removeUserFromProject(int projectId, int userId){
        String sql = "DELETE FROM project_users WHERE projectId = ? AND userId = ?";
        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, projectId);
            ps.setInt(2, userId);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User removed from project");
            } else {
                System.out.println("No association found for given project and user");
            }
        } catch (SQLException e) {
            System.out.println("Error removing user from project: " + e.getMessage());
        }
    }

    public void deleteProjectById(int id){
         String sql = "DELETE FROM projects WHERE id = ?";
         try(Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql)){
             ps.setInt(1, id);
             int rowsDeleted = ps.executeUpdate();
             if(rowsDeleted > 0){
                 System.out.println("Project deleted");
             }else{
                 System.out.println("No project found with ID " + id);
             }
         }catch(SQLException e){
             System.out.println(e.toString());
         }
    }
}
