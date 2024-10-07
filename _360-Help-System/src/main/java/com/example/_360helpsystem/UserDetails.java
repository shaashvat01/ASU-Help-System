package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class UserDetails extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Background setup
        StackPane backgroundPane = new StackPane();
        Rectangle background = new Rectangle(600, 600, Color.web("#f8f5f3"));  // Set background size to 600x600
        backgroundPane.getChildren().add(background);

        // GridPane for form layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);  // Center the grid on the screen
        grid.setHgap(10);  // Horizontal gap between fields
        grid.setVgap(15);  // Vertical gap between fields
        grid.setPadding(new Insets(30, 30, 30, 30));  // Padding to avoid edge overlap

        // Heading using WindowUtil for consistency
        Label headingText = WindowUtil.createStyledLabel("Finish Setting Up Your Account", 24);
        headingText.setTextFill(Color.web("#8b0000"));  // Custom color for heading
        grid.add(headingText, 0, 0, 2, 1);  // Span the heading across 2 columns

        // First Name field using WindowUtil
        Label firstNameLabel = WindowUtil.createStyledLabel("First Name", 16);
        TextField firstNameField = new TextField();
        firstNameField.setPrefWidth(250);  // Adjusted width for larger screen
        grid.add(firstNameLabel, 0, 1);
        grid.add(firstNameField, 1, 1);

        // Middle Name field using WindowUtil
        Label middleNameLabel = WindowUtil.createStyledLabel("Middle Name", 16);
        TextField middleNameField = new TextField();
        middleNameField.setPrefWidth(250);  // Adjusted width for larger screen
        grid.add(middleNameLabel, 0, 2);
        grid.add(middleNameField, 1, 2);

        // Last Name field using WindowUtil
        Label lastNameLabel = WindowUtil.createStyledLabel("Last Name", 16);
        TextField lastNameField = new TextField();
        lastNameField.setPrefWidth(250);  // Adjusted width for larger screen
        grid.add(lastNameLabel, 0, 3);
        grid.add(lastNameField, 1, 3);

        // Preferred Name field using WindowUtil
        Label preferredNameLabel = WindowUtil.createStyledLabel("Preferred Name", 16);
        TextField preferredNameField = new TextField();
        preferredNameField.setPrefWidth(250);  // Adjusted width for larger screen
        grid.add(preferredNameLabel, 0, 4);
        grid.add(preferredNameField, 1, 4);

        // Email field using WindowUtil
        Label emailLabel = WindowUtil.createStyledLabel("Email", 16);
        TextField emailField = new TextField();
        emailField.setPrefWidth(250);  // Adjusted width for larger screen
        grid.add(emailLabel, 0, 5);
        grid.add(emailField, 1, 5);

        // Save button using WindowUtil for consistency
        Button saveButton = WindowUtil.createStyledButton("Save");
        saveButton.setPrefWidth(150);  // Increased button width to match larger screen
        grid.add(saveButton, 1, 6);

        // Create the circular back button using ButtonStyleUtil
        Button backButton = ButtonStyleUtil.createCircularBackButton();

        // Handle back button action
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));  // Implement the previous screen action

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
        primaryStage.setTitle("User Details");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);  // Disable resizing to keep the layout consistent
        primaryStage.show();

        saveButton.setOnAction(e -> showSignInScreen(primaryStage));  // Save button action
    }

    // Method to go back to the previous screen
    private void showPreviousScreen(Stage primaryStage) {
        SignUp signUp = new SignUp();
        try{
            signUp.start(primaryStage);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    // Method to show the Sign In screen
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
