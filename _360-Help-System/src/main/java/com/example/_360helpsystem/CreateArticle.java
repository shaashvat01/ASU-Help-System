package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;


public class CreateArticle extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Title Label and TextField
        Label titleLabel = new Label("Title:");
        titleLabel.setFont(Font.font("Arial", 14));
        titleLabel.setStyle("-fx-font-weight: bold;");  // Make the label bold
        TextField titleField = new TextField();
        titleField.setFont(Font.font("Arial", 14));  // Reduced font size
        titleField.setPrefWidth(400);  // Increased width

        // Level Label and ComboBox (with "Choose a level" option)
        Label levelLabel = new Label("Level:");
        levelLabel.setFont(Font.font("Arial", 14));
        levelLabel.setStyle("-fx-font-weight: bold;");  // Make the label bold
        ComboBox<String> levelComboBox = new ComboBox<>();
        levelComboBox.getItems().addAll("Choose a level", "Beginner", "Intermediate", "Advanced", "Expert");
        levelComboBox.setValue("Choose a level");  // Set default option
        levelComboBox.setStyle("-fx-font-size: 14px;");  // Smaller font for consistency

        // Create a layout to place Title and Level vertically
        VBox titleAndLevelLayout = new VBox(10);  // Vertical box layout with spacing of 10px
        titleAndLevelLayout.setAlignment(Pos.CENTER_LEFT);  // Align to the left
        titleAndLevelLayout.getChildren().addAll(titleLabel, titleField, levelLabel, levelComboBox);

        // Description Label and TextField
        Label descriptionLabel = new Label("Short Description:");
        descriptionLabel.setFont(Font.font("Arial", 14));
        descriptionLabel.setStyle("-fx-font-weight: bold;");  // Make the label bold
        TextField descriptionField = new TextField();
        descriptionField.setFont(Font.font("Arial", 14));  // Reduced font size
        descriptionField.setPrefWidth(400);

        // Keywords Label and TextField
        Label keywordsLabel = new Label("Keywords:");
        keywordsLabel.setFont(Font.font("Arial", 14));
        keywordsLabel.setStyle("-fx-font-weight: bold;");  // Make the label bold
        TextField keywordsField = new TextField();
        keywordsField.setFont(Font.font("Arial", 14));  // Reduced font size
        keywordsField.setPrefWidth(400);

        // Reference Links Label and TextField
        Label referenceLinksLabel = new Label("Reference Links:");
        referenceLinksLabel.setFont(Font.font("Arial", 14));
        referenceLinksLabel.setStyle("-fx-font-weight: bold;");  // Make the label bold
        TextField referenceLinksField = new TextField();
        referenceLinksField.setFont(Font.font("Arial", 14));  // Reduced font size
        referenceLinksField.setPrefWidth(400);

        // Grouping Identifiers Label and CheckBoxes
        Label groupLabel = new Label("Grouping Identifiers:");
        groupLabel.setFont(Font.font("Arial", 14));
        groupLabel.setStyle("-fx-font-weight: bold;");

        // CheckBoxes for Grouping Identifiers
        CheckBox groupEclipse = new CheckBox("Eclipse");
        CheckBox groupJavaFX = new CheckBox("JavaFX");
        CheckBox groupH2 = new CheckBox("H2");
        CheckBox groupGitHub = new CheckBox("GitHub");
        CheckBox groupSQL = new CheckBox("SQL");

        // HBox to hold the CheckBoxes in a row
        HBox groupCheckBoxLayout = new HBox(10);  // Horizontal layout with 10px spacing
        groupCheckBoxLayout.getChildren().addAll(groupEclipse, groupJavaFX, groupH2, groupGitHub, groupSQL);
        groupCheckBoxLayout.setAlignment(Pos.CENTER_LEFT);

        // Body Label and adjustable TextArea (positioned at the bottom)
        Label bodyLabel = new Label("Body:");
        bodyLabel.setFont(Font.font("Arial", 14));
        bodyLabel.setStyle("-fx-font-weight: bold;");  // Make the label bold
        TextArea bodyField = new TextArea();
        bodyField.setPromptText("Body of the help article");
        bodyField.setFont(Font.font("Arial", 14));  // Reduced font size
        bodyField.setPrefWidth(400);
        bodyField.setPrefHeight(200);  // Increased height for the TextArea
        bodyField.setWrapText(true);  // Allow text wrapping in the TextArea

        // Buttons for actions (centered)
        Button saveButton = new Button("Save Article");
        saveButton.setFont(Font.font("Arial", 16));
        saveButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        saveButton.setPrefWidth(150);  // Button width
        saveButton.setOnAction(e -> {
            List<String> selectedGroups = new ArrayList<>();
            if (groupEclipse.isSelected()) selectedGroups.add("Eclipse");
            if (groupJavaFX.isSelected()) selectedGroups.add("JavaFX");
            if (groupH2.isSelected()) selectedGroups.add("H2");
            if (groupGitHub.isSelected()) selectedGroups.add("GitHub");
            if (groupSQL.isSelected()) selectedGroups.add("SQL");
            System.out.println("Selected Groups: " + selectedGroups);
        });

        Button clearButton = new Button("Clear Fields");
        clearButton.setFont(Font.font("Arial", 16));
        clearButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        clearButton.setPrefWidth(150);  // Button width
        clearButton.setOnAction(e -> clearFields(titleField, descriptionField, keywordsField, bodyField, referenceLinksField, groupEclipse, groupJavaFX, groupH2, groupGitHub, groupSQL));

        // Layout for buttons (centered horizontally)
        HBox buttonLayout = new HBox(20);  // Horizontal box layout with spacing between buttons
        buttonLayout.setAlignment(Pos.CENTER);  // Center the buttons
        buttonLayout.getChildren().addAll(saveButton, clearButton);

        // Layout for form fields (stacked vertically)
        VBox formLayout = new VBox(15);
        formLayout.setPadding(new Insets(20));
        formLayout.setAlignment(Pos.CENTER_LEFT);  // Align fields to the left
        formLayout.getChildren().addAll(titleAndLevelLayout, descriptionLabel, descriptionField, keywordsLabel, keywordsField, referenceLinksLabel, referenceLinksField, groupLabel, groupCheckBoxLayout, bodyLabel, bodyField, buttonLayout);

        // Create Back button using ButtonStyleUtil
        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));

        // Logout button
        Button logoutButton = new Button("Logout");
        logoutButton.setFont(Font.font("Arial", 16));
        logoutButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        logoutButton.setOnAction(e -> showSignInScreen(primaryStage));

        // Layout for the top bar
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(5, 10, 0, 10));  // Reduced padding to move the bar up
        topBar.setAlignment(Pos.CENTER);

        HBox leftBox = new HBox(backButton);  // Back button aligned to the left
        leftBox.setAlignment(Pos.TOP_LEFT);

        HBox rightBox = new HBox(logoutButton);  // Logout button aligned to the right
        rightBox.setAlignment(Pos.TOP_RIGHT);

        topBar.getChildren().addAll(leftBox, rightBox);
        HBox.setHgrow(rightBox, Priority.ALWAYS);  // Make the right box grow to push the Logout button to the right

        // Main layout
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(formLayout);
        root.setStyle("-fx-background-color: #f8f5f3;");

        // Increased screen size
        Scene scene = new Scene(root, 900, 700);  // Increased window size

        primaryStage.setTitle("Create Article");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true); // Allow window resizing
        primaryStage.show();
    }

    // Method to clear all fields
    private void clearFields(TextField titleField, TextField descriptionField, TextField keywordsField, TextArea bodyField, TextField referenceLinksField, CheckBox... groupCheckBoxes) {
        titleField.clear();
        descriptionField.clear();
        keywordsField.clear();
        bodyField.clear();
        referenceLinksField.clear();
        for (CheckBox checkBox : groupCheckBoxes) {
            checkBox.setSelected(false);  // Deselect all checkboxes
        }
    }


    private void showPreviousScreen(Stage primaryStage) {
        Article article = new Article();
        try{
            article.start(primaryStage);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    // Show sign-in screen (placeholder)
    private void showSignInScreen(Stage stage) {
        SignIn signIn = new SignIn();
        try {
            signIn.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

