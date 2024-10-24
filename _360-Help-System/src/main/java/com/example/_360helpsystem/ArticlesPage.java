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

public class ArticlesPage extends Application {

    private VBox articleContainerVBox; // The container to display the articles
    private List<ArticleData> eclipseArticles; // List of articles for Eclipse group

    @Override
    public void start(Stage primaryStage) {
        // Initialize the articles for the Eclipse group
        initializeArticles();

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
        createArticleBtn.setFont(javafx.scene.text.Font.font("Arial", 15));
        createArticleBtn.setOnAction(e -> showCreateArticleScreen(primaryStage));

        Button backupBtn = new Button("Backup");
        backupBtn.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        backupBtn.setPrefWidth(100);
        backupBtn.setPrefHeight(35);
        backupBtn.setFont(javafx.scene.text.Font.font("Arial", 15));
        backupBtn.setOnAction(e -> showBackupScreen(primaryStage));

        Button restoreBtn = new Button("Restore");
        restoreBtn.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        restoreBtn.setPrefWidth(100);
        restoreBtn.setPrefHeight(35);
        restoreBtn.setFont(javafx.scene.text.Font.font("Arial", 15));
        restoreBtn.setOnAction(e -> showRestoreArticles(primaryStage));

        // HBox to hold article-related buttons
        HBox articleButtons = new HBox(20, createArticleBtn, backupBtn, restoreBtn);
        articleButtons.setAlignment(Pos.CENTER);
        articleButtons.setPadding(new Insets(0, 0, 0, 0));

        // VBox to hold the article container (which will display the articles dynamically)
        articleContainerVBox = new VBox(10); // This will hold dynamically added articles
        articleContainerVBox.setPrefSize(700, 565); // Set preferred size
        articleContainerVBox.setStyle("-fx-background-color: white;");

        // VBox to hold the article buttons and container
        VBox articleSection = new VBox(20, articleButtons, articleContainerVBox);
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
        searchButton.setFont(javafx.scene.text.Font.font("Arial", 15));
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
        articleContainerVBox.getChildren().clear(); // Clear previous articles

        List<ArticleData> articles = eclipseArticles; // Use your actual logic here for groups

        for (ArticleData article : articles) {
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

            // Create the 3-dots button
            Button optionsButton = new Button("...");
            optionsButton.setStyle("-fx-background-color: transparent; -fx-font-size: 20px;");
            optionsButton.setOnAction(e -> showArticleOptions(article, optionsButton)); // Pass button reference

            // Create HBox for title, level, and options button
            HBox titleOptionsBox = new HBox();
            titleOptionsBox.getChildren().addAll(titleLevelBox, optionsButton);
            HBox.setHgrow(titleLevelBox, Priority.ALWAYS); // Make titleLevelBox grow
            titleOptionsBox.setAlignment(Pos.TOP_RIGHT); // Align the options button to the right

            // Add elements to the VBox (article container)
            articleBox.getChildren().addAll(titleOptionsBox, new Label(article.getShortDescription()));

            // Add articleBox to the main VBox
            articleContainerVBox.getChildren().add(articleBox);
        }
    }



    // Show options for updating or deleting an article
    private void showArticleOptions(ArticleData article, Button optionsButton) {
        // Create a ContextMenu (popup menu)
        ContextMenu contextMenu = new ContextMenu();

        // Create the Delete Article menu item
        MenuItem deleteItem = new MenuItem("Delete Article");
        deleteItem.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        deleteItem.setOnAction(e -> {
            deleteArticle(article);
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
        contextMenu.show(optionsButton, javafx.geometry.Side.BOTTOM, 0, 0);
    }



    // Method to delete the article and refresh the list
    private void deleteArticle(ArticleData article) {
        // Remove the article from the list
        eclipseArticles.remove(article); // Assuming you are working with eclipseArticles list

        // Refresh the article list on the screen
        displayArticlesForGroup("Eclipse"); // Or pass the current group dynamically
    }

    // Initialize articles (replace this with actual data fetching logic)
    private void initializeArticles() {
        eclipseArticles = new ArrayList<>();
        eclipseArticles.add(new ArticleData("Eclipse Intro", "Beginner", "Search or browse high-impact publications on various aspects of cancer research, including molecular and cellular biology, clinical studies, epidemiology, prevention, and translational research, as well as meeting abstracts and clinical trial information, to support advancements in cancer prevention, diagnosis, and treatment."));
        eclipseArticles.add(new ArticleData("Eclipse Debugging", "Intermediate", "Comprehensive access to full-text business and economics journals, dissertations, working papers, and key periodicals like The Economist and The Wall Street Journal, along with detailed company and industry reports."));
        // Add more articles as needed
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

    public static void main(String[] args) {
        launch(args);
    }

    // ArticleData class to hold article details
    class ArticleData {
        private String title;
        private String level;
        private String shortDescription;

        public ArticleData(String title, String level, String shortDescription) {
            this.title = title;
            this.level = level;
            this.shortDescription = shortDescription;
        }

        public String getTitle() {
            return title;
        }

        public String getLevel() {
            return level;
        }

        public String getShortDescription() {
            return shortDescription;
        }
    }
}

