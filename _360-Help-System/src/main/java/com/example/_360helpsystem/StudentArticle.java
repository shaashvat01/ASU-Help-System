package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StudentArticle extends Application {
    // The container to display the articles

    @Override
    public void start(Stage primaryStage) {
        // Top bar with back button on the left and search components centered
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 10, 10));

        // Create circular back button using ButtonStyleUtil
        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));

        // Container for back button on the left
        HBox backButtonContainer = new HBox(backButton);
        backButtonContainer.setAlignment(Pos.CENTER_LEFT);

        // Search Bar
        TextField searchField = new TextField();
        searchField.setPromptText("Search articles...");
        searchField.setPrefWidth(250);
        searchField.setPrefHeight(30);

        // Search Button
        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        searchButton.setFont(Font.font("Arial", 15));
        searchButton.setPrefHeight(29);

        // Filter Button
        Button filterButton = new Button("Filter");
        filterButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        filterButton.setFont(Font.font("Arial", 15));
        filterButton.setPrefHeight(29);
        filterButton.setOnAction(e -> showFilterOptions(primaryStage));

        // Centered layout for search bar, search button, and filter button
        HBox searchBox = new HBox(5, searchField, searchButton, filterButton);
        searchBox.setAlignment(Pos.CENTER);

        // Spacer regions on either side to center-align the searchBox
        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        // Add back button container, left spacer, searchBox, and right spacer to the top bar
        topBar.getChildren().addAll(backButtonContainer, leftSpacer, searchBox, rightSpacer);
        topBar.setAlignment(Pos.CENTER);

        // Create a ScrollPane to hold the VBox containing articles
        ScrollPane scrollPane_Article = new ScrollPane();
        scrollPane_Article.setFitToWidth(true);
        scrollPane_Article.setFitToHeight(true);
        scrollPane_Article.setStyle("-fx-background-color: transparent;");

        // VBox to hold the article container (which will display the articles dynamically)
        VBox articleContainerVBox = new VBox(10);
        articleContainerVBox.setPrefSize(800, 600);
        articleContainerVBox.setStyle("-fx-background-color: white;");

        scrollPane_Article.setContent(articleContainerVBox);
        scrollPane_Article.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane_Article.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        VBox articleSection = new VBox(20, scrollPane_Article);
        articleSection.setAlignment(Pos.TOP_CENTER);
        articleSection.setPadding(new Insets(20));

        // Main layout with only the search bar and article section
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(articleSection);
        root.setStyle("-fx-background-color: #f8f5f3;");

        // Create the scene and set it on the stage
        Scene scene = new Scene(root, 900, 700);
        primaryStage.setTitle("Student Article Dashboard");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    private void showFilterOptions(Stage primaryStage) {
        FilterOptions filterOptions = new FilterOptions();
        try{
            filterOptions.start(primaryStage);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
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