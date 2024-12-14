package org.example.taskmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RegistrationController {

    @FXML
    private void registerUser(ActionEvent event) {
        // Ваш код обработки
    }

    @FXML
    private ImageView gifView;

    @FXML
    public void initialize() {
        // Укажите путь к вашей гифке
        Image image = new Image(getClass().getResource("/org/example/taskmanagementsystem/view/image/images.jfif").toExternalForm());
    }

}
