package org.example.taskmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.taskmanagementsystem.model.Task;
import org.example.taskmanagementsystem.model.TaskStatus;
import org.example.taskmanagementsystem.services.TaskService;
import org.example.taskmanagementsystem.services.ValidationUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

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

    private int projectId;

    public void setProjectId(int projectId) {
        this.projectId = projectId;
        System.out.println("ID текущего проекта: " + projectId);
    }

    @FXML
    public void initialize() {

        priorityComboBox.setItems(FXCollections.observableArrayList(
                Arrays.stream(TaskStatus.values())
                        .map(Enum::name)
                        .collect(Collectors.toList())
        ));

        saveButton.setOnAction(event -> saveTask());
        cancelButton.setOnAction(event -> cancelTask());
    }


    private void saveTask() {
        String name = taskNameField.getText();
        String description = taskDescriptionField.getText();
        String priority = priorityComboBox.getValue();
        LocalDateTime deadline = dueDatePicker.getValue() != null ? dueDatePicker.getValue().atStartOfDay() : null;

        Task task = new Task(
                0, // Временное значение для taskId
                name,
                description,
                TaskStatus.valueOf(priority.toUpperCase()), // Преобразование строки в TaskStatus
                1, // Укажи projectId (например, текущий проект)
                deadline
        );

        taskService.createTask(task);

        closeCurrentWindow();
    }



    private void cancelTask() {
        taskNameField.clear();
        taskDescriptionField.clear();
        priorityComboBox.setValue(null);
        dueDatePicker.setValue(null);

        ((javafx.stage.Stage) cancelButton.getScene().getWindow()).close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeCurrentWindow() {
        ((javafx.stage.Stage) saveButton.getScene().getWindow()).close();
    }
}
