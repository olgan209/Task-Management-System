package org.example.taskmanagementsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.taskmanagementsystem.model.Project;
import org.example.taskmanagementsystem.services.ProjectService;

public class ProjectController {

    @FXML
    private TextField projectNameField;
    @FXML
    private TextArea projectDescriptionField;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;

    private Project currentProject;

    private ProjectService projectService;

    @FXML
    public void initialize() {
        this.projectService = new ProjectService();
    }

    @FXML
    public void saveProject() {
        if (projectNameField.getText().isEmpty() || projectDescriptionField.getText().isEmpty()) {
            showAlert("Ошибка", "Пожалуйста, заполните все поля.");
            return;
        }
        if (currentProject == null) {
            currentProject = new Project(projectNameField.getText(), projectDescriptionField.getText());
        } else {
            currentProject.setName(projectNameField.getText());
            currentProject.setDescription(projectDescriptionField.getText());
        }
        projectService.createProject(currentProject);

        showAlert("Успех", "Проект сохранен.");

        closeCurrentWindow();
    }

    @FXML
    public void deleteProject() {
        if (currentProject != null) {
            currentProject = null;
            projectNameField.clear();
            projectDescriptionField.clear();
            showAlert("Успех", "Проект удален.");
        } else {
            showAlert("Ошибка", "Нет проекта для удаления.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Project getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(Project currentProject) {
        this.currentProject = currentProject;
        if (currentProject != null) {
            projectNameField.setText(currentProject.getName());
            projectDescriptionField.setText(currentProject.getDescription());
        }
    }

    private void closeCurrentWindow() {
        ((javafx.stage.Stage) saveButton.getScene().getWindow()).close();
    }
}
