package com.example._360helpsystem;

import Backend.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import static com.example._360helpsystem.CreateAdminAccount.USER_LIST;

/*******
 * <p> SignIn Class </p>
 *
 * <p> Description: This class handles the sign-in process for users.
 * It provides a screen for entering username and password, and validates credentials. </p>
 *
 * @version 1.00, 2024-10-09
 * @author Team - Th15
 *
 */

public class SignIn extends Application {

    public static User CURRENT_USER;  // Declare the User as an instance variable
    // This method sets up the screen where users can enter their username and password.
    @Override
    public void start(Stage primaryStage) {

        // Background layer (Light grey overall background)
        StackPane backgroundPane = new StackPane();
        Rectangle background = new Rectangle(900, 700, Color.web("#f8f5f3"));  // Very light grey, adjusted to 600x600
        backgroundPane.getChildren().add(background);

        // Create a grid layout for labels and fields
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);  // Center the grid
        grid.setHgap(10);  // Horizontal gap between columns
        grid.setVgap(10);  // Vertical gap between rows (to match SignUp)
        grid.setPadding(new Insets(25, 25, 25, 25));  // Padding to avoid edge overlap

        // Create the Sign In heading using the standardized label style
        Label headingText = WindowUtil.createStyledLabel("Sign In", 24);
        headingText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        headingText.setTextFill(Color.web("#8b0000"));  // Set custom color for the heading
        grid.add(headingText, 0, 0, 2, 1);  // Span heading across 2 columns

        // Center the heading text horizontally and add vertical spacing
        GridPane.setHalignment(headingText, HPos.CENTER);  // Center horizontally
        GridPane.setMargin(headingText, new Insets(0, 0, 20, 0));  // Add margin below the heading

        // Create username label and text field
        Label usernameLabel = WindowUtil.createStyledLabel("Username", 16);  // Label with standard style
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(250);  // Adjust width for larger screen
        grid.add(usernameLabel, 0, 1);  // First column
        grid.add(usernameField, 1, 1);  // Second column

        // Create password label and password field
        Label passwordLabel = WindowUtil.createStyledLabel("Password", 16);  // Label with standard style
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(250);  // Adjust width for larger screen
        grid.add(passwordLabel, 0, 2);  // First column
        grid.add(passwordField, 1, 2);  // Second column

        // Add "Forgot Password?" hyperlink next to the password field
        Hyperlink forgotPasswordLink = new Hyperlink("Forgot Password?");
        forgotPasswordLink.setFont(Font.font("Arial", 15));
        grid.add(forgotPasswordLink, 1, 3);  // Below the password field

        // Create login button using WindowUtil's standardized button style
        Button loginButton = WindowUtil.createStyledButton("Sign In");
        loginButton.setPrefWidth(120);  // Increased width for better fit

        // Center the login button horizontally
        GridPane.setHalignment(loginButton, HPos.CENTER);  // Center the button horizontally

        // Create an HBox for the button
        HBox loginButtonBox = new HBox(10);
        loginButtonBox.setAlignment(Pos.CENTER);  // Align the button to the center
        loginButtonBox.getChildren().add(loginButton);
        grid.add(loginButtonBox, 1, 5);  // Add button to the second column, new row

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);  // Set text color to red for visibility
        grid.add(errorLabel, 1, 4);  // Add it to row 5, below the Confirm Password field

        // Create the circular back button using ButtonStyleUtil
        Button backButton = ButtonStyleUtil.createCircularBackButton();

        // Handle back button action
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));  // Implement your back button logic here

        // Add everything to the background pane and set up the scene
        backgroundPane.getChildren().add(grid);

        // Create a BorderPane to position the back button at the top left
        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(backgroundPane);

        // Align the back button to the top-left and set padding (gap of 5)
        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));  // Gap of 5 from top and left

        // Set the scene size to 600x600 and make all elements visible
        Scene scene = new Scene(root, 900, 700);  // Set scene size to 600x600
        primaryStage.setTitle("Sign In");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Set action for the login button
        loginButton.setOnAction(e -> {
            // Get the entered username and password
            String enteredUsername = usernameField.getText().trim();
            String enteredPassword = passwordField.getText().trim();

            // Check if the username exists in the UserList
            User foundUser = USER_LIST.findUser(enteredUsername);

            if (foundUser != null) {
                // If the user exists, check the password
                if (foundUser.getPassword().equals(enteredPassword)) {
                    // If the password matches, proceed to the next screen
                    SignInAs loginAsScreen = new SignInAs();
                    CURRENT_USER = foundUser;
                    loginAsScreen.start(primaryStage);
                }
                else {
                    // If the password is incorrect, show an alert
                    errorLabel.setText("Wrong Password");
                }
            }
            else {
                // If the username doesn't exist, show an alert
                errorLabel.setText("Username not found");
            }
        });

        // Action for the "Forgot Password" link
        forgotPasswordLink.setOnAction(e -> showForgotPasswordOtpScreen(primaryStage));
    }
    // Method to go back to the previous screen
    private void showPreviousScreen(Stage primaryStage) {
        Main main = new Main();
        try{
            main.start(primaryStage);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    // Method to show the "Forgot Password" screen
    private void showForgotPasswordOtpScreen(Stage primaryStage) {
        ForgotPasswordOtp mainOtp = new ForgotPasswordOtp();
        try {
            mainOtp.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    // main
    public static void main(String[] args) {
        launch(args);
    }
}
