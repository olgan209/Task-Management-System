package org.example.taskmanagementsystem.controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.example.taskmanagementsystem.model.Project;
import org.example.taskmanagementsystem.services.ProjectService;

import java.io.IOException;

public class MainController extends Application {

    @FXML
    private TableView<Project> projectTableView; // Изменено на отображение проектов
    @FXML
    private TableColumn<Project, Integer> idColumn;
    @FXML
    private TableColumn<Project, String> nameColumn;
    @FXML
    private TableColumn<Project, String> descriptionColumn;

    private final ProjectService projectService = new ProjectService();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Загружаем FXML файл
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/taskmanagementsystem/view/ProjectView.fxml"));

        // Устанавливаем сцену и показываем окно
        primaryStage.setTitle("Task Management System - User View");
        primaryStage.setScene(new Scene(root, 800, 600)); // Устанавливаем размер окна
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        // Настраиваем колонки
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Загружаем данные из базы данных
        loadProjects();
    }

    private void loadProjects() {
        ObservableList<Project> projects = FXCollections.observableArrayList(projectService.getAllProjects());
        projectTableView.setItems(projects);
    }

    @FXML
    public void openProjectForm() {
        try {
            // Загружаем FXML для страницы добавления задачи
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/taskmanagementsystem/view/ProjectView.fxml"));
            Scene scene = new Scene(loader.load());

            // Создаем новое окно
            Stage taskStage = new Stage();
            taskStage.setTitle("Добавить проект");
            taskStage.setScene(scene);

            // Показываем новое окно
            taskStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args); // Запуск JavaFX приложения
    }
}
