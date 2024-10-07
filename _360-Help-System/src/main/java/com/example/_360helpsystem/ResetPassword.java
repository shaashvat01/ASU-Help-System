package com.example._360helpsystem;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ResetPassword extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create background
        StackPane backgroundPane = new StackPane();
        Rectangle background = new Rectangle(500, 500, Color.web("#f8f5f3")); // Very light grey background
        backgroundPane.getChildren().add(background);

        // Create labels and password fields
        Label newPasswordLabel = new Label("New Password");
        PasswordField newPasswordField = new PasswordField();
        newPasswordField.setPrefWidth(250); // Set consistent width for the input field

        Label confirmPasswordLabel = new Label("Confirm Password");
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPrefWidth(250); // Set consistent width for the input field

        // Set label width to ensure proper alignment
        newPasswordLabel.setPrefWidth(150);
        confirmPasswordLabel.setPrefWidth(150);

        // HBox for New Password
        HBox newPasswordBox = new HBox(10);  // 10 is the spacing between label and field
        newPasswordBox.setAlignment(Pos.CENTER);  // Center the whole box
        newPasswordBox.getChildren().addAll(newPasswordLabel, newPasswordField);

        // HBox for Confirm Password
        HBox confirmPasswordBox = new HBox(10); // Center the confirm password box
        confirmPasswordBox.setAlignment(Pos.CENTER);  // Center the whole box
        confirmPasswordBox.getChildren().addAll(confirmPasswordLabel, confirmPasswordField);

        // Create Update button
        Button updateButton = new Button("Update Password");
        updateButton.setPrefWidth(150);
        updateButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");

        // VBox layout
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(newPasswordBox, confirmPasswordBox, updateButton);

        // Add layout to background pane
        backgroundPane.getChildren().add(layout);

        // Create scene and set stage
        Scene scene = new Scene(backgroundPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Reset Password");
        primaryStage.show();

        updateButton.setOnAction(e -> showSignInScreen(primaryStage));
    }

    private void showSignInScreen(Stage primaryStage) {
        SignIn signIn = new SignIn();
        try{
            signIn.start(primaryStage);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
