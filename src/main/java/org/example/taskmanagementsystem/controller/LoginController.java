package org.example.taskmanagementsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController {

    @FXML
    private ImageView imageView; // Это ваш ImageView из FXML

    public void initialize() {
        // Путь к изображению в ресурсах
        String imagePath = "/org/example/taskmanagementsystem/view/image/tree.png";  // Укажите правильный путь к изображению в ресурсах
        Image image = new Image(getClass().getResourceAsStream(imagePath)); // Загружаем изображение из ресурсов
        imageView.setImage(image); // Устанавливаем изображение в ImageView
    }
}
