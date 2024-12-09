package org.example.taskmanagementsystem.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/Tsm";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";
    private static Connection connection;

    // Метод для получения соединения
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Соединение с базой данных установлено!");
            } catch (SQLException e) {
                System.err.println("Ошибка при подключении к базе данных: " + e.getMessage());
            }
        }
        return connection;
    }

    // Метод для закрытия соединения
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Соединение с базой данных закрыто!");
            } catch (SQLException e) {
                System.err.println("Ошибка при закрытии соединения: " + e.getMessage());
            }
        }
    }
}
