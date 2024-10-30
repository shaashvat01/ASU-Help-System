package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Backend.*;

/*******
 * <p> SignInAs Class </p>
 *
 * <p> Description: This class provides the functionality to sign in as a specific user role within the help system.
 * It enables users to choose their role upon signing in, allowing customized access and interactions
 * based on their selected role (e.g., Admin, Instructor, Student). </p>
 *
 * @version 1.00, 2024-10-30
 * author Team - Th15
 *
 *******/

public class SignInAs extends Application {
    // This method sets up the screen where users can select their role (Admin, Instructor, Student).
    @Override
    public void start(Stage primaryStage) {
        // Get the currently logged-in user
        User currentUser = SignIn.CURRENT_USER;

        // Title Text
        Label title = WindowUtil.createStyledLabel("LOGIN AS", 36);

        // VBox layout using WindowUtil
        VBox layout = WindowUtil.createStandardLayout();
        layout.setPadding(new Insets(30, 30, 30, 30));
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(20);

        // Conditionally add buttons based on the user role
        if (currentUser.isAdmin()) {
            Button adminButton = WindowUtil.createStyledButton("Admin");
            adminButton.setFont(WindowUtil.createStyledLabel("Admin", 20).getFont());
            adminButton.setPrefWidth(250);
            layout.getChildren().add(adminButton);
            adminButton.setOnAction(e -> showAdminPageScreen(primaryStage));
        }

        if (currentUser.isInstructor()) {
            Button instructorButton = WindowUtil.createStyledButton("INSTRUCTOR");
            instructorButton.setFont(WindowUtil.createStyledLabel("INSTRUCTOR", 20).getFont());
            instructorButton.setPrefWidth(250);
            layout.getChildren().add(instructorButton);
            instructorButton.setOnAction(e -> showInstructorPageScreen(primaryStage));
        }

        if (currentUser.isStudent()) {
            Button studentButton = WindowUtil.createStyledButton("STUDENT");
            studentButton.setFont(WindowUtil.createStyledLabel("STUDENT", 20).getFont());
            studentButton.setPrefWidth(250);
            layout.getChildren().add(studentButton);
            studentButton.setOnAction(e -> showStudentPageScreen(primaryStage));
        }

        // Create the top bar with the Logout button
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 10, 10));

        // Spacer to push the "Logout" button to the right
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        topBar.getChildren().add(spacer);

        // "Logout" button on the right
        Button logoutButton = WindowUtil.createStyledButton("Logout");
        logoutButton.setFont(WindowUtil.createStyledLabel("Logout", 18).getFont());
        topBar.getChildren().add(logoutButton);
        topBar.setAlignment(Pos.CENTER);

        // Set action for the logout button to return to the SignIn screen
        logoutButton.setOnAction(e -> showSignInScreen(primaryStage));

        // Create a BorderPane to position the layout in the center and top bar at the top
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(layout);

        // Create scene for the "Login As" screen with a window size of 600x600
        Scene loginAsScene = new Scene(root, 900, 700);

        // Set the scene and show the stage
        primaryStage.setScene(loginAsScene);
        primaryStage.setTitle("Login As");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // Method to show SignIn screen (triggered by Logout button)
    private void showSignInScreen(Stage primaryStage) {
        SignIn signInScreen = new SignIn();
        try {
            signInScreen.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Method to show admin screen
    private void showAdminPageScreen(Stage primaryStage) {
        AdminPage adminPage = new AdminPage();
        try {
            adminPage.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Method to show instructor home page
    private void showInstructorPageScreen(Stage primaryStage) {
        InstructorPage homePage = new InstructorPage();
        try {
            homePage.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Method to show student home page
    private void showStudentPageScreen(Stage primaryStage) {
        StudentPage studentPage = new StudentPage();
        try {
            studentPage.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // main
    public static void main(String[] args) {
        launch(args);
    }
}
