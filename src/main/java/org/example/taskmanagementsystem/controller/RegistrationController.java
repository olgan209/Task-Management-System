package org.example.taskmanagementsystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.taskmanagementsystem.model.User;
import org.example.taskmanagementsystem.services.UserService;

import java.io.IOException;

public class RegistrationController {

    @FXML
    private ImageView backgroundImageView;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private Button openLogin;

    @FXML
    private Label registrationStatusLabel;

    private final UserService userService = new UserService();

    @FXML
    public void initialize() {
        // Путь к изображению
        String imagePath = "/org/example/taskmanagementsystem/view/image/bg.jpg";

        // Загрузка изображения
        Image image = new Image(getClass().getResourceAsStream(imagePath));

        // Установка изображения в ImageView
        backgroundImageView.setImage(image);

        registerButton.setOnAction(event -> handleRegister());
        openLogin.setOnAction(event -> openLoginForm());
    }

    @FXML
    private void handleRegister() {
        String name = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            //showStatus("Все поля должны быть заполнены!", "error");
            return;
        }

        User newUser = new User(name, email, password);

        try {
            userService.createUser(newUser); // Вызов метода для сохранения пользователя в БД
            //showStatus("Пользователь успешно зарегистрирован!", "success");
            clearFields();
            openLoginForm();
        } catch (Exception e) {
            //showStatus("Ошибка регистрации: " + e.getMessage(), "error");
            e.printStackTrace();
        }

        closeCurrentWindow();
    }

    @FXML
    private void openLoginForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/taskmanagementsystem/view/LoginView.fxml"));
            Scene scene = new Scene(loader.load());

            Stage loginStage = new Stage();
            loginStage.setScene(scene);
            loginStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        usernameField.clear();
        emailField.clear();
        passwordField.clear();
    }

    private void showStatus(String message, String styleClass) {
        registrationStatusLabel.setText(message);
        registrationStatusLabel.getStyleClass().clear();
        registrationStatusLabel.getStyleClass().add(styleClass);
    }
    private void closeCurrentWindow() {
        // Закрытие текущего окна
        ((javafx.stage.Stage) registerButton.getScene().getWindow()).close();
    }
}
