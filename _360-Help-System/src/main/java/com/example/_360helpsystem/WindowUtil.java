package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WindowUtil {

    // Method to set a standardized scene size
    public static void setWindowSize(Stage stage, Scene scene, double width, double height) {
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setScene(scene);
    }

    // Method to set common styling for VBox
    public static VBox createStandardLayout() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);  // Center all elements
        layout.setPadding(new Insets(25, 25, 25, 25));  // Padding around the VBox
        return layout;
    }

    // Method to create a standardized button style
    public static Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        button.setFont(Font.font("Arial", 18));
        return button;
    }

    // Method to set label styling
    public static Label createStyledLabel(String text, int fontSize) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", fontSize));
        return label;
    }
}

