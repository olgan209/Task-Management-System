module org.example.taskmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.example.taskmanagementsystem.controller to javafx.fxml; // Разрешаем доступ к пакету для JavaFX
    exports org.example.taskmanagementsystem.controller; // Экспортируем пакет контроллеров
    exports org.example.taskmanagementsystem.model;
}
