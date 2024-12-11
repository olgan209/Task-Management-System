package org.example.taskmanagementsystem.controller;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class MainController extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Загружаем FXML файл
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/taskmanagementsystem/view/MainView.fxml"));

        // Устанавливаем сцену и показываем окно
        primaryStage.setTitle("Task Management System - User View");
        primaryStage.setScene(new Scene(root, 800, 600)); // Устанавливаем размер окна
        primaryStage.show();
    }

    // Метод для открытия формы добавления задачи
    @FXML
    public void openTaskForm() {
        try {
            // Загружаем FXML для страницы добавления задачи
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/taskmanagementsystem/view/TaskView.fxml"));
            Scene scene = new Scene(loader.load());

            // Создаем новое окно
            Stage taskStage = new Stage();
            taskStage.setTitle("Добавить задачу");
            taskStage.setScene(scene);

            // Показываем новое окно
            taskStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @FXML
    public void openUserForm() {
        try {
            // Загружаем FXML для страницы добавления задачи
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/taskmanagementsystem/view/UserView.fxml"));
            Scene scene = new Scene(loader.load());

            // Создаем новое окно
            Stage taskStage = new Stage();
            taskStage.setTitle("Добавить задачу");
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
