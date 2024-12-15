package org.example.taskmanagementsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.taskmanagementsystem.model.Project;

public class ProjectReviewController {
    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label descriptionLabel;

    public void setProject(Project project) {
        idLabel.setText(String.valueOf(project.getId()));
        nameLabel.setText(project.getName());
        descriptionLabel.setText(project.getDescription());
    }
}
