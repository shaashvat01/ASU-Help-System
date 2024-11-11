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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static com.example._360helpsystem.CreateAdminAccount.ARTICLE_LIST;
import static com.example._360helpsystem.CreateAdminAccount.GROUP_LIST;
import static com.example._360helpsystem.SignIn.CURRENT_USER;

public class InstructorsArticlePage extends Application {

    private VBox articleContainerVBox; // The container to display the articles
    private Button activeButton = null;
    public static Article selectedArticle = null;

    @Override
    public void start(Stage primaryStage) {

        // Create a ScrollPane to hold the VBox containing articles
        ScrollPane scrollPane_Article = new ScrollPane();
        scrollPane_Article.setFitToWidth(true); // Ensure the ScrollPane fits the width of the content
        scrollPane_Article.setFitToHeight(true); // Ensure the ScrollPane also fits the height of the window

        // ScrollPane styling (optional)
        scrollPane_Article.setStyle("-fx-background-color: transparent;");

        // Create a ScrollPane to hold the VBox containing groups
        ScrollPane scrollPane_Groups = new ScrollPane();
        scrollPane_Groups.setFitToWidth(true); // Ensure the ScrollPane fits the width of the content
        scrollPane_Groups.setFitToHeight(true); // Ensure the ScrollPane also fits the height of the window

        // ScrollPane styling (optional)
        scrollPane_Groups.setStyle("-fx-background-color: transparent;");

        // Create the sidebar with groups
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(10, 5, 10, 5));
        sidebar.setStyle("-fx-background-color: #333;");
        sidebar.setPrefWidth(160);  // Adjust the sidebar width

        Label grpTitle = new Label("Groups");
        grpTitle.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-font-size: 19px;");
        grpTitle.setMaxWidth(Double.MAX_VALUE);
        grpTitle.setPadding(new Insets(10, 0, 10, 0)); // Even padding for left and right
        grpTitle.setAlignment(Pos.CENTER);  // Center the label text


        sidebar.getChildren().add(grpTitle);

        for(String grpName : GROUP_LIST)
        {
            Button groupButton = createGroupButton(grpName,primaryStage);
            sidebar.getChildren().add(groupButton);
        }

        scrollPane_Groups.setContent(sidebar); // Add VBox to ScrollPane
        scrollPane_Groups.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Always show vertical scrollbar
        scrollPane_Groups.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  // Disable horizontal scrollbar

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

        Button createGrpBtn = new Button("Create Group");
        createGrpBtn.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        createGrpBtn.setPrefWidth(125);
        createGrpBtn.setPrefHeight(35);
        createGrpBtn.setFont(Font.font("Arial", 15));
        createGrpBtn.setOnAction(e -> showCreateGroup(sidebar,primaryStage));

        Button deleteGrpBtn = new Button("Delete Group");
        deleteGrpBtn.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        deleteGrpBtn.setPrefWidth(125);
        deleteGrpBtn.setPrefHeight(35);
        deleteGrpBtn.setFont(Font.font("Arial", 15));
        deleteGrpBtn.setOnAction(e -> showDeleteGroup(sidebar));

        HBox articleButtons = new HBox(20);
        articleButtons.setAlignment(Pos.CENTER);
        articleButtons.setPadding(new Insets(0, 0, 0, 0));

        if(CURRENT_USER != null)
        {
            if(CURRENT_USER.isAdmin() || CURRENT_USER.isInstructor())
            {
                // HBox to hold article-related buttons
                articleButtons.getChildren().addAll(createGrpBtn,deleteGrpBtn);
            }

        }

        // HBox to hold article-related buttons
        articleButtons.getChildren().addAll(createArticleBtn, backupBtn, restoreBtn);


        // VBox to hold the article container (which will display the articles dynamically)
        articleContainerVBox = new VBox(10); // This will hold dynamically added articles
        articleContainerVBox.setPrefSize(700, 565); // Set preferred size
        articleContainerVBox.setStyle("-fx-background-color: white;");

        scrollPane_Article.setContent(articleContainerVBox); // Add VBox to ScrollPane
        scrollPane_Article.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Always show vertical scrollbar
        scrollPane_Article.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  // Disable horizontal scrollbar
        // VBox to hold the article buttons and container
        VBox articleSection = new VBox(20, articleButtons, scrollPane_Article);
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
        searchButton.setOnAction(e -> displayArticlesForSearch(searchField.getText(),primaryStage));

        // Filter Button
        Button filterButton = new Button("Filter");
        filterButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        filterButton.setFont(Font.font("Arial", 15));
        filterButton.setPrefHeight(29);
        filterButton.setOnAction(e -> showFilterOptions(primaryStage));

// Search bar layout with Search and Filter buttons
        HBox searchBar = new HBox(5, searchField, searchButton, filterButton);
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
        HBox mainLayout = new HBox(scrollPane_Groups, articleSection);

        // Create the root layout and set the top bar and main layout
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(mainLayout);
        root.setStyle("-fx-background-color: #f8f5f3;");

        // Create the scene and set it on the stage
        Scene scene = new Scene(root, 900, 700);
        primaryStage.setTitle("Instructor Article Dashboard");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        displayArticlesForGroup("General",primaryStage);
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

    private void showPreviousScreen(Stage primaryStage) {
        InstructorPage instructorPage = new InstructorPage();
        try{
            instructorPage.start(primaryStage);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    // Helper method to create a sidebar group button
    // Helper method to create a sidebar group button
    private Button createGroupButton(String text,Stage primaryStage) {
        Button button = new Button(text);

        // Consistent button styling for default, hover, and active states
        String defaultStyle = "-fx-background-color: #333; -fx-text-fill: white; -fx-font-size: 19px; "
                + "-fx-border-radius: 15; -fx-background-radius: 15; "
                + "-fx-border-color: white; -fx-border-width: 2px; "
                + "-fx-padding: 10 0 10 0;";  // Updated padding to be equal on left and right

        String hoverStyle = "-fx-background-color: #555; -fx-text-fill: white; -fx-font-size: 19px; "
                + "-fx-border-radius: 15; -fx-background-radius: 15; "
                + "-fx-border-color: white; -fx-border-width: 2px; "
                + "-fx-padding: 10 0 10 0;";  // Same padding as default

        String activeStyle = "-fx-background-color: #222; -fx-text-fill: white; -fx-font-size: 19px; "
                + "-fx-border-radius: 15; -fx-background-radius: 15; "
                + "-fx-border-color: black; -fx-border-width: 2px; "
                + "-fx-padding: 10 0 10 0;";  // Style for active button

        button.setStyle(defaultStyle);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER);  // Align text to the center

        // Hover effect while maintaining font size and padding
        button.setOnMouseEntered(e -> {
            if (button != activeButton) {
                button.setStyle(hoverStyle);
            }
        });
        button.setOnMouseExited(e -> {
            if (button != activeButton) {
                button.setStyle(defaultStyle);
            }
        });

        // Set action when the group button is clicked
        button.setOnAction(e -> {
            // Reset the currently active button's style to default
            if (activeButton != null) {
                activeButton.setStyle(defaultStyle);
            }

            // Set this button's style to active
            button.setStyle(activeStyle);
            activeButton = button; // Update the active button reference

            // Handle the action for displaying articles
            displayArticlesForGroup(text,primaryStage);
        });

        return button;
    }


    // Method to dynamically display articles for a specific group
    private void displayArticlesForGroup(String groupName,Stage primaryStage) {
        // Clear previous articles
        articleContainerVBox.getChildren().clear();

        for (Article article : ARTICLE_LIST) {
            if(article.hasGroup(groupName) || groupName.equals("General")) {
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
                optionsButton.setOnAction(e -> showArticleOptions(article, optionsButton,primaryStage)); // Pass button reference

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
    }

    public void displayArticlesForSearch(String searchText,Stage primaryStage)
    {
        articleContainerVBox.getChildren().clear();

        for (Article article : ARTICLE_LIST) {
            if(article.hasKeyword(searchText)) {
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
                optionsButton.setOnAction(e -> showArticleOptions(article, optionsButton,primaryStage)); // Pass button reference

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
    }



    // Show options for updating or deleting an article
    private void showArticleOptions(Article article, Button optionsButton,Stage primaryStage) {
        // Create a ContextMenu (popup menu)
        ContextMenu contextMenu = new ContextMenu();

        // Create the Delete Article menu item
        MenuItem deleteItem = new MenuItem("Delete Article");
        deleteItem.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        deleteItem.setOnAction(e -> {
            ARTICLE_LIST.removeArticle(article);
            displayArticlesForGroup("General",primaryStage);
            contextMenu.hide(); // Hide the context menu after action
        });

        // Create the Update Article menu item
        MenuItem updateItem = new MenuItem("Update Article");
        updateItem.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        updateItem.setOnAction(e -> {
            System.out.println("Updating article: " + article.getUID());
            selectedArticle = article;
            showCreateArticleScreen(primaryStage); //go to article screen
            contextMenu.hide(); // Hide the context menu after action
        });

        // Add the menu items to the context menu
        contextMenu.getItems().addAll(deleteItem, updateItem);

        // Show the context menu relative to the clicked 3-dots button
        contextMenu.show(optionsButton, Side.BOTTOM, 0, 0);
    }

    private void showCreateGroup(VBox sidebar,Stage primaryStage) {
        // Create a new Stage (window) for the pop-up
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);  // Make the pop-up modal
        popupStage.setTitle("Create New Group");

        // Create a VBox layout for the pop-up content
        VBox popupLayout = new VBox(10);
        popupLayout.setPadding(new Insets(15));
        popupLayout.setAlignment(Pos.CENTER);

        // Create the TextField for entering the group name
        TextField groupNameField = new TextField();
        groupNameField.setPromptText("Enter Group Name");  // Set placeholder text
        groupNameField.setPrefWidth(200);

        // Create the "Create" button
        Button createButton = new Button("Create");
        createButton.setStyle("-fx-background-color: #333; -fx-text-fill: white;");
        createButton.setPrefWidth(100);

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);  // Set text color to red for visibility

        // Handle "Create" button action
        createButton.setOnAction(event -> {
            String groupName = groupNameField.getText();
            if (!groupName.isEmpty()) {
                // Handle group creation logic here
                if(!GROUP_LIST.contains(groupName)) {
                    GROUP_LIST.addGroup(groupName);
                    System.out.println("Group Created: " + groupName);
                    sidebar.getChildren().add(createGroupButton(groupName,primaryStage));
                    // Close the pop-up after creation
                    popupStage.close();
                }

                errorLabel.setText("Group " + groupName + " already exists");


            } else {
                // You can show an alert or error message if the field is empty
                errorLabel.setText("Group name cannot be empty.");
            }
        });

        // Add the TextField and button to the layout
        popupLayout.getChildren().addAll(new Label("Group Name:"), groupNameField,errorLabel, createButton);

        // Create a Scene for the pop-up window and set it to the stage
        Scene popupScene = new Scene(popupLayout, 300, 150);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();  // Show the pop-up and wait until it is closed
    }

    private void showDeleteGroup(VBox sidebar) {
        // Create a new Stage (window) for the pop-up
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);  // Make the pop-up modal
        popupStage.setTitle("Delete Group");

        // Create a VBox layout for the pop-up content
        VBox popupLayout = new VBox(10);
        popupLayout.setPadding(new Insets(15));
        popupLayout.setAlignment(Pos.CENTER);

        // Create the TextField for entering the group name to delete
        TextField groupNameField = new TextField();
        groupNameField.setPromptText("Enter Group Name to Delete");  // Set placeholder text
        groupNameField.setPrefWidth(200);

        // Create the "Delete" button
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-background-color: #333; -fx-text-fill: white;");
        deleteButton.setPrefWidth(100);

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);  // Set text color to red for visibility

        // Handle "Delete" button action
        deleteButton.setOnAction(event -> {
            String groupName = groupNameField.getText();
            if (!groupName.isEmpty()) {
                // Handle group deletion logic here
                if(GROUP_LIST.contains(groupName) && !groupName.equalsIgnoreCase("General")) {
                    GROUP_LIST.removeGroup(groupName); // Assuming removeGroup is a method in GROUP_LIST
                    System.out.println("Group Deleted: " + groupName);
                    // Remove the corresponding button from the sidebar
                    sidebar.getChildren().removeIf(node -> {
                        if (node instanceof Button) {
                            Button button = (Button) node;
                            return button.getText().equals(groupName); // Check if the button text matches
                        }
                        return false;
                    });
                    // Close the pop-up after deletion
                    popupStage.close();
                } else {
                    if(groupName.equalsIgnoreCase("General")) {
                        errorLabel.setText("Cannot delete " + groupName);
                    }
                    else{
                        errorLabel.setText("Group " + groupName + " does not exist.");
                    }

                }
            } else {
                // Show an alert or error message if the field is empty
                errorLabel.setText("Group name cannot be empty.");
            }
        });

        // Add the TextField and button to the layout
        popupLayout.getChildren().addAll(new Label("Group Name:"), groupNameField, errorLabel, deleteButton);

        // Create a Scene for the pop-up window and set it to the stage
        Scene popupScene = new Scene(popupLayout, 300, 150);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();  // Show the pop-up and wait until it is closed
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
