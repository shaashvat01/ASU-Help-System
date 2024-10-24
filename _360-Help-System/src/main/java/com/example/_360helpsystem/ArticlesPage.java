package com.example._360helpsystem;

import Backend.Article;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
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

import static com.example._360helpsystem.CreateAdminAccount.ARTICLE_LIST;

public class ArticlesPage extends Application {

    private VBox articleContainerVBox; // The container to display the articles

    @Override
    public void start(Stage primaryStage) {

        // Create a ScrollPane to hold the VBox containing articles
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true); // Ensure the ScrollPane fits the width of the content
        scrollPane.setFitToHeight(true); // Ensure the ScrollPane also fits the height of the window

        // ScrollPane styling (optional)
        scrollPane.setStyle("-fx-background-color: transparent;");


        // Create group buttons
        Button eclipseBtn = createGroupButton("Eclipse");
        Button gitHubBtn = createGroupButton("GitHub");
        Button javaFxBtn = createGroupButton("JavaFX");
        Button h2Btn = createGroupButton("H2");
        Button sqlBtn = createGroupButton("SQL");

        // Create the sidebar with groups
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(10, 5, 10, 5));
        sidebar.setStyle("-fx-background-color: #333;");
        sidebar.setPrefWidth(160);  // Adjust the sidebar width
        sidebar.getChildren().addAll(eclipseBtn, gitHubBtn, javaFxBtn, h2Btn, sqlBtn);

        // Create article-related buttons (Create Article, Backup, Restore)
        Button createArticleBtn = new Button("Create Article");
        createArticleBtn.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        createArticleBtn.setPrefWidth(150);
        createArticleBtn.setPrefHeight(35);
        createArticleBtn.setFont(Font.font("Arial", 15));
        createArticleBtn.setOnAction(e -> showCreateArticleScreen(primaryStage));

        Button backupBtn = new Button("Backup");
        backupBtn.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        backupBtn.setPrefWidth(100);
        backupBtn.setPrefHeight(35);
        backupBtn.setFont(Font.font("Arial", 15));
        backupBtn.setOnAction(e -> showBackupScreen(primaryStage));

        Button restoreBtn = new Button("Restore");
        restoreBtn.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        restoreBtn.setPrefWidth(100);
        restoreBtn.setPrefHeight(35);
        restoreBtn.setFont(Font.font("Arial", 15));
        restoreBtn.setOnAction(e -> showRestoreArticles(primaryStage));

        // HBox to hold article-related buttons
        HBox articleButtons = new HBox(20, createArticleBtn, backupBtn, restoreBtn);
        articleButtons.setAlignment(Pos.CENTER);
        articleButtons.setPadding(new Insets(0, 0, 0, 0));

        // VBox to hold the article container (which will display the articles dynamically)
        articleContainerVBox = new VBox(10); // This will hold dynamically added articles
        articleContainerVBox.setPrefSize(700, 565); // Set preferred size
        articleContainerVBox.setStyle("-fx-background-color: white;");

        scrollPane.setContent(articleContainerVBox); // Add VBox to ScrollPane
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Always show vertical scrollbar
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  // Disable horizontal scrollbar
        // VBox to hold the article buttons and container
        VBox articleSection = new VBox(20, articleButtons, scrollPane);
        articleSection.setAlignment(Pos.TOP_CENTER);
        articleSection.setPadding(new Insets(20));

        // Back Button using the ButtonStyleUtil class
        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showPreviousScreen(primaryStage)); // Call the showPreviousScreen method

        // Search Bar
        TextField searchField = new TextField();
        searchField.setPromptText("Search articles...");
        searchField.setPrefWidth(250);  // Adjust the width of the search field
        searchField.setPrefHeight(30);

        // Search Button with "Search" text
        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        searchButton.setFont(Font.font("Arial", 15));
        searchButton.setPrefHeight(29);

        // Search bar layout
        HBox searchBar = new HBox(5, searchField, searchButton);
        searchBar.setAlignment(Pos.CENTER);
        searchBar.setPadding(new Insets(10, 0, 0, 240));


        // Top bar layout with Back, Search, and Logout buttons
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 0, 10));
        topBar.setSpacing(10);

        HBox leftBox = new HBox(backButton);
        leftBox.setAlignment(Pos.TOP_LEFT);

        HBox rightBox = new HBox(createArticleBtn);
        rightBox.setAlignment(Pos.TOP_RIGHT);

        topBar.getChildren().addAll(leftBox, searchBar, rightBox);
        topBar.setAlignment(Pos.CENTER);
        HBox.setHgrow(rightBox, Priority.ALWAYS); // Make the right box grow to fit

        // Main layout with sidebar and main content area (article section)
        HBox mainLayout = new HBox(sidebar, articleSection);

        // Create the root layout and set the top bar and main layout
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(mainLayout);
        root.setStyle("-fx-background-color: #f8f5f3;");

        // Create the scene and set it on the stage
        Scene scene = new Scene(root, 900, 700);
        primaryStage.setTitle("Article Dashboard");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        // Load articles for the default group (e.g., Eclipse) when the app starts
        displayArticlesForGroup("Eclipse");
    }

    private void showPreviousScreen(Stage primaryStage) {
        AdminPage adminPage = new AdminPage();
        try{
            adminPage.start(primaryStage);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    // Helper method to create a sidebar group button
    private Button createGroupButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-font-size: 19px;");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPadding(new Insets(10, 20, 10, 20));
        button.setAlignment(Pos.CENTER_LEFT);

        // Set action when the group button is clicked
        button.setOnAction(e -> displayArticlesForGroup(text));
        return button;
    }

    // Method to dynamically display articles for a specific group
    private void displayArticlesForGroup(String groupName) {
        // Clear previous articles
        articleContainerVBox.getChildren().clear();

        // Iterate over the articles in the ArticleList
        int numArticles = 0;
        for (Article article : ARTICLE_LIST) {
            numArticles++;
            System.out.println(numArticles);

            // Create VBox for each article with padding and border
            VBox articleBox = new VBox(5);
            articleBox.setPadding(new Insets(10));
            articleBox.setStyle("-fx-border-color: lightgray; -fx-border-width: 0 0 1 0; -fx-background-color: white;");
            articleBox.setAlignment(Pos.TOP_LEFT);

            // Create HBox for title and level
            HBox titleLevelBox = new HBox(10);
            Label titleLabel = new Label(article.getTitle());
            titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 17px; -fx-text-fill: #8b0000;");
            titleLabel.setFont(Font.font("Arial", 17));

            Label levelLabel = new Label("(" + article.getLevel() + ")");
            levelLabel.setStyle("-fx-text-fill: gray; -fx-font-size: 14px;");
            levelLabel.setFont(Font.font("Arial", 14));

            titleLevelBox.getChildren().addAll(titleLabel, levelLabel);
            titleLevelBox.setAlignment(Pos.TOP_LEFT);

            // Create the 3-dots button for options
            Button optionsButton = new Button("...");
            optionsButton.setStyle("-fx-background-color: transparent; -fx-font-size: 20px;");
            optionsButton.setOnAction(e -> showArticleOptions(article, optionsButton)); // Pass button reference

            // Create HBox for title, level, and options button
            HBox titleOptionsBox = new HBox();
            titleOptionsBox.getChildren().addAll(titleLevelBox, optionsButton);
            HBox.setHgrow(titleLevelBox, Priority.ALWAYS); // Make titleLevelBox grow horizontally
            titleOptionsBox.setAlignment(Pos.TOP_RIGHT); // Align the options button to the right

            // Add titleOptionsBox and abstract to articleBox (VBox)
            articleBox.getChildren().addAll(titleOptionsBox, new Label(article.getAbs()));

            // Add the articleBox (for each article) to the main VBox
            articleContainerVBox.getChildren().add(articleBox);
        }
    }



    // Show options for updating or deleting an article
    private void showArticleOptions(Article article, Button optionsButton) {
        // Create a ContextMenu (popup menu)
        ContextMenu contextMenu = new ContextMenu();

        // Create the Delete Article menu item
        MenuItem deleteItem = new MenuItem("Delete Article");
        deleteItem.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        deleteItem.setOnAction(e -> {
            ARTICLE_LIST.removeArticle(article);
            displayArticlesForGroup("Eclipse");
            contextMenu.hide(); // Hide the context menu after action
        });

        // Create the Update Article menu item
        MenuItem updateItem = new MenuItem("Update Article");
        updateItem.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        updateItem.setOnAction(e -> {
            System.out.println("Updating article: " + article.getTitle());
            contextMenu.hide(); // Hide the context menu after action
        });

        // Add the menu items to the context menu
        contextMenu.getItems().addAll(deleteItem, updateItem);

        // Show the context menu relative to the clicked 3-dots button
        contextMenu.show(optionsButton, Side.BOTTOM, 0, 0);
    }




    private void showRestoreArticles(Stage primaryStage) {
        RestoreArticles restoreArticles = new RestoreArticles();
        try{
            restoreArticles.start(primaryStage);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showCreateArticleScreen(Stage primaryStage) {
        CreateArticle createArticle = new CreateArticle();
        try{
            createArticle.start(primaryStage);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showBackupScreen(Stage primaryStage) {
        BackupArticles backupArticles = new BackupArticles();
        try{
            backupArticles.start(primaryStage);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
