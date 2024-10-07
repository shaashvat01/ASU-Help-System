package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

// Main class to handle initial Login/Signup buttons and navigation
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // StackPane to hold the background
        StackPane backgroundPane = new StackPane();
        Rectangle background = new Rectangle(600, 600, Color.web("#f8f5f3"));  // Very light grey, size adjusted to 600x600
        backgroundPane.getChildren().add(background);

        // VBox layout to hold the "Sign In" and "Sign Up" buttons
        VBox layout = WindowUtil.createStandardLayout();  // Use standardized VBox layout
        layout.setSpacing(20);  // Add spacing between buttons for better layout
        layout.setAlignment(Pos.CENTER);  // Center align the buttons
        layout.setPadding(new Insets(20));  // Add padding to avoid edges

        // Create "Sign In" Button
        Button signInOption = WindowUtil.createStyledButton("Sign In");
        signInOption.setPrefWidth(250);  // Set button width to fit larger window

        // Create "Sign Up" Button
        Button signUpOption = WindowUtil.createStyledButton("Sign Up");
        signUpOption.setPrefWidth(250);  // Set button width to fit larger window

        // Add buttons to the layout
        layout.getChildren().addAll(signInOption, signUpOption);
        backgroundPane.getChildren().add(layout);

        // Create scene for the initial screen and set the window size to 600x600
        Scene scene = new Scene(backgroundPane, 600, 600);

        primaryStage.setTitle("Login/Signup");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Action for "Sign In" button to open the username/password login window
        signInOption.setOnAction(e -> showSignInScreen(primaryStage));

        // "Sign Up" functionality is currently a placeholder
        signUpOption.setOnAction(e -> showNewUserOtpScreen(primaryStage));
    }

    // Method to show the Sign In screen (Main.java login form)
    private void showSignInScreen(Stage primaryStage) {
        SignIn mainLogin = new SignIn();
        try {
            mainLogin.start(primaryStage);  // Open the SignIn screen
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showNewUserOtpScreen(Stage primaryStage){
        NewUserOtp mainOtp = new NewUserOtp();
        try {
            mainOtp.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);  // Ensure you're running Main.java as the entry point
    }
}
