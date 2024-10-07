package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignInAs extends Application {

    public void start(Stage primaryStage) {
        // Title Text
        Text title = new Text("LOGIN AS");
        title.setFont(Font.font("Arial", 36));

        //Admin Button
        Button adminButton = new Button("Admin");
        adminButton.setFont(Font.font("Arial", 20));
        adminButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        adminButton.setPrefWidth(200);

        // Instructor Button
        Button instructorButton = new Button("INSTRUCTOR");
        instructorButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        instructorButton.setFont(Font.font("Arial", 18));
        instructorButton.setPrefWidth(200);

        // Student Button
        Button studentButton = new Button("STUDENT");
        studentButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        studentButton.setFont(Font.font("Arial", 18));
        studentButton.setPrefWidth(200);

        // VBox layout
        VBox layout = new VBox(20);  // 20 is the spacing between elements
        layout.setStyle("-fx-background-color: #f8f5f3;");  // Light yellow background
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(title, instructorButton, studentButton, adminButton);
        layout.setMinWidth(400);  // Set minimum width for layout

        // Create scene for the "Login As" screen
        Scene loginAsScene = new Scene(layout, 400, 300);

        // Switch to the new scene
        primaryStage.setScene(loginAsScene);

        adminButton.setOnAction(e -> showAdminPageScreen(primaryStage));
        studentButton.setOnAction(e -> showHomePageScreen(primaryStage));
        instructorButton.setOnAction(e -> showHomePageScreen(primaryStage));
    }

    private void showAdminPageScreen(Stage primaryStage) {
        AdminPage adminPage = new AdminPage();
        try{
            adminPage.start(primaryStage);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void showHomePageScreen(Stage primaryStage) {
        HomePage homePage = new HomePage();
        try{
            homePage.start(primaryStage);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}