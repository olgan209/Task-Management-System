package org.example.taskmanagementsystem.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.taskmanagementsystem.services.DatabaseConnection;
import org.example.taskmanagementsystem.services.UserService;

import java.io.IOException;
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
    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Ошибка", "Пожалуйста, заполните все поля.", Alert.AlertType.ERROR);
        } else {
            if (UserService.validateUser(username, password)) {
                showAlert("Успех", "Авторизация успешна!", Alert.AlertType.INFORMATION);
                // Переход на следующую сцену, если нужно
            } else {
                showAlert("Ошибка", "Неверный логин или пароль.", Alert.AlertType.ERROR);
            }
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
