package main.java.org.example.taskmanagementsystem.controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class MainController extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Загружаем FXML файл
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/taskmanagementsystem/view/MainView.fxml"));

        // Устанавливаем сцену и показываем окно
        primaryStage.setTitle("Task Management System - Main View");
        primaryStage.setScene(new Scene(root, 800, 600)); // Устанавливаем размер окна
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Запуск JavaFX приложения
    }
}
