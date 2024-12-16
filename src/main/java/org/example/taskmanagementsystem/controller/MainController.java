package org.example.taskmanagementsystem.controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.example.taskmanagementsystem.model.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.taskmanagementsystem.model.Task;
import org.example.taskmanagementsystem.services.ProjectService;
import org.example.taskmanagementsystem.services.TaskService;

import java.io.IOException;
import java.util.List;

public class MainController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Загружаем основной FXML файл с TabPane
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/taskmanagementsystem/view/MainView.fxml"));

        // Устанавливаем сцену и показываем окно
        primaryStage.setTitle("Task Management System");
        primaryStage.setScene(new Scene(root, 800, 600)); // Устанавливаем размер окна
        primaryStage.show();
    }

    @FXML
    private TableView<Project> projectTableView;
    @FXML
    private TableColumn<Project, Integer> idColumn;
    @FXML
    private TableColumn<Project, String> nameColumn;
    @FXML
    private TableColumn<Project, String> descriptionColumn;
    @FXML
    private ListView<String> deadlineListView; // Свяжите с вашим FXML

    private final TaskService taskService = new TaskService();


    private ProjectService projectService = new ProjectService();

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @FXML
    public void handleRowClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Project selectedProject = projectTableView.getSelectionModel().getSelectedItem();

            if (selectedProject != null) {
                try {
                    // Получаем задачи для выбранного проекта
                    List<Task> projectTasks = taskService.getTasksByProjectId(selectedProject.getId());

                    // Загружаем новую вьюшку для обзора проекта
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/taskmanagementsystem/view/ProjectReviewView.fxml"));
                    Parent root = loader.load();

                    // Передаем проект и задачи в контроллер
                    ProjectReviewController controller = loader.getController();
                    controller.initialize(selectedProject, projectTasks);

                    // Создаем новое окно
                    Stage stage = new Stage();
                    stage.setTitle("Обзор проекта");
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace(); // Логируем ошибку
                }
            }
        }
    }

    // Инициализация данных
    public void initialize() {
        // Получаем ближайшие задачи
        List<Task> closestTasks = taskService.getFiveClosestDeadlines();

        // Заполняем ListView дедлайнами
        for (Task task : closestTasks) {
            String taskInfo = task.getName() + " — " + task.getDeadline();
            deadlineListView.getItems().add(taskInfo);
        }

        // Настройка столбцов таблицы
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id")); // Связываем с полем "id" в Project
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Загрузка данных из ProjectService
        List<Project> projectList = projectService.getAllProjects();
        ObservableList<Project> projects = FXCollections.observableArrayList(projectList);
        projectTableView.setItems(projects);

        // Установка обработчиков двойного клика на строки таблицы
        projectTableView.setRowFactory(tv -> {
            TableRow<Project> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Project project = row.getItem();
                    openProjectReviewView(project);
                }
            });
            return row;
        });
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

    private void openProjectReviewView(Project project) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/taskmanagementsystem/view/ProjectReviewView.fxml"));
            Parent root = loader.load();

            // Получаем задачи для выбранного проекта
            List<Task> projectTasks = taskService.getTasksByProjectId(project.getId());

            // Передача данных в контроллер через метод initialize
            ProjectReviewController controller = loader.getController();
            controller.initialize(project, projectTasks);  // Используем метод initialize вместо setProject

            // Создаем новое окно
            Stage stage = new Stage();
            stage.setTitle("Обзор проекта");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Логируем ошибку
        }
    }


    public static void main(String[] args) {
        launch(args); // Запуск JavaFX приложения
    }
}
