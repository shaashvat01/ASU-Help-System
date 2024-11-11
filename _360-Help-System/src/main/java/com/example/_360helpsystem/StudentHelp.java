package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentHelp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Top bar with back button on the left and a spacer to keep alignment
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 10, 10));

        // Create circular back button using ButtonStyleUtil
        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));

        // Spacer to push content alignment
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS); // Make the spacer grow

        // Add back button and spacer to the top bar
        topBar.getChildren().addAll(backButton, spacer);
        topBar.setAlignment(Pos.CENTER);

        // Sidebar VBox with a blank style
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(20, 5, 10, 5));
        sidebar.setStyle("-fx-background-color: #333;");
        sidebar.setPrefWidth(160);

        // Main content area
        VBox mainContentArea = new VBox(30);
        mainContentArea.setAlignment(Pos.CENTER); // Center the content vertically and horizontally
        mainContentArea.setPadding(new Insets(20));

        // Section 1: Send a Generic Message
        Label genericMessageLabel = new Label("Send a Generic Message");
        genericMessageLabel.setStyle("-fx-font-size: 18px;");
        TextField genericMessageField = new TextField();
        genericMessageField.setPromptText("Write a message"); // Placeholder
        Button genericMessageButton = new Button("Enter");
        genericMessageButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white; -fx-font-size: 16px;");
        genericMessageButton.setOnAction(e -> showMessageSentAlert());

        HBox genericMessageBox = new HBox(10, genericMessageField, genericMessageButton);
        genericMessageBox.setAlignment(Pos.CENTER);

        VBox genericMessageSection = new VBox(10, genericMessageLabel, genericMessageBox);
        genericMessageSection.setAlignment(Pos.CENTER);

        // Section 2: Send a Message to Our Team
        Label teamMessageLabel = new Label("Send a Message to Our Team");
        teamMessageLabel.setStyle("-fx-font-size: 18px;");
        TextField teamMessageField = new TextField();
        teamMessageField.setPromptText("Write a message"); // Placeholder
        Button teamMessageButton = new Button("Enter");
        teamMessageButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white; -fx-font-size: 16px;");
        teamMessageButton.setOnAction(e -> showMessageSentAlert());

        HBox teamMessageBox = new HBox(10, teamMessageField, teamMessageButton);
        teamMessageBox.setAlignment(Pos.CENTER);

        VBox teamMessageSection = new VBox(10, teamMessageLabel, teamMessageBox);
        teamMessageSection.setAlignment(Pos.CENTER);

        // Add both sections to the main content area
        mainContentArea.getChildren().addAll(genericMessageSection, teamMessageSection);

        // Main layout with sidebar and main content area centered
        HBox mainLayout = new HBox(sidebar, mainContentArea);
        HBox.setHgrow(mainContentArea, Priority.ALWAYS); // Make the main content area take available space

        // BorderPane root to include top bar and layout
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(mainLayout);
        root.setStyle("-fx-background-color: #f8f5f3;");

        // Scene settings
        Scene scene = new Scene(root, 900, 700);
        primaryStage.setTitle("Student Help");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to handle back button logic
    private void showPreviousScreen(Stage primaryStage) {
        StudentPage studentPage = new StudentPage();
        try {
            studentPage.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Method to show the "Message has been sent" alert
    private void showMessageSentAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Message has been sent.");
        alert.showAndWait(); // Displays the alert and waits for user to click "OK"
    }

    public static void main(String[] args) {
        launch(args);
    }
}
