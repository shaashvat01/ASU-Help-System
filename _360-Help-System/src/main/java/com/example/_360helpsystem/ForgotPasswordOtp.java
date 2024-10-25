package com.example._360helpsystem;

import Backend.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static com.example._360helpsystem.CreateAdminAccount.OTP_LIST;
import static com.example._360helpsystem.CreateAdminAccount.USER_LIST;

/*******
 * <p> ForgotPasswordOtp Class </p>
 *
 * <p> Description: This class handles the OTP validation process when a user forgets their password.
 * It allows the user to enter an OTP and, if valid, redirects them to the reset password screen. </p>
 *
 * @version 1.00, 2024-10-09
 * @author Team - Th15
 *
 */

public class ForgotPasswordOtp extends Application {

    // This method sets up the OTP validation screen with an input field, feedback, and navigation buttons.
    @Override
    public void start(Stage primaryStage) {
        // Create background
        StackPane backgroundPane = new StackPane();
        Rectangle background = new Rectangle(900, 700, Color.web("#f8f5f3"));  // Very light grey background with size adjusted to 600x600
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

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);  // Set text color to red for visibility

        // VBox layout for OTP screen
        VBox layout = new VBox(30);  // Increased spacing between elements
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(otpText, otpField,errorLabel, otpOkButton);

        // Create the circular back button using ButtonStyleUtil
        Button backButton = ButtonStyleUtil.createCircularBackButton();

        // Handle back button action
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));  // Implement your back button logic here

        // Add layout to the background pane
        backgroundPane.getChildren().add(layout);

        // Create a BorderPane to position the back button at the top left
        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(backgroundPane);

        // Align the back button to the top-left and set padding (gap of 5)
        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));  // Gap of 5 from top and left

        // Create scene and set stage with size 900x700
        Scene scene = new Scene(root, 900, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Forgot Password");
        primaryStage.show();

        // Handle OK button action to redirect to the reset password screen
        otpOkButton.setOnAction(e -> {
            String enteredOtp = otpField.getText();  // Get the OTP from the text field
            int otpInt = Integer.parseInt(enteredOtp);

            if(USER_LIST.findUserByOTP(otpInt) != null)
            {
                // Now, you can use the entered OTP for further validation
                showResetPasswordScreen(primaryStage,USER_LIST.findUserByOTP(otpInt));
            }
            else {
                errorLabel.setText("Invalid OTP");
            }
        });
    }

    // Method to show the Reset Password screen
    private void showResetPasswordScreen(Stage primaryStage, User user) {
        ResetPassword resetPassword = new ResetPassword(user);
        try{
            resetPassword.start(primaryStage);  // Call the ResetPassword screen
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    // Method to handle back button logic
    private void showPreviousScreen(Stage primaryStage) {
        SignIn signIn = new SignIn();
        try{
            signIn.start(primaryStage);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    // main
    public static void main(String[] args) {
        launch(args);
    }
}
