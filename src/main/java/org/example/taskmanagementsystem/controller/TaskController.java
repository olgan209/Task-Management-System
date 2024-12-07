package org.example.taskmanagementsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;

public class TaskController {

    // Объявление полей для элементов управления
    @FXML private TextField taskNameField;
    @FXML private TextArea taskDescriptionField;
    @FXML private ComboBox<String> userComboBox;
    @FXML private ComboBox<String> priorityComboBox;
    @FXML private DatePicker dueDatePicker;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    // Метод инициализации (для привязки элементов управления, если необходимо)
    @FXML
    private void initialize() {
        // Дополнительные настройки, если нужны (например, заполнение ComboBox)
    }
}
