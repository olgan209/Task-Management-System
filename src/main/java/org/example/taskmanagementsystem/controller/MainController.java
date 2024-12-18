package org.example.taskmanagementsystem.controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.example.taskmanagementsystem.model.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.taskmanagementsystem.model.Session;
import org.example.taskmanagementsystem.model.Task;
import org.example.taskmanagementsystem.model.User;
import org.example.taskmanagementsystem.services.ProjectService;
import org.example.taskmanagementsystem.services.TaskService;
import org.example.taskmanagementsystem.services.UserService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class MainController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/taskmanagementsystem/view/RegistrationView.fxml"));
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
    @FXML
    private Label userNameLabel;
    @FXML
    private Label userLoginLabel;

    private final UserService userService = new UserService();

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @FXML
    public void handleRowClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Project selectedProject = projectTableView.getSelectionModel().getSelectedItem();

            if (selectedProject != null) {
                try {

                    List<Task> projectTasks = taskService.getTasksByProjectId(selectedProject.getId());

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/taskmanagementsystem/view/ProjectReviewView.fxml"));
                    Parent root = loader.load();

                    ProjectReviewController controller = loader.getController();
                    controller.initialize(selectedProject, projectTasks);

                    Stage stage = new Stage();
                    stage.setTitle("Обзор проекта");
                    stage.setScene(new Scene(root));
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void initialize() {
        List<Task> closestTasks = taskService.getFiveClosestDeadlines();
        for (Task task : closestTasks) {
            String taskInfo = task.getName() + " — " + task.getDeadline();
            deadlineListView.getItems().add(taskInfo);
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id")); // Связываем с полем "id" в Project
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        List<Project> projectList = projectService.getAllProjects();
        ObservableList<Project> projects = FXCollections.observableArrayList(projectList);
        projectTableView.setItems(projects);

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

        User user = fetchUserFromDatabase();
        if (user != null) {
            userNameLabel.setText(user.getName());
            userLoginLabel.setText(user.getEmail());
        }
    }

    private User fetchUserFromDatabase() {
        User user = null;
        String url = "jdbc:postgresql://localhost:5432/Tms";
        String username = "postgres";
        String password = "1234";
        int currentUserId = Session.getInstance().getUserId();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT name, email FROM User_table WHERE userId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, currentUserId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String login = rs.getString("email");
                user = new User(name, login);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @FXML
    public void openProjectForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/taskmanagementsystem/view/ProjectView.fxml"));
            Scene scene = new Scene(loader.load());

            Stage taskStage = new Stage();
            taskStage.setTitle("Добавить проект");
            taskStage.setScene(scene);

            taskStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openProjectReviewView(Project project) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/taskmanagementsystem/view/ProjectReviewView.fxml"));
            Parent root = loader.load();

            List<Task> projectTasks = taskService.getTasksByProjectId(project.getId());

            ProjectReviewController controller = loader.getController();
            controller.initialize(project, projectTasks);

            Stage stage = new Stage();
            stage.setTitle("Обзор проекта");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteAccount() {
        int currentUserId = Session.getInstance().getUserId(); // Получаем ID текущего пользователя из сессии
        UserService userService = new UserService();

        // Подтверждение удаления аккаунта
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Вы уверены, что хотите удалить свой аккаунт?", ButtonType.YES, ButtonType.NO);
        confirmationAlert.setTitle("Удаление аккаунта");
        confirmationAlert.setHeaderText(null);

        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                userService.deleteUser(currentUserId); // Удаляем пользователя из базы данных
                System.out.println("Пользователь удален.");

                // Закрываем текущее окно
                Stage stage = (Stage) projectTableView.getScene().getWindow();
                stage.close();
            }
        });
    }

    @FXML
    public void editAccount() {
        try {
            // Получаем ID текущего пользователя из сессии
            int currentUserId = Session.getInstance().getUserId();

            // Получаем данные текущего пользователя с помощью UserService
            User currentUser = userService.getUser(currentUserId);

            // Загружаем FXML для формы редактирования
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/taskmanagementsystem/view/EditUserView.fxml"));
            Parent root = loader.load();

            // Передаем текущего пользователя в контроллер EditUserController
            EditUserController controller = loader.getController();
            controller.setCurrentUser(currentUser);  // Передаем объект пользователя

            // Открываем окно редактирования
            Stage stage = new Stage();
            stage.setTitle("Редактирование аккаунта");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
