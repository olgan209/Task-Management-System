module org.example.taskmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.taskmanagementsystem to javafx.fxml;
    exports org.example.taskmanagementsystem;
}