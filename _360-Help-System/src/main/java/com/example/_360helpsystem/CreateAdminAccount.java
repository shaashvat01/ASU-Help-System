package com.example._360helpsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateAdminAccount extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Check if users exist (if none exist, show the first-time setup screen)
        boolean usersExist = checkIfUsersExist();

        if (!usersExist) {
            showFirstTimeSetupScreen(primaryStage);  // Show the Admin creation screen
        } else {
            System.out.println("Users already exist, redirect to login.");
        }
    }

    // Method to show the first-time setup screen
    private void showFirstTimeSetupScreen(Stage primaryStage) {
        VBox layout = WindowUtil.createStandardLayout();  // Use standardized VBox layout

        Label prompt = WindowUtil.createStyledLabel("Create Admin Account", 24);  // Standardized title label

        // Username and Password fields
        Label usernameLabel = WindowUtil.createStyledLabel("Username", 16);  // Label with standard style
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(250);
        usernameField.setMaxWidth(250);

        Label passwordLabel = WindowUtil.createStyledLabel("Password", 16);  // Label with standard style
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(250);
        passwordField.setMaxWidth(250);

        // Create the standardized button
        Button createAdminButton = WindowUtil.createStyledButton("Create Admin Account");

        // Action to create the Admin account
        createAdminButton.setOnAction(e -> showUserDetailsScreen(primaryStage));

        // Add all elements to the layout
        layout.getChildren().addAll(prompt, usernameLabel, usernameField, passwordLabel, passwordField, createAdminButton);

        // Set standardized window size
        Scene scene = new Scene(layout);
        WindowUtil.setWindowSize(primaryStage, scene, 600, 600);  // Standard size applied

        primaryStage.show();
    }

    private void showUserDetailsScreen(Stage primaryStage) {
        UserDetails userDetails = new UserDetails();
        try {
            userDetails.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean checkIfUsersExist() {
        return false;  // For testing purposes, this is set to false
    }
}
