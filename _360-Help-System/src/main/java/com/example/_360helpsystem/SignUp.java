package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SignUp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Background setup
        StackPane backgroundPane = new StackPane();
        Rectangle background = new Rectangle(600, 600, Color.web("#f8f5f3"));  // Set background size to 600x600
        backgroundPane.getChildren().add(background);

        // GridPane for form layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);  // Center the grid
        grid.setHgap(10);  // Increase horizontal gap between columns
        grid.setVgap(15);  // Increase vertical gap between rows
        grid.setPadding(new Insets(30, 30, 30, 30));  // Add padding to avoid edges

        // Heading using WindowUtil for consistent style
        Label headingText = WindowUtil.createStyledLabel("Sign Up", 24);
        headingText.setTextFill(Color.web("#8b0000"));  // Custom color for heading
        grid.add(headingText, 0, 0, 2, 1);  // Span the heading across 2 columns

        // Username field using WindowUtil for consistency
        Label usernameLabel = WindowUtil.createStyledLabel("Username", 16);
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(250);  // Adjusted width for the larger window
        grid.add(usernameLabel, 0, 1);
        grid.add(usernameField, 1, 1);

        // Password field using WindowUtil for consistency
        Label passwordLabel = WindowUtil.createStyledLabel("Password", 16);
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(250);  // Adjusted width for the larger window
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);

        // Confirm Password field using WindowUtil for consistency
        Label confirmPasswordLabel = WindowUtil.createStyledLabel("Confirm Password", 16);
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPrefWidth(250);  // Adjusted width for the larger window
        grid.add(confirmPasswordLabel, 0, 3);
        grid.add(confirmPasswordField, 1, 3);

        // Sign Up button using WindowUtil's standardized button style
        Button signUpButton = WindowUtil.createStyledButton("Sign Up");
        signUpButton.setPrefWidth(150);  // Increased width to match the larger screen
        grid.add(signUpButton, 1, 4);

        // Create the circular back button using ButtonStyleUtil
        Button backButton = ButtonStyleUtil.createCircularBackButton();

        // Handle back button action
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));

        // Add grid to background pane
        backgroundPane.getChildren().add(grid);

        // Create a BorderPane to position the back button at the top left
        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(backgroundPane);

        // Align the back button to the top-left and set padding (gap of 5)
        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));  // Gap of 5 from top and left

        // Set the scene size to 600x600 and make all elements visible
        Scene scene = new Scene(root, 600, 600);  // Adjusted scene size to 600x600
        primaryStage.setTitle("Sign Up");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);  // Disable resizing to keep the layout consistent
        primaryStage.show();

        // Sign Up button event handler
        signUpButton.setOnAction(e -> showUserDetailsScreen(primaryStage));
    }

    // Method to go back to the previous screen
    private void showPreviousScreen(Stage primaryStage) {
      NewUserOtp newUserOtp = new NewUserOtp();
      try{
          newUserOtp.start(primaryStage);
      }
      catch(Exception ex){
          ex.printStackTrace();
      }
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

    public static void main(String[] args) {
        launch(args);
    }
}
