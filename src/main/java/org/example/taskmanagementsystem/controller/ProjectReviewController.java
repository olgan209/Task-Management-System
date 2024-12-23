package org.example.taskmanagementsystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.example.taskmanagementsystem.model.Project;
import org.example.taskmanagementsystem.model.Task;
import org.example.taskmanagementsystem.services.TaskService;

import java.io.IOException;
import java.util.List;

public class ProjectReviewController {

    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private TableView<Task> taskTableView;
    @FXML
    private TableColumn<Task, Integer> taskIdColumn;
    @FXML
    private TableColumn<Task, String> taskNameColumn;
    @FXML
    private TableColumn<Task, String> taskDescriptionColumn;

    private Project currentProject;

    @FXML
    public void initialize(Project project, List<Task> tasks) {
        System.out.println("Проект: " + project.getName());
        currentProject = project;
        nameLabel.setText(project.getName());
        descriptionLabel.setText(project.getDescription());
        taskTableView.getItems().setAll(tasks);
        taskIdColumn.setCellValueFactory(cellData -> cellData.getValue().getTaskIdProperty().asObject());
        taskNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        taskDescriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescriptionProperty());
    }

    @FXML
    private void openTaskForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/taskmanagementsystem/view/TaskView.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Добавить задачу");

            TaskController taskController = loader.getController();
            taskController.setProjectId(currentProject.getId());

            stage.setOnHidden(event -> refreshTaskTable());

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshTaskTable() {
        List<Task> tasks = new TaskService().getTasksByProjectId(currentProject.getId());
        taskTableView.getItems().setAll(tasks);
    }

}
