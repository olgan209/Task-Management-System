package org.example.taskmanagementsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.taskmanagementsystem.model.User;
import org.example.taskmanagementsystem.services.UserService;

public class EditUserController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;  // Поле для пароля

    private User currentUser;
    private final UserService userService = new UserService();

    // Метод для передачи текущего пользователя
    public void setCurrentUser(User user) {
        this.currentUser = user;
        initialize();
    }

    // Метод для инициализации данных в полях
    public void initialize() {
        if (currentUser != null) {
            nameField.setText(currentUser.getName());
            emailField.setText(currentUser.getEmail());
            passwordField.setText(currentUser.getPassword());  // Если необходимо показывать старый пароль
        }
    }

    @FXML
    public void saveChanges() {
        String newName = nameField.getText();
        String newEmail = emailField.getText();
        String newPassword = passwordField.getText();  // Получаем новый пароль

        if (newName.isEmpty() || newEmail.isEmpty() || newPassword.isEmpty()) {
            // Выводим ошибку, если одно из полей пустое
            System.out.println("Имя, email и пароль не могут быть пустыми.");
            return;
        }

        // Обновляем информацию о пользователе
        currentUser.setName(newName);
        currentUser.setEmail(newEmail);
        currentUser.setPassword(newPassword);  // Устанавливаем новый пароль

        // Сохраняем изменения
        userService.updateUser(currentUser);

        // Закрываем окно редактирования
        System.out.println("Данные пользователя обновлены.");
        ((javafx.stage.Stage) nameField.getScene().getWindow()).close();
    }
}
