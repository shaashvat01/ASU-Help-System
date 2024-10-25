package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/*******
 * <p> Main Class </p>
 *
 * <p> Description: This class handles the initial login/signup options and navigation.
 * It presents buttons for "Sign In" and "Sign Up" and navigates accordingly. </p>
 *
 * @version 1.00, 2024-10-09
 * @author Team - Th15
 *
 */

// Main class to handle initial Login/Signup buttons and navigation
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // StackPane to hold the background
        StackPane backgroundPane = new StackPane();
        Rectangle background = new Rectangle(900, 700, Color.web("#f8f5f3"));  // Very light grey, size adjusted to 600x600
        backgroundPane.getChildren().add(background);

        // VBox layout to hold the heading and buttons
        VBox layout = WindowUtil.createStandardLayout();  // Use standardized VBox layout
        layout.setSpacing(20);  // Add spacing between heading and buttons
        layout.setAlignment(Pos.CENTER);  // Center align the heading and buttons
        layout.setPadding(new Insets(20));  // Add padding to avoid edges

        // Create heading label
        Label heading = new Label("Welcome to ASU Help System");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 28));  // Set font style, weight, and size
        heading.setTextFill(Color.DARKRED);  // Set text color
        heading.setPadding(new Insets(0, 0, 20, 0));  // Add a gap of 20 below the heading

        // Create "Sign In" Button
        Button signInOption = WindowUtil.createStyledButton("Sign In");
        signInOption.setPrefWidth(250);  // Set button width to fit larger window

        // Create "Sign Up" Button
        Button signUpOption = WindowUtil.createStyledButton("Sign Up");
        signUpOption.setPrefWidth(250);  // Set button width to fit larger window

        // Add heading and buttons to the layout
        layout.getChildren().addAll(heading, signInOption, signUpOption);
        backgroundPane.getChildren().add(layout);

        // Create scene for the initial screen and set the window size to 600x600
        Scene scene = new Scene(backgroundPane, 900, 700);

        primaryStage.setTitle("Signin/Signup");
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
    // This method opens the OTP screen for new users when the "Sign Up" button is clicked.
    private void showNewUserOtpScreen(Stage primaryStage) {
        NewUserOtp mainOtp = new NewUserOtp();
        try {
            mainOtp.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
