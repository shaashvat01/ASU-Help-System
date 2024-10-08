package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SignInAs extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Title Text
        Label title = WindowUtil.createStyledLabel("LOGIN AS", 36);  // Adjusted font size to fit within 600x600 screen

        // Admin Button using WindowUtil's standardized button style
        Button adminButton = WindowUtil.createStyledButton("Admin");
        adminButton.setFont(WindowUtil.createStyledLabel("Admin", 20).getFont());  // Adjusted button font size
        adminButton.setPrefWidth(250);  // Adjusted button width for smaller screen

        // Instructor Button
        Button instructorButton = WindowUtil.createStyledButton("INSTRUCTOR");
        instructorButton.setFont(WindowUtil.createStyledLabel("INSTRUCTOR", 20).getFont());  // Consistent font size
        instructorButton.setPrefWidth(250);  // Adjusted button width

        // Student Button
        Button studentButton = WindowUtil.createStyledButton("STUDENT");
        studentButton.setFont(WindowUtil.createStyledLabel("STUDENT", 20).getFont());  // Consistent font size
        studentButton.setPrefWidth(250);  // Adjusted button width

        // VBox layout using WindowUtil
        VBox layout = WindowUtil.createStandardLayout();  // Standardized VBox layout
        layout.setPadding(new Insets(30, 30, 30, 30));  // Adjusted padding for 600x600 screen
        layout.setAlignment(Pos.CENTER);  // Center the layout
        layout.setSpacing(20);  // Adjusted spacing between elements
        layout.getChildren().addAll(title, instructorButton, studentButton, adminButton);

        // Create the circular back button using ButtonStyleUtil
        Button backButton = ButtonStyleUtil.createCircularBackButton();

        // Handle back button action
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));  // Implement your back button logic here

        // Create a BorderPane to position the back button at the top left
        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(layout);

        // Align the back button to the top-left and set padding (gap of 5)
        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));  // Gap of 5 from top and left

        // Create scene for the "Login As" screen with a window size of 600x600
        Scene loginAsScene = new Scene(root, 600, 600);  // Set the window size to 600x600

        // Set the scene and show the stage
        primaryStage.setScene(loginAsScene);
        primaryStage.setTitle("Login As");
        primaryStage.setResizable(false);  // Disable resizing to keep consistent layout
        primaryStage.show();

        // Button actions
        adminButton.setOnAction(e -> showAdminPageScreen(primaryStage));
        studentButton.setOnAction(e -> showHomePageScreen(primaryStage));
        instructorButton.setOnAction(e -> showHomePageScreen(primaryStage));
    }

    // Method to go back to the previous screen
    private void showPreviousScreen(Stage primaryStage) {
        SignIn signIn = new SignIn();
        try{
            signIn.start(primaryStage);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void showAdminPageScreen(Stage primaryStage) {
        AdminPage adminPage = new AdminPage();
        try {
            adminPage.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showHomePageScreen(Stage primaryStage) {
        HomePage homePage = new HomePage();
        try {
            homePage.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
