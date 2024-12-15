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
        // Загружаем основной FXML файл с TabPane
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/taskmanagementsystem/view/ProjectReviewView.fxml"));

        // Устанавливаем сцену и показываем окно
        primaryStage.setTitle("Task Management System");
        primaryStage.setScene(new Scene(root, 800, 600)); // Устанавливаем размер окна
        primaryStage.show();
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
    private void openTaskForm() {
        // Ваш код для обработки открытия формы добавления задачи
    }

    public static void main(String[] args) {
        launch(args); // Запуск JavaFX приложения
    }
}
