package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ForgotPasswordOtp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create background
        StackPane backgroundPane = new StackPane();
        Rectangle background = new Rectangle(600, 600, Color.web("#f8f5f3"));  // Very light grey background with size adjusted to 600x600
        backgroundPane.getChildren().add(background);

        // Create OTP label and text field
        Text otpText = new Text("Enter 6-digit OTP");
        otpText.setFont(Font.font("Arial", 20));  // Font size slightly increased for visibility
        TextField otpField = new TextField();
        otpField.setPrefWidth(250);  // Adjust the text field width to fit larger screen
        otpField.setMaxWidth(300);

        // Create OK button
        Button otpOkButton = new Button("OK");
        otpOkButton.setPrefWidth(120);  // Increased button width for larger screen
        otpOkButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");

        // VBox layout for OTP screen
        VBox layout = new VBox(30);  // Increased spacing between elements
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(otpText, otpField, otpOkButton);

        // Add layout to the background pane
        backgroundPane.getChildren().add(layout);

        // Create scene and set stage with size 600x600
        Scene scene = new Scene(backgroundPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Forgot Password");
        primaryStage.show();

        // Handle OK button action to redirect to the reset password screen
        otpOkButton.setOnAction(e -> showResetPasswordScreen(primaryStage));
    }

    // Method to show the Reset Password screen
    private void showResetPasswordScreen(Stage primaryStage){
        ResetPassword resetPassword = new ResetPassword();
        try{
            resetPassword.start(primaryStage);  // Call the ResetPassword screen
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
