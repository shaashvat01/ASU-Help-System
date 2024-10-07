package com.example._360helpsystem;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignUp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Background setup
        StackPane backgroundPane = new StackPane();
        Rectangle background = new Rectangle(500, 500, Color.web("#f8f5f3"));
        backgroundPane.getChildren().add(background);

        // GridPane for form layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Heading
        Text headingText = new Text("Sign Up");
        headingText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        headingText.setFill(Color.web("#8b0000"));
        grid.add(headingText, 0, 0, 2, 1);

        // Username
        Label usernameLabel = new Label("Username");
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(200);
        usernameField.setMaxWidth(250);
        grid.add(usernameLabel, 0, 1);
        grid.add(usernameField, 1, 1);

        // Password
        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(200);
        passwordField.setMaxWidth(250);
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);

        // Confirm Password
        Label confirmPasswordLabel = new Label("Confirm Password");
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPrefWidth(200);
        confirmPasswordField.setMaxWidth(250);
        grid.add(confirmPasswordLabel, 0, 3);
        grid.add(confirmPasswordField, 1, 3);

        // Sign Up button
        Button signUpButton = new Button("Sign Up");
        signUpButton.setPrefWidth(100);
        signUpButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        grid.add(signUpButton, 1, 4);

        // Add grid to background pane
        backgroundPane.getChildren().add(grid);

        // Set the scene and show the stage
        Scene scene = new Scene(backgroundPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sign Up");
        primaryStage.show();

        // Sign Up button event handler
        signUpButton.setOnAction(e -> showUserDetailsScreen(primaryStage));
    }

    // Method to show the UserDetails screen
    private void showUserDetailsScreen(Stage primaryStage) {
        UserDetails userDetails = new UserDetails();
        try {
            userDetails.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Method to show the UserDetails screen


    public static void main(String[] args) {
        launch(args);
    }
}
