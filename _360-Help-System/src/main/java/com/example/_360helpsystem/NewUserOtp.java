package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class NewUserOtp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create background
        StackPane backgroundPane = new StackPane();
        Rectangle background = new Rectangle(600, 600, Color.web("#f8f5f3"));  // Very light grey, adjusted to 600x600
        backgroundPane.getChildren().add(background);

        // Create OTP label and text field
        Label otpText = WindowUtil.createStyledLabel("Enter 6-digit OTP", 18);  // Standardized OTP label
        TextField otpField = new TextField();
        otpField.setPrefWidth(250);  // Adjust the width to fit a larger screen
        otpField.setMaxWidth(300);

        // Create OK button using WindowUtil's standardized button method
        Button otpOkButton = WindowUtil.createStyledButton("OK");
        otpOkButton.setPrefWidth(120);  // Increase the button width for a better fit

        // VBox layout for OTP screen
        VBox layout = WindowUtil.createStandardLayout();  // Use standardized VBox layout
        layout.setSpacing(20);  // Add spacing between elements for better layout
        layout.setAlignment(Pos.CENTER);  // Center the elements in the VBox
        layout.getChildren().addAll(otpText, otpField, otpOkButton);

        // Add layout to the background pane
        backgroundPane.getChildren().add(layout);

        // Create scene and set the stage with the adjusted window size
        Scene scene = new Scene(backgroundPane, 600, 600);  // Set scene size to 600x600
        primaryStage.setTitle("New User OTP");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Handle OK button action to redirect to the sign-up screen
        otpOkButton.setOnAction(e -> showSignUpScreen(primaryStage));
    }

    // Method to show the Sign Up screen
    private void showSignUpScreen(Stage primaryStage) {
        SignUp mainSignUp = new SignUp();
        try {
            mainSignUp.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
