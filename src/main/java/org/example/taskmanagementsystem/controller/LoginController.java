package org.example.taskmanagementsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.taskmanagementsystem.services.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private ImageView imageView;

    // Метод для инициализации контроллера
    public void initialize() {
        String imagePath = "/org/example/taskmanagementsystem/view/image/tree-removebg-preview.png";  // Укажите правильный путь к изображению в ресурсах
        Image image = new Image(getClass().getResourceAsStream(imagePath)); // Загружаем изображение из ресурсов
        imageView.setImage(image); // Устанавливаем изображение в ImageView
        loginButton.setOnAction(event -> handleLogin());
    }

    // Метод для обработки логина
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Ошибка", "Пожалуйста, заполните все поля.", Alert.AlertType.ERROR);
        } else {
            if (validateUser(username, password)) {
                showAlert("Успех", "Авторизация успешна!", Alert.AlertType.INFORMATION);
                // Переход на следующую сцену, если нужно
            } else {
                showAlert("Ошибка", "Неверный логин или пароль.", Alert.AlertType.ERROR);
            }
        }
    }

    // Метод для проверки пользователя в базе данных
    private boolean validateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();  // Если пользователь найден, возвращаем true
        } catch (SQLException e) {
            System.err.println("Ошибка при запросе к базе данных: " + e.getMessage());
            return false;
        }
    }

    // Метод для отображения всплывающих сообщений
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
