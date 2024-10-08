package com.example._360helpsystem;

import Backend.OTPList;
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
import javafx.stage.Stage;

import static com.example._360helpsystem.CreateAdminAccount.OTP_LIST;
import static com.example._360helpsystem.CreateAdminAccount.USER_LIST;

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

        // Create the circular back button using the utility class
        Button backButton = ButtonStyleUtil.createCircularBackButton();

        // VBox layout for OTP screen
        VBox layout = WindowUtil.createStandardLayout();  // Use standardized VBox layout
        layout.setSpacing(20);  // Add spacing between elements for better layout
        layout.setAlignment(Pos.CENTER);  // Center the elements in the VBox
        layout.getChildren().addAll(otpText, otpField, otpOkButton);

        // Add VBox layout to background pane
        backgroundPane.getChildren().add(layout);

        // Create a BorderPane to position the back button at the top left
        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(backgroundPane);

        // Align the back button to the top-left and set padding (gap of 5)
        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));  // Gap of 5 from top and left

        // Create the scene and set the stage with the adjusted window size
        Scene scene = new Scene(root, 600, 600);  // Set scene size to 600x600
        primaryStage.setTitle("New User OTP");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Handle OK button action to redirect to the sign-up screen
        otpOkButton.setOnAction(e -> {
            String otpInput = otpField.getText();

            try {
                int otp = Integer.parseInt(otpInput);  // Parse input to an integer

                // Check if OTP exists in OTP_LIST
                if (OTP_LIST.findOTP(otp)) {
                    if(OTP_LIST.removeOTP(otp))// Remove the OTP after validation
                    {
                        if(USER_LIST.findUserByOTP(otp).isInstructor() && USER_LIST.findUserByOTP(otp).isStudent())
                        {
                            showSignUpScreen(primaryStage,"SI",otp);  // Navigate to Sign-Up screen
                        }
                        if(USER_LIST.findUserByOTP(otp).isInstructor())
                        {
                            showSignUpScreen(primaryStage,"I",otp);  // Navigate to Sign-Up screen
                        }
                        if(USER_LIST.findUserByOTP(otp).isStudent())
                        {
                            showSignUpScreen(primaryStage,"S",otp);  // Navigate to Sign-Up screen
                        }

                    }

                } else {
                    // Optionally show an error message if OTP is invalid
                    System.out.println("Invalid OTP. Please try again.");
                    // You could also show a dialog or label to inform the user
                }
            } catch (NumberFormatException ex) {
                // Handle case where input is not a valid integer
                System.out.println("Please enter a valid 6-digit OTP.");
            }
        });

        backButton.setOnAction(e -> showPreviousScreen(primaryStage));
    }

    // Method to show the Sign Up screen
    private void showSignUpScreen(Stage primaryStage,String role,int otp) {
        SignUp mainSignUp = new SignUp(role,otp);
        try {
            mainSignUp.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showPreviousScreen(Stage primaryStage) {
        Main main = new Main();
        try{
            main.start(primaryStage);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}