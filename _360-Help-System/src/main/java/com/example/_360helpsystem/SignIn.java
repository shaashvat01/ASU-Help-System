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
 * <p> Description: This class handles the user sign-in functionality within the help system.
 * It provides the interface and validation for users to securely log in, ensuring authorized
 * access to system resources based on user credentials. </p>
 *
 * @version 1.00, 2024-10-30
 * author Team - Th15
 *
 *******/

public class SignIn extends Application {

    public static User CURRENT_USER;

    @Override
    public void start(Stage primaryStage) {

        // Background layer (Light grey overall background)
        StackPane backgroundPane = new StackPane();
        Rectangle background = new Rectangle(900, 700, Color.web("#f8f5f3"));
        backgroundPane.getChildren().add(background);

        // Create a grid layout for labels and fields
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Create the Sign In heading
        Label headingText = WindowUtil.createStyledLabel("Sign In", 24);
        headingText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        headingText.setTextFill(Color.web("#8b0000"));
        grid.add(headingText, 0, 0, 2, 1);

        GridPane.setHalignment(headingText, HPos.CENTER);
        GridPane.setMargin(headingText, new Insets(0, 0, 20, 0));

        // Create username label and text field
        Label usernameLabel = WindowUtil.createStyledLabel("Username", 16);
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(250);
        grid.add(usernameLabel, 0, 1);
        grid.add(usernameField, 1, 1);

        // Create password label and password field
        Label passwordLabel = WindowUtil.createStyledLabel("Password", 16);
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(250);
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);

        // "Forgot Password?" and "Sign Up?" links in a horizontal box
        Hyperlink forgotPasswordLink = new Hyperlink("Forgot Password?");
        forgotPasswordLink.setFont(Font.font("Arial", 15));

        Hyperlink signUpLink = new Hyperlink("Sign Up?");
        signUpLink.setFont(Font.font("Arial", 15));

        // HBox to hold both hyperlinks
        HBox linksBox = new HBox(10, forgotPasswordLink, signUpLink);
        linksBox.setAlignment(Pos.CENTER_LEFT);
        grid.add(linksBox, 1, 3);

        // Create login button
        Button loginButton = WindowUtil.createStyledButton("Sign In");
        loginButton.setPrefWidth(120);
        GridPane.setHalignment(loginButton, HPos.CENTER);

        HBox loginButtonBox = new HBox(10);
        loginButtonBox.setAlignment(Pos.CENTER);
        loginButtonBox.getChildren().add(loginButton);
        grid.add(loginButtonBox, 1, 5);

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        grid.add(errorLabel, 1, 4);

        // Add everything to the background pane and set up the scene
        backgroundPane.getChildren().add(grid);
        BorderPane root = new BorderPane();
        root.setCenter(backgroundPane);

        Scene scene = new Scene(root, 900, 700);
        primaryStage.setTitle("Sign In");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Set action for the login button
        loginButton.setOnAction(e -> {
            String enteredUsername = usernameField.getText().trim();
            String enteredPassword = passwordField.getText().trim();
            User foundUser = USER_LIST.findUser(enteredUsername);

            if (foundUser != null) {
                if (foundUser.getPassword().equals(enteredPassword)) {
                    SignInAs loginAsScreen = new SignInAs();
                    CURRENT_USER = foundUser;
                    loginAsScreen.start(primaryStage);
                } else {
                    errorLabel.setText("Wrong Password");
                }
            } else {
                errorLabel.setText("Username not found");
            }
        });

        // Action for "Forgot Password?" link
        forgotPasswordLink.setOnAction(e -> showForgotPasswordOtpScreen(primaryStage));

        // Action for "Sign Up?" link
        signUpLink.setOnAction(e -> showSignUpScreen(primaryStage));
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

    // Method to show the "Sign Up" screen
    private void showSignUpScreen(Stage primaryStage) {
        NewUserOtp newUserOtp = new NewUserOtp();
        try {
            newUserOtp.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
