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
            // Валидация пользователя и получение userId
            int userId = UserService.getUserIdIfValid(username, password);

            if (userId != -1) { // Если возвращается валидный userId
                showAlert("Успех", "Авторизация успешна!", Alert.AlertType.INFORMATION);

                // Устанавливаем текущего пользователя в сессию
                Session.getInstance().setUserId(userId);

                // Переходим на главное окно
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
            // Загружаем FXML для страницы добавления задачи
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/taskmanagementsystem/view/MainView.fxml"));
            Scene scene = new Scene(loader.load());

            // Создаем новое окно
            Stage mainStage = new Stage();
            mainStage.setTitle("Добавить проект");
            mainStage.setScene(scene);
            mainStage.setHeight(600);
            mainStage.setWidth(800);

            // Показываем новое окно
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
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

    private void closeCurrentWindow() {
        // Закрытие текущего окна
        ((javafx.stage.Stage) loginButton.getScene().getWindow()).close();
    }
}
