package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class HomePage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Top bar with "Home" label on the left and "Logout" button on the right
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 10, 10));

        // "Home" text on the left
        Label homeText = WindowUtil.createStyledLabel("Home", 24);
        topBar.getChildren().add(homeText);
        HBox.setMargin(homeText, new Insets(0, 0, 0, 10));  // Extra padding for spacing

        // Add spacer to push the "Logout" button to the right
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);  // Make the spacer grow to push the Logout button to the right
        topBar.getChildren().add(spacer);

        // "Logout" button on the right
        Button logoutButton = WindowUtil.createStyledButton("Logout");
        logoutButton.setFont(WindowUtil.createStyledLabel("Logout", 18).getFont());  // Consistent font
        topBar.getChildren().add(logoutButton);

        // Align the top bar
        topBar.setAlignment(Pos.CENTER);

        // Create the main layout with the top bar
        BorderPane root = new BorderPane();
        root.setTop(topBar);

        // Set the scene with the required window size
        Scene scene = new Scene(root, 600, 600);  // Set to 600x600
        primaryStage.setTitle("Home Page");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Set action for the logout button to show the SignIn screen
        logoutButton.setOnAction(e -> showSignInScreen(primaryStage));
    }

    // Method to show the SignIn screen when Logout is clicked
    private void showSignInScreen(Stage primaryStage) {
        SignIn signIn = new SignIn();
        try {
            signIn.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
