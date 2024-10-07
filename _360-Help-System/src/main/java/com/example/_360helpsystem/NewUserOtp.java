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

public class NewUserOtp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create background
        StackPane backgroundPane = new StackPane();
        Rectangle background = new Rectangle(500, 500, Color.web("#f8f5f3"));  // Very light grey
        backgroundPane.getChildren().add(background);

        // Create OTP label and text field
        Text otpText = new Text("Enter 6-digit OTP");
        otpText.setFont(Font.font("Arial", 18));
        TextField otpField = new TextField();
        otpField.setPrefWidth(200);
        otpField.setMaxWidth(250);

        // Create OK button
        Button otpOkButton = new Button("OK");
        otpOkButton.setPrefWidth(100);
        otpOkButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");

        // VBox layout for OTP screen
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(otpText, otpField, otpOkButton);

        // Add layout to the background pane
        backgroundPane.getChildren().add(layout);

        // Create scene and set stage
        Scene scene = new Scene(backgroundPane, 500, 500);
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
