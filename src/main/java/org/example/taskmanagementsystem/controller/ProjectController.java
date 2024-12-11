package org.example.taskmanagementsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.taskmanagementsystem.model.Project;

public class ProjectController {

    @FXML
    private TextField projectNameField;
    @FXML
    private TextArea projectDescriptionField;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;

    // Переменная для хранения текущего проекта
    private Project currentProject;

    // Инициализация контроллера
    @FXML
    public void initialize() {
        // Инициализация компонентов, если необходимо
    }

    // Метод для сохранения проекта
    @FXML
    public void saveProject() {
        // Проверка обязательных полей
        if (projectNameField.getText().isEmpty() || projectDescriptionField.getText().isEmpty()) {
            showAlert("Ошибка", "Пожалуйста, заполните все поля.");
            return;
        }

        // Создание нового проекта
        if (currentProject == null) {
            currentProject = new Project(0, projectNameField.getText(), projectDescriptionField.getText());
        } else {
            currentProject.setName(projectNameField.getText());
            currentProject.setDescription(projectDescriptionField.getText());
        }

        // В данном случае можно сохранить проект в базе данных или другом хранилище

        showAlert("Успех", "Проект сохранен.");

        closeCurrentWindow();
    }

    // Метод для удаления проекта
    @FXML
    public void deleteProject() {
        // Здесь можно реализовать удаление проекта из базы данных или хранилища
        if (currentProject != null) {
            // Очистка текущего проекта
            currentProject = null;
            projectNameField.clear();
            projectDescriptionField.clear();
            showAlert("Успех", "Проект удален.");
        } else {
            showAlert("Ошибка", "Нет проекта для удаления.");
        }
    }

    // Метод для отображения сообщений
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Геттер для текущего проекта
    public Project getCurrentProject() {
        return currentProject;
    }

    // Сеттер для текущего проекта
    public void setCurrentProject(Project currentProject) {
        this.currentProject = currentProject;
        // Заполнение полей данными проекта, если они существуют
        if (currentProject != null) {
            projectNameField.setText(currentProject.getName());
            projectDescriptionField.setText(currentProject.getDescription());
        }
    }

    private void closeCurrentWindow() {
        // Закрытие текущего окна
        ((javafx.stage.Stage) saveButton.getScene().getWindow()).close();
    }
}
