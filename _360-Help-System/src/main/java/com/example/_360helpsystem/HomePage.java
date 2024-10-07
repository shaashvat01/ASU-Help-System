package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomePage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Top bar with "Home" label on the left and "Logout" button on the right
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 10, 10));
        topBar.setSpacing(10);

        // "Home" text on the left
        Text homeText = new Text("Home");
        topBar.getChildren().add(homeText);
        HBox.setMargin(homeText, new Insets(0, 0, 0, 10));  // Extra padding for spacing

        // "Logout" button on the right with the same style as Admin logout button
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        logoutButton.setFont(Font.font("Arial", 18));
        topBar.getChildren().add(logoutButton);

        // Align the "Logout" button to the right
        topBar.setAlignment(Pos.TOP_RIGHT);
        topBar.setSpacing(400);  // Adjust spacing to push "Home" to the left and "Logout" to the right

        // Create the main layout with the top bar
        BorderPane root = new BorderPane();
        root.setTop(topBar);

        // Set the scene and show the stage
        Scene scene = new Scene(root, 400, 300);
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
