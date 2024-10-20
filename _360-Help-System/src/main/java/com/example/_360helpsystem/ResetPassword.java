package com.example._360helpsystem;

import Backend.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/*******
 * <p> ResetPassword Class </p>
 *
 * <p> Description: This class handles the password reset functionality.
 * It provides a screen for users to enter a new password and confirm it before resetting. </p>
 *
 * @version 1.00, 2024-10-09
 * @author Team - Th15
 *
 */

public class ResetPassword extends Application {
    private User User_to_resetPassword;
    public ResetPassword(User user) {this.User_to_resetPassword = user;}
    // This method sets up the screen where users can enter and confirm their new password.
    @Override
    public void start(Stage primaryStage) {
        // Create background
        StackPane backgroundPane = new StackPane();
        Rectangle background = new Rectangle(900, 700, Color.web("#f8f5f3"));  // Very light grey background, adjusted to 600x600
        backgroundPane.getChildren().add(background);

        // Create labels and password fields
        Label newPasswordLabel = WindowUtil.createStyledLabel("New Password", 16);
        PasswordField newPasswordField = new PasswordField();
        newPasswordField.setPrefWidth(250);  // Set consistent width for the input field

        Label confirmPasswordLabel = WindowUtil.createStyledLabel("Confirm Password", 16);
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPrefWidth(250);  // Set consistent width for the input field

        // Set label width to ensure proper alignment
        newPasswordLabel.setPrefWidth(150);
        confirmPasswordLabel.setPrefWidth(150);

        // HBox for New Password
        HBox newPasswordBox = new HBox(10);  // 10 is the spacing between label and field
        newPasswordBox.setAlignment(Pos.CENTER);  // Center the whole box
        newPasswordBox.getChildren().addAll(newPasswordLabel, newPasswordField);

        // HBox for Confirm Password
        HBox confirmPasswordBox = new HBox(10);  // 10 is the spacing between label and field
        confirmPasswordBox.setAlignment(Pos.CENTER);  // Center the whole box
        confirmPasswordBox.getChildren().addAll(confirmPasswordLabel, confirmPasswordField);

        // Create Update button using WindowUtil for consistent styling
        Button updateButton = WindowUtil.createStyledButton("Update Password");
        updateButton.setPrefWidth(250);

        // Feedback label for real-time password strength evaluation
        Label passwordFeedbackLabel = new Label();
        passwordFeedbackLabel.setTextFill(Color.RED);  // Initially set the text color to red

        // Error label for password mismatch
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);  // Set text color to red for visibility

        // VBox layout
        VBox layout = WindowUtil.createStandardLayout();  // Use standardized VBox layout
        layout.setSpacing(20);  // Add spacing between elements for better visibility
        layout.setAlignment(Pos.CENTER);  // Center the VBox elements
        layout.getChildren().addAll(newPasswordBox, confirmPasswordBox, passwordFeedbackLabel,errorLabel,updateButton);

        PasswordEvaluator PE = new PasswordEvaluator(newPasswordField,passwordFeedbackLabel);
        // Create the circular back button using ButtonStyleUtil
        Button backButton = ButtonStyleUtil.createCircularBackButton();

        // Handle back button action
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));  // Implement your back button logic here

        // Add layout to background pane
        backgroundPane.getChildren().add(layout);

        // Create a BorderPane to position the back button at the top left
        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(backgroundPane);

        // Align the back button to the top-left and set padding (gap of 5)
        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));  // Gap of 5 from top and left

        // Create scene and set stage using the adjusted window size
        Scene scene = new Scene(root, 900, 700);  // Set scene size to 600x600
        primaryStage.setTitle("Reset Password");
        primaryStage.setScene(scene);
        primaryStage.show();

        updateButton.setOnAction(e -> {
            String newPassword = newPasswordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            // Check if both password fields match
            if (PE.checkPassword(newPassword)) {

                // Check password strength
                if (newPassword.equals(confirmPassword)) {
                    // If password is strong enough, update it
                    User_to_resetPassword.setPassword(newPassword);
                    User_to_resetPassword.setAccOTP(1000000);  // Assuming there's a method to reset OTP
                    showSignInScreen(primaryStage);
                } else {
                    // If passwords don't match, show an error message 
                    errorLabel.setText("Passwords do not match!");     
                }
            }
            
        });
    }

    // Method to go back to the previous screen
    private void showPreviousScreen(Stage primaryStage) {
        ForgotPasswordOtp forgotPasswordOtp = new ForgotPasswordOtp();
        try{
            forgotPasswordOtp.start(primaryStage);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
         }
    // This method navigates to the Sign In screen after password reset.
    private void showSignInScreen(Stage primaryStage) {
        SignIn signIn = new SignIn();
        try {
            signIn.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    // main
    public static void main(String[] args) {
        launch(args);
    }
}
