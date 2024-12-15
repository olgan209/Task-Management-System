package org.example.taskmanagementsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RegistrationController {

    @FXML
    private ImageView backgroundImageView;

    @FXML
    public void initialize() {
        // Путь к изображению
        String imagePath = "/org/example/taskmanagementsystem/view/image/bg.jpg";

        // Загрузка изображения
        Image image = new Image(getClass().getResourceAsStream(imagePath));

        // Установка изображения в ImageView
        backgroundImageView.setImage(image);
    }
}
