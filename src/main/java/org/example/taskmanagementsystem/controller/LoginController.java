package org.example.taskmanagementsystem.controller;

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
import org.example.taskmanagementsystem.model.Session;
import org.example.taskmanagementsystem.services.UserService;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private ImageView imageView;
    public void initialize() {
        String imagePath = "/org/example/taskmanagementsystem/view/image/tree-removebg-preview.png";  // Укажите правильный путь к изображению в ресурсах
        Image image = new Image(getClass().getResourceAsStream(imagePath)); // Загружаем изображение из ресурсов
        imageView.setImage(image); // Устанавливаем изображение в ImageView
        loginButton.setOnAction(event -> handleLogin());
    }
    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Ошибка", "Пожалуйста, заполните все поля.", Alert.AlertType.ERROR);
        } else {
            int userId = UserService.getUserIdIfValid(username, password);

            if (userId != -1) {
                showAlert("Успех", "Авторизация успешна!", Alert.AlertType.INFORMATION);

                Session.getInstance().setUserId(userId);

                openMainView();
                closeCurrentWindow();
            } else {
                showAlert("Ошибка", "Неверный логин или пароль.", Alert.AlertType.ERROR);
            }
        }
    }
    @FXML
    public void openMainView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/taskmanagementsystem/view/MainView.fxml"));
            Scene scene = new Scene(loader.load());

            Stage mainStage = new Stage();
            mainStage.setTitle("Добавить проект");
            mainStage.setScene(scene);
            mainStage.setHeight(600);
            mainStage.setWidth(800);

            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void closeCurrentWindow() {
        // Закрытие текущего окна
        ((javafx.stage.Stage) loginButton.getScene().getWindow()).close();
    }
}
