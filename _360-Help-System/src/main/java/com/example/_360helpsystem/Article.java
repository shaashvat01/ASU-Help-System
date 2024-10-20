package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Article extends Application {

    @Override
    public void start(Stage primaryStage) {
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

        // Create the Logout button
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        logoutButton.setFont(javafx.scene.text.Font.font("Arial", 18));
        logoutButton.setPrefWidth(100);  // Set preferred width to control the button size
        logoutButton.setPrefHeight(35);  // Set preferred height

        logoutButton.setOnAction(e -> showSignInPage(primaryStage));

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
        searchBar.setPadding(new Insets(10, 0, 0, 100));

        // Create article-related buttons (Create Article, Backup, Restore) with reduced padding
        Button createArticleBtn = new Button("Create Article");
        createArticleBtn.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        createArticleBtn.setPrefWidth(150);  // Set the preferred width similar to Logout
        createArticleBtn.setPrefHeight(35);  // Set the preferred height
        createArticleBtn.setFont(javafx.scene.text.Font.font("Arial", 15));
        createArticleBtn.setOnAction(e -> showCreateArticleScreen(primaryStage));

        Button backupBtn = new Button("Backup");
        backupBtn.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        backupBtn.setPrefWidth(100);  // Set the preferred width similar to Logout
        backupBtn.setPrefHeight(35);  // Set the preferred height
        backupBtn.setFont(javafx.scene.text.Font.font("Arial", 15));
        backupBtn.setOnAction(e -> showBackupScreen(primaryStage));

        Button restoreBtn = new Button("Restore");
        restoreBtn.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        restoreBtn.setPrefWidth(100);  // Set the preferred width similar to Logout
        restoreBtn.setPrefHeight(35);  // Set the preferred height
        restoreBtn.setFont(javafx.scene.text.Font.font("Arial", 15));
        restoreBtn.setOnAction(e -> showRestoreArticles(primaryStage));

        // HBox to hold article-related buttons with reduced padding
        HBox articleButtons = new HBox(20, createArticleBtn, backupBtn, restoreBtn); // Adjust spacing between buttons
        articleButtons.setAlignment(Pos.CENTER);
        articleButtons.setPadding(new Insets(0, 0, 0, 0)); // Reduced padding for better alignment

        // Create a large box to display articles in the future
        Rectangle articleContainer = new Rectangle(590, 450);  // Adjust width and height as needed
        articleContainer.setFill(Color.WHITE);
        //articleContainer.setStroke(Color.BLACK);  // Border for the container

        // VBox to hold the article buttons and container
        VBox articleSection = new VBox(20, articleButtons, articleContainer);
        articleSection.setAlignment(Pos.TOP_CENTER);
        articleSection.setPadding(new Insets(20));

        // Top bar layout with Back, Search, and Logout buttons
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 0, 10));
        topBar.setSpacing(10);

        HBox leftBox = new HBox(backButton);
        leftBox.setAlignment(Pos.TOP_LEFT);  // Align back button to the left

        HBox rightBox = new HBox(logoutButton);
        rightBox.setAlignment(Pos.TOP_RIGHT);  // Align logout button to the right

        topBar.getChildren().addAll(leftBox, searchBar, rightBox);
        HBox.setHgrow(searchBar, Priority.ALWAYS); // Make the search bar grow to fit

        // Main layout with sidebar and main content area (article section)
        HBox mainLayout = new HBox(sidebar, articleSection);

        // Create the root layout and set the top bar and main layout
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(mainLayout);
        root.setStyle("-fx-background-color: #f8f5f3;");

        // Create the scene and set it on the stage
        Scene scene = new Scene(root, 900, 700);  // Set the window size to 800x600
        primaryStage.setTitle("Article Dashboard");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);  // Disable resizing if you want a fixed size
        primaryStage.show();
    }

    // Helper method to create a sidebar group button
    private Button createGroupButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-font-size: 19px;");
        button.setMaxWidth(Double.MAX_VALUE);  // Make the button fill the width of the sidebar
        button.setPadding(new Insets(10, 20, 10, 20));
        button.setAlignment(Pos.CENTER_LEFT);
        return button;
    }

    private void showRestoreArticles(Stage primaryStage) {
        RestoreArticles restoreArticles = new RestoreArticles();
        try{
            restoreArticles.start(primaryStage);
        }
        catch(Exception ex){
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
        BackupArticles backup = new BackupArticles();
        try{
            backup.start(primaryStage);
        }
        catch (Exception ex) {
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

    private void showPreviousScreen(Stage primaryStage) {
        AdminPage adminpage = new AdminPage();
        try {
            adminpage.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
