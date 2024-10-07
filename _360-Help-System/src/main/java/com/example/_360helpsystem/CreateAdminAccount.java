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
            // You could redirect to a normal login screen or another workflow if needed
            System.out.println("Users already exist, redirect to login.");
        }
    }

    // Method to show the first-time setup screen
    private void showFirstTimeSetupScreen(Stage primaryStage) {
        VBox layout = new VBox(20);  // Set spacing of 20px between elements
        layout.setAlignment(Pos.CENTER);  // Center all elements
        layout.setPadding(new Insets(25, 25, 25, 25));  // Padding around the VBox

        Label prompt = new Label("Create Admin Account");
        prompt.setFont(Font.font("Arial", 24));

        // Username and Password fields
        Label usernameLabel = new Label("Username");
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(250);
        usernameField.setMaxWidth(250);

        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(250);
        passwordField.setMaxWidth(250);

        Button createAdminButton = new Button("Create Admin Account");
        createAdminButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        createAdminButton.setFont(Font.font("Arial", 18));

        // Action to create the Admin account
        createAdminButton.setOnAction(e -> showUserDetailsScreen(primaryStage));

        // Add all elements to the layout
        layout.getChildren().addAll(prompt, usernameLabel, usernameField, passwordLabel, passwordField, createAdminButton);

        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showUserDetailsScreen(Stage primaryStage) {
        UserDetails userDetails = new UserDetails();
        try{
            userDetails.start(primaryStage);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Mock method to check if users exist (replace with actual database check)
    private boolean checkIfUsersExist() {
        // Here, check if there are any users in your system
        // Return true if users exist, false if it's the first user
        return false;  // For testing purposes, this is set to false
    }

    // Mock method to create admin account (replace with actual account creation logic)
    private void createAdminAccount(String username, String password) {
        // Logic to create the admin account and save it in the system
        System.out.println("Admin account created for user: " + username);
        // Implement database or file storage logic here
    }

    // Mock method to show the sign-in screen (replace with actual implementation)
    private void showSignInScreen(Stage primaryStage) {
        System.out.println("Redirecting to the sign-in screen.");
        // Replace with your actual logic to show the sign-in screen
    }
}
