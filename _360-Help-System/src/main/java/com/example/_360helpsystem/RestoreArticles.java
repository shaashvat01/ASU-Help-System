package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RestoreArticles extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Creating a label for instruction
        Text instructionText = new Text("Please select the groups to Restore:");
        instructionText.setFont(Font.font("Arial", 20));

        // Creating Checkboxes for groups and setting font
        CheckBox eclipseCheckbox = new CheckBox("Eclipse");
        eclipseCheckbox.setFont(Font.font("Arial", 18));
        eclipseCheckbox.setPrefWidth(200);  // Ensure consistent width

        CheckBox javaFxCheckbox = new CheckBox("JavaFX");
        javaFxCheckbox.setFont(Font.font("Arial", 18));
        javaFxCheckbox.setPrefWidth(200);  // Ensure consistent width

        CheckBox h2DatabaseCheckbox = new CheckBox("H2 Database");
        h2DatabaseCheckbox.setFont(Font.font("Arial", 18));
        h2DatabaseCheckbox.setPrefWidth(200);  // Ensure consistent width

        CheckBox sqlFiddleCheckbox = new CheckBox("SQL Fiddle");
        sqlFiddleCheckbox.setFont(Font.font("Arial", 18));
        sqlFiddleCheckbox.setPrefWidth(200);  // Ensure consistent width

        CheckBox gitHubCheckbox = new CheckBox("GitHub");
        gitHubCheckbox.setFont(Font.font("Arial", 18));
        gitHubCheckbox.setPrefWidth(200);  // Ensure consistent width

        // Remove all the existing help articles from the selected groups Button
        Button removeAllButton = new Button("Remove all the existing help articles from the selected groups");
        removeAllButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        removeAllButton.setFont(javafx.scene.text.Font.font("Arial", 18));
        removeAllButton.setPrefWidth(750);

        Button mergeToExistingButton = new Button("Merge the backed-up copies with the current help articles");
       mergeToExistingButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        mergeToExistingButton.setFont(javafx.scene.text.Font.font("Arial", 18));
        mergeToExistingButton.setPrefWidth(750);

        // VBox for checkboxes and instruction text centered and just above the Backup button
        VBox checkboxesLayout = new VBox(15); // 15 is the spacing between elements
        checkboxesLayout.setAlignment(Pos.CENTER);  // Align everything to the center
        checkboxesLayout.getChildren().addAll(instructionText, eclipseCheckbox, javaFxCheckbox, h2DatabaseCheckbox, sqlFiddleCheckbox, gitHubCheckbox);

        // Center layout for checkboxes and backup button
        VBox contentLayout = new VBox(20); // 20 is the spacing between elements
        contentLayout.setPadding(new Insets(20));
        contentLayout.setAlignment(Pos.CENTER);
        contentLayout.getChildren().addAll(checkboxesLayout, removeAllButton, mergeToExistingButton);

        // Back Button using the ButtonStyleUtil class
        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));

        // Logout Button
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        logoutButton.setFont(javafx.scene.text.Font.font("Arial", 18));
        logoutButton.setPrefWidth(100);  // Set preferred width to control the button size
        logoutButton.setOnAction(e -> showSignInPage(primaryStage));

        // Top bar layout with Back and Logout button
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 0, 10));
        topBar.setSpacing(10);

        HBox leftBox = new HBox(backButton);
        leftBox.setAlignment(Pos.TOP_LEFT);  // Align back button to the left

        HBox rightBox = new HBox(logoutButton);
        rightBox.setAlignment(Pos.TOP_RIGHT);  // Align logout button to the right

        topBar.getChildren().addAll(leftBox, rightBox);
        HBox.setHgrow(rightBox, Priority.ALWAYS); // Make the rightBox grow to push the logout button to the right

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(contentLayout); // Add checkboxes and backup button in the center
        root.setStyle("-fx-background-color: #f8f5f3;");  // Set the background color similar to AdminPage

        Scene backupScene = new Scene(root, 900, 700);  // Set the window size to 800x600

        primaryStage.setTitle("Backup Page");
        primaryStage.setScene(backupScene);
        primaryStage.setResizable(false);  // Disable resizing for fixed size
        primaryStage.show();
    }

    private void showPreviousScreen(Stage primaryStage) {
        Articles articles = new Articles();
        try {
            articles.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showSignInPage(Stage primaryStage) {
        SignIn signin = new SignIn();
        try {
            signin.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
