package com.example._360helpsystem;

import Backend.Article;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import static com.example._360helpsystem.CreateAdminAccount.ARTICLE_LIST;

public class StudentArticle extends Application {

    private VBox articleContainerVBox; // The container to display the articles

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
        articleContainerVBox = new VBox(10);
        articleContainerVBox.setPrefSize(800, 600);
        articleContainerVBox.setStyle("-fx-background-color: white;");

        scrollPane_Article.setContent(articleContainerVBox);
        scrollPane_Article.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane_Article.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        VBox articleSection = new VBox(20, scrollPane_Article);
        articleSection.setAlignment(Pos.TOP_CENTER);
        articleSection.setPadding(new Insets(20));

        // Main layout with only the search bar and article section
        BorderPane mainContent = new BorderPane();
        mainContent.setTop(topBar);
        mainContent.setCenter(articleSection);
        mainContent.setStyle("-fx-background-color: #f8f5f3;");

        // Filter options panel (hidden initially)
        VBox filterPanel = createFilterPanel();

        // StackPane to overlay filter panel on the main content
        StackPane root = new StackPane(mainContent, filterPanel);
        Scene scene = new Scene(root, 900, 700);
        primaryStage.setTitle("Student Article Dashboard");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        // Slide filter panel in and out
        TranslateTransition slideIn = new TranslateTransition(Duration.millis(300), filterPanel);
        slideIn.setFromX(900);
        slideIn.setToX(0);

        TranslateTransition slideOut = new TranslateTransition(Duration.millis(300), filterPanel);
        slideOut.setFromX(0);
        slideOut.setToX(900);

        // Set action for filter button to slide in the filter panel
        filterButton.setOnAction(e -> {
            filterPanel.setVisible(true);
            slideIn.play();
        });

        // Set action for save button to slide out and hide the filter panel
        Button saveButton = (Button) filterPanel.lookup("#saveButton");
        saveButton.setOnAction(e -> slideOut.play());
        slideOut.setOnFinished(e -> filterPanel.setVisible(false));

        // Display all articles in the "General" group by default
        displayGeneralArticles();
    }

    private VBox createFilterPanel() {
        // Filter options panel with Content Level and Group checkboxes
        VBox filterPanel = new VBox(20);
        filterPanel.setPadding(new Insets(20));
        filterPanel.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ccc;");
        filterPanel.setPrefWidth(300);
        filterPanel.setTranslateX(900); // Initially hidden off-screen
        filterPanel.setVisible(false); // Hidden initially

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

        // Groups heading and checkboxes
        Label groupLabel = new Label("Groups:");
        groupLabel.setFont(Font.font("Arial", 14));
        groupLabel.setStyle("-fx-font-weight: bold;");

        CheckBox allGroupCheckBox = new CheckBox("All");
        CheckBox javafxCheckBox = new CheckBox("JavaFX");
        CheckBox eclipseCheckBox = new CheckBox("Eclipse");
        CheckBox githubCheckBox = new CheckBox("GitHub");

        VBox groupOptions = new VBox(10, allGroupCheckBox, javafxCheckBox, eclipseCheckBox, githubCheckBox);

        // Search By heading and checkboxes
        Label searchByLabel = new Label("Search By:");
        searchByLabel.setFont(Font.font("Arial", 14));
        searchByLabel.setStyle("-fx-font-weight: bold;");

        CheckBox titleCheckBox = new CheckBox("Title");
        CheckBox authorCheckBox = new CheckBox("Author");
        CheckBox uidCheckBox = new CheckBox("UID");

        VBox searchByOptions = new VBox(10, titleCheckBox, authorCheckBox, uidCheckBox);

        // Buttons for clearing and saving filter selections
        Button clearButton = new Button("Clear");
        clearButton.setFont(Font.font("Arial", 16));
        clearButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        clearButton.setPrefWidth(100);
        clearButton.setOnAction(e -> {
            // Clear all checkboxes
            allContentCheckBox.setSelected(false);
            beginnerCheckBox.setSelected(false);
            intermediateCheckBox.setSelected(false);
            advancedCheckBox.setSelected(false);
            expertCheckBox.setSelected(false);
            allGroupCheckBox.setSelected(false);
            javafxCheckBox.setSelected(false);
            eclipseCheckBox.setSelected(false);
            githubCheckBox.setSelected(false);
            titleCheckBox.setSelected(false);
            authorCheckBox.setSelected(false);
            uidCheckBox.setSelected(false);
        });

        Button saveButton = new Button("Save");
        saveButton.setFont(Font.font("Arial", 16));
        saveButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        saveButton.setPrefWidth(100);
        saveButton.setId("saveButton");

        HBox buttonLayout = new HBox(20, clearButton, saveButton);
        buttonLayout.setAlignment(Pos.CENTER);

        // Add all elements to the filter panel
        filterPanel.getChildren().addAll(contentLevelLabel, contentLevelOptions, groupLabel, groupOptions, searchByLabel, searchByOptions, buttonLayout);
        return filterPanel;
    }

    private void showPreviousScreen(Stage primaryStage) {
        StudentPage studentPage = new StudentPage();
        try {
            studentPage.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Display articles in the "General" group
    private void displayGeneralArticles() {
        articleContainerVBox.getChildren().clear();

        for (Article article : ARTICLE_LIST) {
            if (article.hasGroup("General")) {
                VBox articleBox = new VBox(5);
                articleBox.setPadding(new Insets(10));
                articleBox.setStyle("-fx-border-color: lightgray; -fx-border-width: 0 0 1 0; -fx-background-color: white;");
                articleBox.setAlignment(Pos.TOP_LEFT);

                HBox titleLevelBox = new HBox(10);
                Label titleLabel = new Label(article.getTitle());
                titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 17px; -fx-text-fill: #8b0000;");
                titleLabel.setFont(Font.font("Arial", 17));

                Label levelLabel = new Label("(" + article.getLevel() + ")");
                levelLabel.setStyle("-fx-text-fill: gray; -fx-font-size: 14px;");
                levelLabel.setFont(Font.font("Arial", 14));

                titleLevelBox.getChildren().addAll(titleLabel, levelLabel);
                titleLevelBox.setAlignment(Pos.TOP_LEFT);

                Label abstractLabel = new Label(article.getAbs());
                abstractLabel.setWrapText(true);

                Button requestAccessButton = new Button("Request Access");
                requestAccessButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
                requestAccessButton.setFont(Font.font("Arial", 14));
                requestAccessButton.setOnAction(e -> showArticleDetails(article));

                HBox optionsBox = new HBox(requestAccessButton);
                optionsBox.setAlignment(Pos.TOP_RIGHT);

                articleBox.getChildren().addAll(titleLevelBox, abstractLabel, optionsBox);
                articleContainerVBox.getChildren().add(articleBox);
            }
        }
    }

    private void showArticleDetails(Article article) {
        // Create a new stage for the details window
        Stage detailStage = new Stage();
        detailStage.setTitle("Article Details - " + article.getTitle());

        // Article details layout
        VBox detailsLayout = new VBox(10);
        detailsLayout.setPadding(new Insets(20));
        detailsLayout.setAlignment(Pos.TOP_LEFT);

        // Title
        Label titleLabel = new Label("Title: " + article.getTitle());
        titleLabel.setFont(Font.font("Arial", 18));
        titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Level
        Label levelLabel = new Label("Level: " + article.getLevel());
        levelLabel.setFont(Font.font("Arial", 14));
        levelLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #7f8c8d;");

        // Abstract
        Label abstractHeading = new Label("Abstract:");
        abstractHeading.setFont(Font.font("Arial", 14));
        abstractHeading.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        Label abstractLabel = new Label(article.getAbs());
        abstractLabel.setWrapText(true);

        // Keywords
        Label keywordsHeading = new Label("Keywords:");
        keywordsHeading.setFont(Font.font("Arial", 14));
        keywordsHeading.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        Label keywordsLabel = new Label(article.getKeywords());
        keywordsLabel.setWrapText(true);

        // Reference Links
        Label referenceLinksHeading = new Label("Reference Links:");
        referenceLinksHeading.setFont(Font.font("Arial", 14));
        referenceLinksHeading.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        Label referenceLinksLabel = new Label(article.getLinks());
        referenceLinksLabel.setWrapText(true);



        // Body or Content
        Label bodyHeading = new Label("Body:");
        bodyHeading.setFont(Font.font("Arial", 14));
        bodyHeading.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        Label bodyLabel = new Label(article.getBody());
        bodyLabel.setWrapText(true);

        // Add all details to the layout
        detailsLayout.getChildren().addAll(
                titleLabel,
                levelLabel,
                abstractHeading, abstractLabel,
                keywordsHeading, keywordsLabel,
                referenceLinksHeading, referenceLinksLabel,
                bodyHeading, bodyLabel
        );

        // Create and set the scene
        Scene detailScene = new Scene(detailsLayout, 600, 500);
        detailStage.setScene(detailScene);
        detailStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
