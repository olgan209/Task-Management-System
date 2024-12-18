package org.example.taskmanagementsystem.services;

import org.example.taskmanagementsystem.model.Task;
import org.example.taskmanagementsystem.model.TaskStatus;
import org.example.taskmanagementsystem.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final String url = "jdbc:postgresql://localhost:5432/Tms";
    private static final String username = "postgres";
    private static final String password = "1234";

    private Connection connection;

    public UserService(Connection connection) {
        this.connection = connection;
    }

    public UserService() {
    }

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
        String sql = "INSERT INTO User_table (name, email, password) VALUES (?,?,?)";
        try{
            Connection conn = this.connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
            System.out.println("User created");
            conn.close();
        } catch (SQLException e){
            System.out.println(e.toString());
        }
    }
    public User getUser(int id){
        String sql = "SELECT * FROM User_table WHERE userId = ?";
        try{
            Connection conn = this.connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new User(
                        rs.getInt("userId"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
        } catch(SQLException e){
            System.out.println(e.toString());
        }
        return null;
    }

    public static int getUserIdIfValid(String username, String password) {
        int userId = -1;
        String query = "SELECT userId FROM User_table WHERE name = ? AND password = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("UserId");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }


    public static boolean validateUser(String name, String password) {
        String query = "SELECT * FROM User_table WHERE name = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, password);

            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();  // Если пользователь найден, возвращаем true
        } catch (SQLException e) {
            System.err.println("Ошибка при запросе к базе данных: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<User> getAllUser(){
        String sql = "SELECT * FROM User_table";
        ArrayList<User> users = new ArrayList<>();
        try(Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                users.add(new User(
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

            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),  // Assuming the task has an 'id' column
                        rs.getString("name"),
                        rs.getString("description"),
                        TaskStatus.valueOf(rs.getString("status")),
                        rs.getInt("id_project"),
                        rs.getTimestamp("taskDeadline").toLocalDateTime() // Convert Timestamp to LocalDateTime
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching tasks: " + e.toString());
        }

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
