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

import java.io.IOException;

public class ProjectReviewController {
    @FXML
    private TableView<Task> taskTableView;
    @FXML
    private TableColumn<Task, Integer> taskIdColumn;
    @FXML
    private TableColumn<Task, String> taskNameColumn;
    @FXML
    private TableColumn<Task, String> taskDescriptionColumn;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label descriptionLabel;

    public void setProject(Project project) {
        idLabel.setText(project.getId() != null ? String.valueOf(project.getId()) : "ID не задан");
        nameLabel.setText(project.getName());
        descriptionLabel.setText(project.getDescription());

//        // Подгружаем задачи, связанные с проектом
//        ObservableList<Task> tasks = FXCollections.observableArrayList(
//                TaskService.getTasksByProjectId(project.getId())
//        );
//        taskTableView.setItems(tasks);
    }

    @FXML
    private void openTaskForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/taskmanagementsystem/view/TaskFormView.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Добавить задачу");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
