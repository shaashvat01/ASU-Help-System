package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignIn extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Background layer (Light grey overall background)
        StackPane backgroundPane = new StackPane();
        Rectangle background = new Rectangle(500, 500, Color.web("#f8f5f3"));  // Very light grey
        backgroundPane.getChildren().add(background);

        // Create a grid layout for labels and fields
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);  // Center the grid
        grid.setHgap(10);  // Horizontal gap between columns
        grid.setVgap(10);  // Vertical gap between rows (to match SignUp)
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Create the Sign In heading
        Text headingText = new Text("Sign In");
        headingText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        headingText.setFill(Color.web("#8b0000"));
        grid.add(headingText, 0, 0, 2, 1);  // Span heading across 2 columns

        // Center the heading text horizontally and add vertical spacing
        GridPane.setHalignment(headingText, HPos.CENTER);  // Center horizontally
        GridPane.setMargin(headingText, new Insets(0, 0, 20, 0));  // Add margin below the heading

        // Create username label and text field
        Label usernameLabel = new Label("Username");
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(200);
        usernameField.setMaxWidth(250);  // Match the field width from SignUp
        grid.add(usernameLabel, 0, 1);  // First column
        grid.add(usernameField, 1, 1);  // Second column

        // Create password label and password field
        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(200);
        passwordField.setMaxWidth(250);  // Match the field width from SignUp
        grid.add(passwordLabel, 0, 2);  // First column
        grid.add(passwordField, 1, 2);  // Second column

        // Add "Forgot Password?" hyperlink next to the password field
        Hyperlink forgotPasswordLink = new Hyperlink("Forgot Password?");
        forgotPasswordLink.setFont(Font.font("Arial", 15));
        grid.add(forgotPasswordLink, 2, 2);  // Next to the password field

        // Create login button
        Button loginButton = new Button("Sign In");
        loginButton.setPrefWidth(100);
        loginButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");

        // Center the login button horizontally
        GridPane.setHalignment(loginButton, HPos.CENTER);  // Center the button horizontally

        // Create an HBox for the button
        HBox loginButtonBox = new HBox(10);
        loginButtonBox.setAlignment(Pos.CENTER_LEFT);  // Align to the left of the second column
        loginButtonBox.getChildren().add(loginButton);
        grid.add(loginButtonBox, 1, 3);  // Second column

        // Add everything to the background pane and set up the scene
        backgroundPane.getChildren().add(grid);
        Scene scene = new Scene(backgroundPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sign In");
        primaryStage.show();

        // Set action for the login button
        loginButton.setOnAction(e -> {
            SignInAs loginAsScreen = new SignInAs();
            loginAsScreen.start(primaryStage);
        });

        forgotPasswordLink.setOnAction(e -> showForgotPasswordOtpScreen(primaryStage));
    }

    // Method to show the "Forgot Password" screen
    private void showForgotPasswordOtpScreen(Stage primaryStage){
        ForgotPasswordOtp mainOtp = new ForgotPasswordOtp();
        try{
            mainOtp.start(primaryStage);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
