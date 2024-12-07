package main.java.org.example.taskmanagementsystem.services;

import main.java.org.example.taskmanagementsystem.model.Task;
import main.java.org.example.taskmanagementsystem.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
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

    public void createUser(User user){
        String sql = "INSERT INTO users(id, name, email) VALUES (?,?,?)";
        try{
            Connection conn = this.connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
            System.out.println("User created");
            conn.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
    }
    public User getUser(int id){
        String sql = "SELECT * FROM users WHERE id = ?";
        try{
            Connection conn = this.connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
        } catch(SQLException e){
            System.out.println(e.toString());
        }
        return null;
    }
    public ArrayList<User> getAllUser(){
        String sql = "SELECT * FROM users";
        ArrayList<User> users = new ArrayList<>();
        try(Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                users.add(new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")));
            }
        } catch(SQLException e){
            System.out.println(e.toString());
        }
        return users;
    }
    public User getUserTasks(int userId) {
        String sql = "SELECT * FROM tasks WHERE user_id = ?";  // Adjust this query if necessary based on your database schema
        List<Task> tasks = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId); // Set the userId in the SQL query
            ResultSet rs = ps.executeQuery();

            // Iterate over the results and create Task objects
            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),  // Assuming the task has an 'id' column
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getInt("id_project"),
                        rs.getTimestamp("taskDeadline").toLocalDateTime() // Convert Timestamp to LocalDateTime
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching tasks: " + e.toString());
        }

        // Assuming User class has a setTasks() method
        User user = getUser(userId);  // Fetch the user by their ID
        user.setTasks(tasks);  // Set the list of tasks for the user

        return user;
    }


    public void updateUser(User user){
        String sql = "UPDATE employee SET name = ?, position = ?, hireDate = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
            System.out.println("User updated");

        } catch(SQLException e){
            System.out.println(e.toString());
        }
    }

    public void deleteUser(int id){
        String sql = "DELETE FROM users WHERE id = ?";
        try(Connection conn = this.connect();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("User deleted");
        } catch(SQLException e){
            System.out.println(e.toString());
        }
    }
}
