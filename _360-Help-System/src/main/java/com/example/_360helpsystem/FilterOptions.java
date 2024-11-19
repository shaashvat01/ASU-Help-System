package com.example._360helpsystem;

/*******
 * <p> FilterOptions Class </p>
 *
 * <p> Description: This class provides the UI for selecting filter options,
 * including content levels and groups. Users can clear or save their selections
 * and navigate back to the previous screen. </p>
 *
 * @version 1.00, 2024-11-19
 * @author Team - Th15
 *
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class FilterOptions extends Application {


    public void start(Stage primaryStage) {
        // Top bar with back button on the left
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 10, 10));

        // Create circular back button using ButtonStyleUtil
        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));

        // Add back button to top bar and align it to the left
        topBar.getChildren().add(backButton);
        topBar.setAlignment(Pos.CENTER_LEFT);

        // Content Level heading and checkboxes
        Label contentLevelLabel = new Label("Content Level:");
        contentLevelLabel.setFont(Font.font("Arial", 14));
        contentLevelLabel.setStyle("-fx-font-weight: bold;");

        CheckBox allContentCheckBox = new CheckBox("All");
        CheckBox beginnerCheckBox = new CheckBox("Beginner");
        CheckBox intermediateCheckBox = new CheckBox("Intermediate");
        CheckBox advancedCheckBox = new CheckBox("Advanced");
        CheckBox expertCheckBox = new CheckBox("Expert");

        VBox contentLevelOptions = new VBox(10, allContentCheckBox, beginnerCheckBox, intermediateCheckBox, advancedCheckBox, expertCheckBox);
        contentLevelOptions.setAlignment(Pos.TOP_LEFT);

        VBox contentLevelSection = new VBox(10, contentLevelLabel, contentLevelOptions);
        contentLevelSection.setAlignment(Pos.CENTER_LEFT);

        // Groups heading and checkboxes
        Label groupLabel = new Label("Groups:");
        groupLabel.setFont(Font.font("Arial", 14));
        groupLabel.setStyle("-fx-font-weight: bold;");

        CheckBox allGroupCheckBox = new CheckBox("All");
        CheckBox javafxCheckBox = new CheckBox("JavaFX");
        CheckBox eclipseCheckBox = new CheckBox("Eclipse");
        CheckBox githubCheckBox = new CheckBox("GitHub");


        VBox groupOptions = new VBox(10, allGroupCheckBox, javafxCheckBox, eclipseCheckBox, githubCheckBox);
        groupOptions.setAlignment(Pos.TOP_LEFT);

        VBox groupSection = new VBox(10, groupLabel, groupOptions);
        groupSection.setAlignment(Pos.CENTER_LEFT);

        // Place content levels and groups side by side
        HBox sectionsLayout = new HBox(50, contentLevelSection, groupSection);
        sectionsLayout.setAlignment(Pos.CENTER);

        // Buttons for clear and save actions
        Button clearButton = new Button("Clear");
        clearButton.setFont(Font.font("Arial", 16));
        clearButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        clearButton.setPrefWidth(100);

        // Clear button action to uncheck all checkboxes
        clearButton.setOnAction(e -> {
            allContentCheckBox.setSelected(false);
            beginnerCheckBox.setSelected(false);
            intermediateCheckBox.setSelected(false);
            advancedCheckBox.setSelected(false);
            expertCheckBox.setSelected(false);
            allGroupCheckBox.setSelected(false);
            javafxCheckBox.setSelected(false);
            eclipseCheckBox.setSelected(false);
            githubCheckBox.setSelected(false);
        });

        Button saveButton = new Button("Save");
        saveButton.setFont(Font.font("Arial", 16));
        saveButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        saveButton.setPrefWidth(100);

        HBox buttonLayout = new HBox(20, clearButton, saveButton);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setPadding(new Insets(20));

        // Main layout with sections and buttons
        VBox mainContent = new VBox(20, sectionsLayout, buttonLayout);
        mainContent.setAlignment(Pos.CENTER);

        // Set up the BorderPane layout
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(mainContent);
        root.setStyle("-fx-background-color: #f8f5f3;");

        // Scene settings
        Scene scene = new Scene(root, 900, 700);
        primaryStage.setTitle("File Options");
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

    public static void main(String[] args) {
        launch(args);
    }
}