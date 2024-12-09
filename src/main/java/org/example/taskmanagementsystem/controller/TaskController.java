package org.example.taskmanagementsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import org.example.taskmanagementsystem.model.Task;
import org.example.taskmanagementsystem.model.TaskStatus;
import org.example.taskmanagementsystem.services.TaskService;

import java.time.LocalDateTime;

public class TaskController {

    @FXML
    private TextField taskNameField;
    @FXML
    private TextArea taskDescriptionField;
    @FXML
    private ComboBox<String> userComboBox;
    @FXML
    private ComboBox<String> priorityComboBox;
    @FXML
    private DatePicker dueDatePicker;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    private TaskService taskService;

    public TaskController() {
        taskService = new TaskService();
    }

    @FXML
    public void initialize() {
        // Настройка ComboBox с данными (можно изменить на реальные данные пользователей)
        userComboBox.getItems().addAll("Пользователь 1", "Пользователь 2", "Пользователь 3");
        priorityComboBox.getItems().addAll("Высокий", "Средний", "Низкий");

        // Обработчики событий
        saveButton.setOnAction(event -> saveTask());
        cancelButton.setOnAction(event -> cancelTask());
    }

    private void saveTask() {
        // Получение данных из UI
        String name = taskNameField.getText();
        String description = taskDescriptionField.getText();
        String user = userComboBox.getValue();
        String priority = priorityComboBox.getValue();
        LocalDateTime deadline = dueDatePicker.getValue() != null ? dueDatePicker.getValue().atStartOfDay() : null;

        // Создание объекта Task
        Task task = new Task(0, name, description, TaskStatus.valueOf(priority.toUpperCase()), 1, deadline);

        // Сохранение задачи через сервис
        taskService.createTask(task);
    }

    private void cancelTask() {
        // Очистка полей
        taskNameField.clear();
        taskDescriptionField.clear();
        userComboBox.setValue(null);
        priorityComboBox.setValue(null);
        dueDatePicker.setValue(null);
    }
}
